package edu.dosw.service;

import edu.dosw.dto.*;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    public BillResponseDTO createOrder(OrderRequestDTO orderRequest) {
        if (orderRequest.getFurnitureIds() == null || orderRequest.getFurnitureIds().isEmpty()) {
            throw new RuntimeException("La orden debe contener al menos un mueble");
        }

        BillResponseDTO response = new BillResponseDTO();
        response.setOrderId(1);

        ClientDTO client = new ClientDTO();
        client.setId(orderRequest.getClientId());
        client.setName("Cliente " + orderRequest.getClientId());
        client.setAddress("Dirección del cliente");
        response.setClient(client);


        List<FurnitureDTO> furnitures = new ArrayList<>();
        double subtotal = 0;

        for (Integer furnitureId : orderRequest.getFurnitureIds()) {
            FurnitureDTO furniture = new FurnitureDTO();
            furniture.setId(furnitureId);
            furniture.setName("Mueble " + furnitureId);
            furniture.setPrice(100.0 * furnitureId);
            furniture.setStyle(edu.dosw.model.Style.CLASSIC);
            furnitures.add(furniture);
            subtotal += furniture.getPrice();
        }

        response.setFurnitures(furnitures);
        response.setSubtotal(subtotal);


        double discount = orderRequest.isApplyDiscount() ? subtotal * 0.1 : 0;
        response.setDiscount(discount);


        double shipping = orderRequest.isIncludeShipping() ? orderRequest.getShippingCost() : 0;
        response.setShipping(shipping);


        double iva = (subtotal - discount) * 0.16;
        response.setIva(iva);

        double total = subtotal - discount + shipping + iva;
        response.setTotal(total);

        return response;
    }

    public BillResponseDTO getOrder(int id) {

        OrderRequestDTO request = new OrderRequestDTO();
        request.setClientId(1);
        request.setFurnitureIds(List.of(1, 2));
        request.setApplyDiscount(true);
        request.setIncludeShipping(true);
        request.setShippingCost(50.0);

        BillResponseDTO response = createOrder(request);
        response.setOrderId(id);
        return response;
    }
}