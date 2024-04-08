package com.global.beverage.model;

public class Customer {
    private int customerId;
    private double basicDiscount;
    private double bulkDiscountThreshold1; // Discount for orders above 10000 EUR
    private double bulkDiscountThreshold2; // Discount for orders above 30000 EUR

    public Customer(int customerId, double basicDiscount, double bulkDiscountThreshold1, double bulkDiscountThreshold2) {
        this.customerId = customerId;
        this.basicDiscount = basicDiscount;
        this.bulkDiscountThreshold1 = bulkDiscountThreshold1;
        this.bulkDiscountThreshold2 = bulkDiscountThreshold2;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getBasicDiscount() {
        return basicDiscount;
    }

    public void setBasicDiscount(double basicDiscount) {
        this.basicDiscount = basicDiscount;
    }

    public double getBulkDiscountThreshold1() {
        return bulkDiscountThreshold1;
    }

    public void setBulkDiscountThreshold1(double bulkDiscountThreshold1) {
        this.bulkDiscountThreshold1 = bulkDiscountThreshold1;
    }

    public double getBulkDiscountThreshold2() {
        return bulkDiscountThreshold2;
    }

    public void setBulkDiscountThreshold2(double bulkDiscountThreshold2) {
        this.bulkDiscountThreshold2 = bulkDiscountThreshold2;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", basicDiscount=" + basicDiscount +
                ", bulkDiscountThreshold1=" + bulkDiscountThreshold1 +
                ", bulkDiscountThreshold2=" + bulkDiscountThreshold2 +
                '}';
    }
}
