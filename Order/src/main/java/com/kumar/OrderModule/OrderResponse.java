package com.kumar.OrderModule;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderResponse {
    private String message;
    private List<Orders> orders;

    // Constructor for a list of orders
    public OrderResponse(String message, List<Orders> orders) {
        this.message = message;
        this.orders = orders;
    }
    // Constructor for message only
    public OrderResponse(String message) {
        this.message = message;
    }
}
