package com.global.beverage.service;

import com.global.beverage.model.Customer;
import com.global.beverage.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public void printDiscountsByCustomer(Customer customer) {
        System.out.println("---------------------------------------------------------------");
        System.out.println(" Basic Discount: " + String.format("%.0f", customer.getBasicDiscount() * 100) + "%" + " | Over 10000 EUR: " + String.format("%.0f", customer.getBulkDiscountThreshold1() * 100) + "%"+ " | Over 30000 EUR: " + String.format("%.0f", customer.getBulkDiscountThreshold2() * 100) + "%");
    }

    public Customer inputCustomer(BufferedReader reader) throws Exception {
        Customer customer = null;
        while (customer == null) {
            System.out.print("-> Enter your Customer ID: ");
            int customerId;
            try {
                customerId = Integer.parseInt(reader.readLine());
                customer = customerRepository.getCustomer(customerId);
                if (customer == null) {
                    System.out.println("The ID provided is invalid. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid entry. Please enter a valid customer ID number.");
            }
        }
        return customer;
    }
}
