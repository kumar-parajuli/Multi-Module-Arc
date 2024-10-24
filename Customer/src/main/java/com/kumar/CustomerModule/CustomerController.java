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

    //Get the all Customer
    @GetMapping
    public ResponseEntity<CustomerResponse> getAllCustomer() {
        List<Customer> customers = customerRepository.findAll();
        String message;
        if (customers.isEmpty()) {
            message = "No customers found";
        } else {
            message = "Customer retrived successfully. Total:" + customers.size();
        }
        CustomerResponse response = new CustomerResponse(message, customers);
        return ResponseEntity.ok(response);
    }

    //Add new costumer
    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        String message = "Customer created successfully. ID: " + savedCustomer.getId();
        CustomerResponse response = new CustomerResponse(message, savedCustomer);
        return ResponseEntity.ok(response);
    }

    //Get the single customer by id
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Long id) {
        return customerRepository.findById(id)
                .map(customer -> {
                    // Create a CustomerResponse with the customer details
                    CustomerResponse response = new CustomerResponse("Customer retrieved successfully.", customer);
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.ok(new CustomerResponse("Customer not found with ID: " + id)));
    }

    // To update the customer details
    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
        return customerRepository.findById(id)
                .map(customer -> {
                    // Update customer details
                    customer.setName(customerDetails.getName());
                    customer.setEmail(customerDetails.getEmail());

                    // Save the updated customer
                    Customer updatedCustomer = customerRepository.save(customer);

                    // Create response with the updated customer details
                    CustomerResponse response = new CustomerResponse("Customer updated successfully. Customer ID: " + updatedCustomer.getId(), updatedCustomer);

                    return ResponseEntity.ok(response);
                }).orElse(ResponseEntity.ok(new CustomerResponse("Customer not found with ID: " + id)));
    }
    //To delete the customer By ID
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerResponse> deleteCustomer(@PathVariable Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            // Return a response with a success message
            return ResponseEntity.ok(new CustomerResponse("Customer deleted successfully. ID: " + id));
        }
        // Return a response with a not found message
        return ResponseEntity.ok(new CustomerResponse("Customer not found with ID: " + id));
    }
}
