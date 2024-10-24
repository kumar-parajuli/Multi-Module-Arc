package com.kumar.CustomerModule;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping
    public ResponseEntity<CustomerResponse>getAllCustomer() {
        List<Customer> customers = customerRepository.findAll();
        String message;
        if (customers.isEmpty()) {
            message = "No customers found";
        } else {
            message = "Customer retrived successfully. Total:" + customers.size();
        }
        CustomerResponse response= new CustomerResponse(message,customers);
        return  ResponseEntity.ok(response);
    }
    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
         customerRepository.save(customer);
        return ResponseEntity.ok("Customer created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getCustomerById(@PathVariable Long id) {
        return customerRepository.findById(id)
                .map(customer -> ResponseEntity.ok("Customer retrieved successfully. Name: " + customer.getName() + ", Email: " + customer.getEmail()))
                .orElse(ResponseEntity.ok("Customer not found with ID: " + id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setName(customerDetails.getName());
                    customer.setEmail(customerDetails.getEmail());
                    Customer updatedCustomer = customerRepository.save(customer);
                    CustomerResponse response = new CustomerResponse("Customer updated successfully. Customer ID:" +updatedCustomer.getId());
                    return ResponseEntity.ok(response);
                }).orElse(ResponseEntity.ok(new CustomerResponse("Customer not found with ID: " + id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return ResponseEntity.ok("Customer deleted successfully.");
        }
        return ResponseEntity.ok("Customer not found with ID: " + id);
    }
}
