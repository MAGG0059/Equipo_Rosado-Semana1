package edu.dosw.service;

import edu.dosw.model.*;
import edu.dosw.dto.*;
import edu.dosw.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private FurnitureService furnitureService;

    public BillResponseDTO createOrder(OrderRequestDTO orderRequest) {
        Client client = clientService.getClientEntityById(orderRequest.getClientId());

        List<Furniture> furnitures = furnitureService.getFurnitureEntitiesByIds(orderRequest.getFurnitureIds());
        if (furnitures.isEmpty()) {
            throw new RuntimeException("No se encontraron muebles con los IDs proporcionados");
        }

        BillComponent bill = new Bill(client, furnitures);
        double subtotal = ((Bill) bill).getSubtotal();

        double discountAmount = 0;
        double shippingAmount = 0;

        if (orderRequest.isApplyDiscount()) {
            discountAmount = subtotal * 0.10;
            bill = new DiscountDecorator(bill, 0.10);
        }

        bill = new IVADecorator(bill);
        double ivaAmount = subtotal * 0.19;

        if (orderRequest.isIncludeShipping()) {
            shippingAmount = orderRequest.getShippingCost();
            bill = new EnvioDecorator(bill, shippingAmount);
        }

        orderRepository.save(bill);

        return createBillResponseDTO(bill, client, furnitures, subtotal, ivaAmount, discountAmount, shippingAmount);
    }

    public BillResponseDTO getOrder(int orderId) {
        BillComponent bill = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Orden no encontrada"));

        Bill baseBill = extractBaseBill(bill);
        Client client = baseBill.getClient();
        List<Furniture> furnitures = baseBill.getFurnitures();

        double subtotal = baseBill.getSubtotal();
        double total = bill.getTotal();
        double ivaAmount = subtotal * 0.19;

        double discountAmount = calculateDiscount(bill, subtotal);
        double shippingAmount = calculateShipping(bill);

        return createBillResponseDTO(bill, client, furnitures, subtotal, ivaAmount, discountAmount, shippingAmount);
    }

    private BillResponseDTO createBillResponseDTO(BillComponent bill, Client client, List<Furniture> furnitures, double subtotal, double iva, double discount, double shipping) {
        BillResponseDTO response = new BillResponseDTO();

        response.setOrderId(1);

        response.setClient(new ClientDTO(client.getIdClient(), client.getName(), client.getAddress()));

        List<FurnitureDTO> furnitureDTOs = furnitures.stream().map(f -> new FurnitureDTO(f.getId(), f.getName(), f.getPrice(), f.getStyle())).collect(Collectors.toList());
        response.setFurnitures(furnitureDTOs);

        response.setSubtotal(subtotal);
        response.setTotal(bill.getTotal());
        response.setIva(iva);
        response.setDiscount(discount);
        response.setShipping(shipping);

        return response;
    }

    private Bill extractBaseBill(BillComponent bill) {
        if (bill instanceof Bill) {
            return (Bill) bill;
        } else if (bill instanceof BillDecorator) {
            return extractBaseBill(((BillDecorator) bill).wrappedBill);
        }
        throw new RuntimeException("No se pudo extraer la factura");
    }

    private double calculateDiscount(BillComponent bill, double subtotal) {
        if (bill instanceof DiscountDecorator) {
            return subtotal * 0.10;
        }
        return 0;
    }

    private double calculateShipping(BillComponent bill) {
        if (bill instanceof EnvioDecorator) {
            return ((EnvioDecorator) bill).getCostoEnvio();
        }
        return 0;
    }
}