package com.kumar.ProductsModule;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    //Get all product
    @GetMapping
    public ResponseEntity<ProductResponse> getAllOrders() {

        List<Product> orders = productRepository.findAll();
        String message;
        if (orders.isEmpty()) {
            message = "No Orders found";
        } else {
            message = "Orders retrieved successfully. Total: " + orders.size();
        }
        ProductResponse response = new ProductResponse(message, orders);
        return ResponseEntity.ok(response);
    }

// Create a product
@PostMapping
public ResponseEntity<ProductResponse> createProduct(@RequestBody Product product) {
    Product savedProduct = productRepository.save(product);
    String message = "Product created successfully. Product ID: " + savedProduct.getId();
    ProductResponse response = new ProductResponse(message, savedProduct);
    return ResponseEntity.ok(response);
}
    //Get product by id
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update a product
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(productDetails.getName());
                    product.setPrice(productDetails.getPrice());

                    // Save the updated product
                    Product updatedProduct = productRepository.save(product);

                    // Create a response with the updated product
                    ProductResponse response = new ProductResponse("Product updated successfully. Product ID: " + updatedProduct.getId(),updatedProduct);
                    return ResponseEntity.ok(response);
                }).orElse(ResponseEntity.ok(new ProductResponse("Product not found with ID: " + id)));
    }
    // Delete a product
    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponse> deleteProduct(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(product -> {
                    // Store product details before deletion
                    Product deletedProduct = product;
                    productRepository.deleteById(id);
                    ProductResponse response = new ProductResponse("Product deleted successfully. ID: " + id, deletedProduct);
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.ok(new ProductResponse("Product not found with ID: " + id)));
    }
}
