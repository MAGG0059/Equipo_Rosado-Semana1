package edu.dosw.repository;

import edu.dosw.model.Client;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class ClientRepository {

    private Map<Integer, Client> clients = new HashMap<>();
    private int nextClientId = 1;

    public Client save(Client client) {
        if (client.getIdClient() == 0) {
            client = new Client(nextClientId++, client.getName(), client.getAddress());
        }
        clients.put(client.getIdClient(), client);
        return client;
    }

    public Optional<Client> findById(int id) {
        return Optional.ofNullable(clients.get(id));
    }

    public List<Client> findAll() {
        return new ArrayList<>(clients.values());
    }

    public void deleteById(int id) {
        clients.remove(id);
    }
}