package com.global.beverage.repository;

import com.global.beverage.model.Customer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomerRepository {
    private final Map<Integer, Customer> customers = new HashMap<>();

    public CustomerRepository() {
        // Initialize in-memory database with Customer discount details
        customers.put(1, new Customer(1, 0.05, 0.0, 0.02));
        customers.put(2, new Customer(2, 0.04, 0.01, 0.02));
        customers.put(3, new Customer(3, 0.03, 0.01, 0.03));
        customers.put(4, new Customer(4, 0.02, 0.03, 0.05));
        customers.put(5, new Customer(5, 0.00, 0.05, 0.07));
    }

    public Customer getCustomer(Integer customerId) {
        return customers.get(customerId);
    }
}
