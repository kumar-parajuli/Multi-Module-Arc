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

    //Fetch all the order
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

    //Add the new Order
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody Orders order) {
        Orders savedOrder = orderRepository.save(order);
        String message = "Order created successfully. ID: " + savedOrder.getId();
        OrderResponse response = new OrderResponse(message, savedOrder);
        return ResponseEntity.ok(response);
    }

    //Get Single order By ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id) {
        return orderRepository.findById(id)
                .map(order -> {
                    OrderResponse response = new OrderResponse("Order retrieved successfully.", order);
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.ok(new OrderResponse("Order not found with ID: " + id)));
    }

    //Update the order
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
                }).orElse(ResponseEntity.ok(new OrderResponse("Order not found with ID: " + id)));
    }

    //Delete the order
    @DeleteMapping("/{id}")
    public ResponseEntity<OrderResponse> deleteOrder(@PathVariable Long id) {
        return orderRepository.findById(id)
                .map(order -> {
                    // Delete the order
                    orderRepository.deleteById(id);
                    // Return success message along with deleted order details
                    OrderResponse response = new OrderResponse("Order deleted successfully. ID: " + id, order);
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.ok(new OrderResponse("Order not found with ID: " + id)));
    }
}
