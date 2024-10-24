package com.kumar.CustomerModule;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerResponse {
    private String message;          // Response message
    private Customer customer;       // Single customer for create and update responses
    private List<Customer> customers; // List of customers for retrieval responses

    // Constructor for a single customer
    public CustomerResponse(String message, Customer customer) {
        this.message = message;
        this.customer = customer;
    }

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
