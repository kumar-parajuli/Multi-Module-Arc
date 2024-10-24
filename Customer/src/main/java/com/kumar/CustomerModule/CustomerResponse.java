package com.kumar.CustomerModule;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomerResponse {
    private String message;
//    private Customer customer;
    private List<Customer> customers;

//    // Constructor for a single customer
//    public CustomerResponse(String message, Customer customer) {
//        this.message = message;
//        this.customer = customer;
//    }

    // Constructor for a list of customers
    public CustomerResponse(String message, List<Customer> customers) {
        this.message = message;
        this.customers = customers;
    }

    // Constructor for message only
    public CustomerResponse(String message) {
        this.message = message;
    }
}
