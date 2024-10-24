package com.kumar.ProductsModule;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse {
    private String message;
    private Product product;  // Single product response
    private List<Product> products; // List of products response

    // Constructor for a single product
    public ProductResponse(String message, Product product) {
        this.message = message;
        this.product = product;  // Fixed to set the product
    }

    // Constructor for a list of products
    public ProductResponse(String message, List<Product> products) {
        this.message = message;
        this.products = products;
    }

    // Constructor for message only
    public ProductResponse(String message) {
        this.message = message;
    }
}
