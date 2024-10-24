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
    public ResponseEntity<OrderResponse> getAllOrders() {

        List<Orders> orders = orderRepository.findAll();
        String message;
        if (orders.isEmpty()) {
            message = "No Orders found";
        } else {
            message = "Orders retrieved successfully. Total: " + orders.size();
        }
        OrderResponse response = new OrderResponse(message, orders);
        return ResponseEntity.ok(response);
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
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable Long id, @RequestBody Orders orderDetails) {
        return orderRepository.findById(id)
                .map(order -> {
                    order.setCustomerId(orderDetails.getCustomerId());
                    order.setProductId(orderDetails.getProductId());
                    order.setQuantity(orderDetails.getQuantity());

                    // Save the updated order
                    Orders updatedOrder = orderRepository.save(order);

                    // Create a response with a single order
                    OrderResponse response = new OrderResponse("Order updated successfully. Order ID: " + updatedOrder.getId());

                    return ResponseEntity.ok(response);
                }).orElse(ResponseEntity.ok(new OrderResponse("Order not found with ID: " + id, null)));
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
