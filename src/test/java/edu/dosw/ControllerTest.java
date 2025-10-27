package src.test.java.edu.dosw;
import edu.dosw.controller.ClientController;
import edu.dosw.controller.FurnitureController;
import edu.dosw.controller.OrderController;
import edu.dosw.dto.ClientDTO;
import edu.dosw.dto.FurnitureDTO;
import edu.dosw.dto.OrderRequestDTO;
import edu.dosw.dto.BillResponseDTO;
import edu.dosw.service.ClientService;
import edu.dosw.service.FurnitureService;
import edu.dosw.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ControllerTest {

    @Mock
    private ClientService clientService;
    @InjectMocks
    private ClientController clientController;
    @Mock
    private ClientDTO clientDTO;

    @Mock
    private FurnitureService furnitureService;
    @InjectMocks
    private FurnitureController furnitureController;
    @Mock
    private FurnitureDTO furnitureDTO;
    @Mock
    private OrderService orderService;
    @InjectMocks
    private OrderController orderController;
    @Mock
    private OrderRequestDTO orderRequestDTO;
    @Mock
    private BillResponseDTO billResponseDTO;

    @Test
    void testClientController_basicFlows() {
        when(clientService.registerClient(clientDTO)).thenReturn(clientDTO);
        ResponseEntity<ClientDTO> regResp = clientController.registerClient(clientDTO);
        assertEquals(200, regResp.getStatusCodeValue());
        assertEquals(clientDTO, regResp.getBody());
        when(clientService.getAllClients()).thenReturn(Arrays.asList(clientDTO));
        ResponseEntity<List<ClientDTO>> allResp = clientController.getAllClients();
        assertEquals(200, allResp.getStatusCodeValue());
        assertEquals(1, allResp.getBody().size());
        when(clientService.getClientById(1)).thenReturn(clientDTO);
        ResponseEntity<ClientDTO> byIdResp = clientController.getClientById(1);
        assertEquals(200, byIdResp.getStatusCodeValue());
        assertEquals(clientDTO, byIdResp.getBody());
    }

    @Test
    void testFurnitureController_basicFlows() {
        when(furnitureService.getFurnitureCatalog(null, null)).thenReturn(Arrays.asList(furnitureDTO));
        ResponseEntity<List<FurnitureDTO>> catalogResp = furnitureController.getFurnitureCatalog(null, null);
        assertEquals(200, catalogResp.getStatusCodeValue());
        assertEquals(1, catalogResp.getBody().size());
        when(furnitureService.addFurniture(furnitureDTO)).thenReturn(furnitureDTO);
        ResponseEntity<FurnitureDTO> addResp = furnitureController.addFurniture(furnitureDTO);
        assertEquals(200, addResp.getStatusCodeValue());
        assertEquals(furnitureDTO, addResp.getBody());
    }

    @Test
    void testOrderController_basicFlows() {
        when(orderService.createOrder(orderRequestDTO)).thenReturn(billResponseDTO);
        ResponseEntity<BillResponseDTO> createResp = orderController.createOrder(orderRequestDTO);
        assertEquals(200, createResp.getStatusCodeValue());
        assertEquals(billResponseDTO, createResp.getBody());
        when(orderService.getOrder(1)).thenReturn(billResponseDTO);
        ResponseEntity<BillResponseDTO> getResp = orderController.getOrder(1);
        assertEquals(200, getResp.getStatusCodeValue());
        assertEquals(billResponseDTO, getResp.getBody());
    }
}