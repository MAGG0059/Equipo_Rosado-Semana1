package edu.dosw.service;

import edu.dosw.dto.ClientDTO;
import edu.dosw.model.Client;
import edu.dosw.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public ClientDTO registerClient(int id, ClientDTO clientDTO) {
        Optional<Client> existingClient = clientRepository.findById(id);
        if (existingClient.isPresent()) {
            throw new RuntimeException("Cliente con ID " + id + " ya existe");
        }

        Client client = new Client(id, clientDTO.getName(), clientDTO.getAddress());
        client.setEmail(clientDTO.getEmail());
        client.setPhone(clientDTO.getPhone());

        Client savedClient = clientRepository.save(client);
        return convertToDTO(savedClient);
    }

    public List<ClientDTO> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ClientDTO getClientById(int id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isEmpty()) {
            throw new RuntimeException("Cliente no encontrado con ID: " + id);
        }
        return convertToDTO(client.get());
    }

    private ClientDTO convertToDTO(Client client) {
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getIdClient());
        dto.setName(client.getName());
        dto.setAddress(client.getAddress());
        dto.setEmail(client.getEmail());
        dto.setPhone(client.getPhone());
        return dto;
    }

    public Client getClientEntityById(int id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isEmpty()) {
            throw new RuntimeException("Cliente no encontrado con ID: " + id);
        }
        return client.get();
    }
}