package edu.dosw.service;

import edu.dosw.model.Client;
import edu.dosw.dto.ClientDTO;
import edu.dosw.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public ClientDTO registerClient(ClientDTO clientDTO) {
        Client client = new Client(0, clientDTO.getName(), clientDTO.getAddress());
        Client savedClient = clientRepository.save(client);
        return convertToDTO(savedClient);
    }

    public Client getClientEntityById(int id) {
        return clientRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encontro al cliente"));
    }

    public ClientDTO getClientById(int id) {
        Client client = getClientEntityById(id);
        return convertToDTO(client);
    }

    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public void deleteClient(int id) {
        clientRepository.deleteById(id);
    }

    private ClientDTO convertToDTO(Client client) {
        return new ClientDTO(
                client.getIdClient(),
                client.getName(),
                client.getAddress()
        );
    }
}