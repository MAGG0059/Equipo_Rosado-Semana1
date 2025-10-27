package edu.dosw.controller;

import edu.dosw.dto.OrderRequestDTO;
import edu.dosw.dto.BillResponseDTO;
import edu.dosw.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ordenes")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<BillResponseDTO> createOrder(@RequestBody OrderRequestDTO orderRequest) {
        try {
            BillResponseDTO billResponse = orderService.createOrder(orderRequest);
            return ResponseEntity.ok(billResponse);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BillResponseDTO> getOrder(@PathVariable int id) {
        try {
            BillResponseDTO billResponse = orderService.getOrder(id);
            return ResponseEntity.ok(billResponse);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}