package com.kumar.OrderModule;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL) // This will prevent null fields from appearing in the JSON response
public class OrderResponse {
    private String message;
    private Orders order;
    private List<Orders> orders;

    // Constructor for a single order
    public OrderResponse(String message, Orders order) {
        this.message = message;
        this.order = order;
    }

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
