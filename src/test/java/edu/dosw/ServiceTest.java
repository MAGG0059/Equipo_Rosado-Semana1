package edu.dosw;

import edu.dosw.model.Client;
import edu.dosw.model.Furniture;
import edu.dosw.model.Style;
import edu.dosw.model.Bill;
import edu.dosw.service.ClientService;
import edu.dosw.service.FurnitureService;
import edu.dosw.service.OrderService;
import edu.dosw.repository.ClientRepository;
import edu.dosw.repository.FurnitureRepository;
import edu.dosw.repository.OrderRepository;
import edu.dosw.dto.ClientDTO;
import edu.dosw.dto.FurnitureDTO;
import edu.dosw.dto.OrderRequestDTO;
import edu.dosw.dto.BillResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {

    @Mock
    private ClientRepository clientRepository;
    @Mock
    private FurnitureRepository furnitureRepository;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ClientService clientServiceDep;
    @Mock
    private FurnitureService furnitureServiceDep;
    @InjectMocks
    private ClientService clientService;
    @InjectMocks
    private FurnitureService furnitureService;
    @InjectMocks
    private OrderService orderService;

    @Test
    void testClientService_basicFlows() {
        Client client = new Client(1, "Juan", "Calle Falsa 123");
        when(clientRepository.save(any(Client.class))).thenReturn(client);
        when(clientRepository.findAll()).thenReturn(Arrays.asList(client));
        when(clientRepository.findById(1)).thenReturn(Optional.of(client));
        ClientDTO inputDto = new ClientDTO(0, "Juan", "Calle Falsa 123");
        ClientDTO saved = clientService.registerClient(inputDto);
        assertNotNull(saved);
        assertEquals(1, saved.getIdClient());
        List<ClientDTO> all = clientService.getAllClients();
        assertEquals(1, all.size());
        ClientDTO byId = clientService.getClientById(1);
        assertEquals("Juan", byId.getName());
    }

    @Test
    void testFurnitureService_catalogAndAdd() {
        Furniture furniture = new Furniture(1, "Silla", 100.0, Style.MODERN) {};
        when(furnitureRepository.findAll()).thenReturn(Arrays.asList(furniture));
        when(furnitureRepository.save(any(Furniture.class))).thenReturn(furniture);
        when(furnitureRepository.findByStyle(Style.MODERN)).thenReturn(Arrays.asList(furniture));
        when(furnitureRepository.findByPriceLessThanEqual(150.0)).thenReturn(Arrays.asList(furniture));
        List<FurnitureDTO> catalogAll = furnitureService.getFurnitureCatalog(null, null);
        assertEquals(1, catalogAll.size());
        List<FurnitureDTO> catalogStyle = furnitureService.getFurnitureCatalog(Style.MODERN, null);
        assertEquals(1, catalogStyle.size());
        List<FurnitureDTO> catalogPrice = furnitureService.getFurnitureCatalog(null, 150.0);
        assertEquals(1, catalogPrice.size());
        FurnitureDTO toAdd = new FurnitureDTO(0, "Silla", 100.0, Style.MODERN);
        FurnitureDTO added = furnitureService.addFurniture(toAdd);
        assertNotNull(added);
        assertEquals(1, added.getId());
    }

    @Test
    void testOrderService_createAndGet_basic() {
        Client client = new Client(1, "Ana", "Av Siempre Viva 1");
        Furniture furniture = new Furniture(1, "Mesa", 200.0, Style.CLASSIC) {};
        List<Integer> furnitureIds = Arrays.asList(1);
        List<Furniture> furnitureList = Arrays.asList(furniture);
        when(clientServiceDep.getClientEntityById(1)).thenReturn(client);
        when(furnitureServiceDep.getFurnitureEntitiesByIds(furnitureIds)).thenReturn(furnitureList);
        when(orderRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
        when(orderRepository.findById(1)).thenReturn(Optional.of(new Bill(client, furnitureList)));
        OrderRequestDTO req = new OrderRequestDTO();
        req.setClientId(1);
        req.setFurnitureIds(furnitureIds);
        req.setApplyDiscount(false);
        req.setIncludeShipping(false);
        req.setShippingCost(0.0);
        BillResponseDTO created = orderService.createOrder(req);
        assertNotNull(created);
        assertEquals(1, created.getOrderId());
        assertEquals(1, created.getClient().getIdClient());
        assertEquals(1, created.getFurnitures().size());
        BillResponseDTO fetched = orderService.getOrder(1);
        assertNotNull(fetched);
        assertEquals(1, fetched.getOrderId());
        assertEquals(1, fetched.getClient().getIdClient());
    }
}