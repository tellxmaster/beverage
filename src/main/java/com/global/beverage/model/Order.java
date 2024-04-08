package com.global.beverage.model;
import java.util.Map;


public class Order {
    private Customer customer;
    private Map<Product, Integer> productQuantities;

    public Order() {
    }

    public Order(Customer customer, Map<Product, Integer> productQuantities) {
        this.customer = customer;
        this.productQuantities = productQuantities;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Map<Product, Integer> getProductQuantities() {
        return productQuantities;
    }

    public void setProductQuantities(Map<Product, Integer> productQuantities) {
        this.productQuantities = productQuantities;
    }

    @Override
    public String toString() {
        return "Order{" +
                "customer=" + customer +
                ", productQuantities=" + productQuantities +
                '}';
    }
}
