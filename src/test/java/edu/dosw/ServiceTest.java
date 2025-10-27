
package edu.dosw;

import edu.dosw.dto.ClientDTO;
import edu.dosw.dto.FurnitureDTO;
import edu.dosw.dto.OrderRequestDTO;
import edu.dosw.model.Style;
import edu.dosw.service.ClientService;
import edu.dosw.service.FurnitureService;
import edu.dosw.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ServiceTest {

    @Autowired
    private ClientService clientService;

    @Autowired
    private FurnitureService furnitureService;

    @Autowired
    private OrderService orderService;

    @Test
    public void testClientRegistrationWithRepository() {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName("Test Client");
        clientDTO.setEmail("test@example.com");
        clientDTO.setPhone("+1234567890");
        clientDTO.setAddress("Test Address");

        ClientDTO saved = clientService.registerClient(100, clientDTO);
        assertNotNull(saved);
        assertEquals(100, saved.getId());
        assertEquals("Test Client", saved.getName());
        assertEquals("test@example.com", saved.getEmail());
    }

    @Test
    public void testGetClientByIdWithRepository() {

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName("Test Client");
        clientDTO.setEmail("test@example.com");
        clientDTO.setAddress("Test Address");
        clientService.registerClient(50, clientDTO);

        ClientDTO client = clientService.getClientById(50);
        assertNotNull(client);
        assertEquals(50, client.getId());
        assertEquals("Test Client", client.getName());
    }

    @Test
    public void testGetAllClientsWithRepository() {
        List<ClientDTO> clients = clientService.getAllClients();
        assertNotNull(clients);
        assertFalse(clients.isEmpty());
    }

    @Test
    public void testClientRegistrationDuplicateId() {
        ClientDTO clientDTO1 = new ClientDTO();
        clientDTO1.setName("Client 1");
        clientDTO1.setEmail("client1@example.com");
        clientService.registerClient(200, clientDTO1);

        ClientDTO clientDTO2 = new ClientDTO();
        clientDTO2.setName("Client 2");
        clientDTO2.setEmail("client2@example.com");

        assertThrows(RuntimeException.class, () -> {
            clientService.registerClient(200, clientDTO2);
        });
    }

    @Test
    public void testGetNonExistentClient() {
        assertThrows(RuntimeException.class, () -> {
            clientService.getClientById(9999);
        });
    }

    @Test
    public void testFurnitureCatalog() {
        FurnitureDTO furniture = new FurnitureDTO();
        furniture.setName("Test Furniture");
        furniture.setPrice(100.0);
        furniture.setStyle(Style.CLASSIC);

        FurnitureDTO saved = furnitureService.addFurniture(furniture);
        assertNotNull(saved);
        assertTrue(saved.getId() > 0);
    }

    @Test
    public void testOrderCreation() {
        OrderRequestDTO orderRequest = new OrderRequestDTO();
        orderRequest.setClientId(1);
        orderRequest.setFurnitureIds(Arrays.asList(1, 2));
        orderRequest.setApplyDiscount(true);
        orderRequest.setIncludeShipping(true);
        orderRequest.setShippingCost(50.0);

        var result = orderService.createOrder(orderRequest);
        assertNotNull(result);
        assertTrue(result.getOrderId() > 0);
        assertTrue(result.getTotal() > 0);
    }

    @Test
    public void testGetFurnitureCatalog() {
        List<FurnitureDTO> furniture = furnitureService.getFurnitureCatalog(Style.CLASSIC, 500.0);
        assertNotNull(furniture);
    }
}