package edu.dosw.controller;

import edu.dosw.dto.ClientDTO;
import edu.dosw.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientDTO> registerClient(@RequestBody ClientDTO clientDTO) {
        try {
            ClientDTO registeredClient = clientService.registerClient(clientDTO);
            return ResponseEntity.ok(registeredClient);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        try {
            List<ClientDTO> clients = clientService.getAllClients();
            return ResponseEntity.ok(clients);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable int id) {
        try {
            ClientDTO client = clientService.getClientById(id);
            return ResponseEntity.ok(client);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}