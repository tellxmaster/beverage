package com.global.beverage.service;

import com.global.beverage.model.Customer;
import com.global.beverage.model.Product;
import com.global.beverage.repository.CustomerRepository;
import com.global.beverage.repository.ProductRepository;
import com.global.beverage.response.DiscountDetailsResponse;
import com.global.beverage.response.OrderCalculationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class OrderService {
    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final CustomerRepository customerRepository;

    public OrderService(ProductRepository productRepository, CustomerRepository customerRepository) {
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    public OrderCalculationResponse calculateTotalBeforeDiscounts(Map<String, Integer> productQuantities) {
        BigDecimal total = BigDecimal.ZERO;
        Map<String, BigDecimal> discountsApplied = new HashMap<>();

        for (Map.Entry<String, Integer> entry : productQuantities.entrySet()) {
            Product product = productRepository.getProduct(entry.getKey());
            BigDecimal quantity = BigDecimal.valueOf(entry.getValue());
            BigDecimal basePrice = product.getUnitCost().multiply(BigDecimal.ONE.add(product.getMarkup()));
            BigDecimal discountAmount = BigDecimal.ZERO;

            if (product.getDiscountPerUnit() != null) {
                // Calculate the total discount
                discountAmount = product.getDiscountPerUnit().multiply(quantity);
                basePrice = basePrice.subtract(product.getDiscountPerUnit());
            }

            BigDecimal totalPrice = basePrice.multiply(quantity);
            total = total.add(totalPrice);
            // Add the discount applied to this product
            discountsApplied.put(entry.getKey(), discountAmount);
        }

        return new OrderCalculationResponse(total, discountsApplied);
    }
    public DiscountDetailsResponse calculateTotalAfterDiscounts(int customerId, BigDecimal totalBeforeDiscounts) {
        Customer customer = customerRepository.getCustomer(customerId);
        BigDecimal basicDiscount = BigDecimal.valueOf(customer.getBasicDiscount());
        BigDecimal totalAfterBasicDiscount = totalBeforeDiscounts.multiply(BigDecimal.ONE.subtract(basicDiscount));

        BigDecimal bulkDiscount = BigDecimal.ZERO;
        if (totalAfterBasicDiscount.compareTo(new BigDecimal("30000")) > 0) {
            bulkDiscount = BigDecimal.valueOf(customer.getBulkDiscountThreshold2());
        } else if (totalAfterBasicDiscount.compareTo(new BigDecimal("10000")) > 0) {
            bulkDiscount = BigDecimal.valueOf(customer.getBulkDiscountThreshold1());
        }

        BigDecimal totalAfterDiscounts = totalAfterBasicDiscount.multiply(BigDecimal.ONE.subtract(bulkDiscount));
        // Calcula el valor absoluto de los descuentos aplicados
        BigDecimal basicDiscountApplied = totalBeforeDiscounts.subtract(totalAfterBasicDiscount);
        BigDecimal bulkDiscountApplied = totalAfterBasicDiscount.subtract(totalAfterBasicDiscount.multiply(BigDecimal.ONE.subtract(bulkDiscount)));

        return new DiscountDetailsResponse(totalAfterDiscounts, basicDiscountApplied, bulkDiscountApplied);
    }

    public void printOrderDetails(OrderCalculationResponse calculationResponse, DiscountDetailsResponse discountDetailsResponse, Map<String, Integer> productQuantities) {
        printProductDetails(productQuantities);
        printTotalDetails(calculationResponse, discountDetailsResponse);
    }

    private void printProductDetails(Map<String, Integer> productQuantities) {
        System.out.println("---------------------------------------------------------------");
        System.out.println("\t\t\t\t Billing Details:");
        System.out.println("---------------------------------------------------------------");
        productQuantities.forEach((productId, quantity) -> {
            Product product = productRepository.getProduct(productId);
            BigDecimal totalPrice = product.getUnitCost().multiply(BigDecimal.ONE.add(product.getMarkup()).multiply(BigDecimal.valueOf(quantity)));
            System.out.printf("%-20s x %2d = %7.2f EUR%n", product.getName(), quantity, totalPrice);
        });
        System.out.println("---------------------------------------------------------------");
    }

    private void printTotalDetails(OrderCalculationResponse calculationResponse, DiscountDetailsResponse discountDetailsResponse) {
        final int width = 30;
        System.out.println(formatLine("Total before discounts: ", calculationResponse.getTotalBeforeDiscounts(), width));
        System.out.println(formatLine("Basic discount applied: ", discountDetailsResponse.getBasicDiscountApplied(), width));
        System.out.println(formatLine("Bulk discount applied: ", discountDetailsResponse.getBulkDiscountApplied(), width));
        System.out.println("---------------------------------------------------------------");
        System.out.println(formatLine("Total after discounts: ", discountDetailsResponse.getTotalAfterDiscounts(), width));
        System.out.println("---------------------------------------------------------------");
    }

    private String formatLine(String label, BigDecimal amount, int width) {
        return String.format("%-" + width + "s%.2f EUR", label, (amount.max(amount)));
    }
}
