package com.kumar.OrderModule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping
    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    @PostMapping
    public Orders createOrder(@RequestBody Orders order) {
        return orderRepository.save(order);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orders> getOrderById(@PathVariable Long id) {
        return orderRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orders> updateOrder(@PathVariable Long id, @RequestBody Orders orderDetails) {
        return orderRepository.findById(id)
                .map(order -> {
                    order.setCustomerId(orderDetails.getCustomerId());
                    order.setProductId(orderDetails.getProductId());
                    order.setQuantity(orderDetails.getQuantity());
                    return ResponseEntity.ok(orderRepository.save(order));
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }}
