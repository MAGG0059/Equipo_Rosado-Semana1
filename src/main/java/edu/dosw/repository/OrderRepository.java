package edu.dosw.repository;

import edu.dosw.model.BillComponent;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class OrderRepository {

    private Map<Integer, BillComponent> orders = new HashMap<>();
    private int nextOrderId = 1;

    public BillComponent save(BillComponent order) {
        int orderId = nextOrderId++;
        orders.put(orderId, order);
        return order;
    }

    public Optional<BillComponent> findById(int id) {
        return Optional.ofNullable(orders.get(id));
    }

    public List<BillComponent> findAll() {
        return new ArrayList<>(orders.values());
    }

    public int getNextOrderId() {
        return nextOrderId;
    }
}