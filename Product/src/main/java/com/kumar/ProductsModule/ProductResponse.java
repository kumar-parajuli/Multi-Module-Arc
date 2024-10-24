package com.kumar.ProductsModule;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductResponse {
    private String message;
    private List<Product> products;

    public ProductResponse(String message, List<Product> products) {
        this.message = message;
        this.products = products;
    }
    // Constructor for message only
    public ProductResponse(String message) {
        this.message = message;
    }
}
