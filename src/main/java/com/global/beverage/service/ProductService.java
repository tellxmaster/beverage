package com.global.beverage.service;

import com.global.beverage.model.Product;
import com.global.beverage.repository.CustomerRepository;
import com.global.beverage.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    public void printProducts(ProductRepository repository) {
        final int nameWidth = 20;
        final String productFormat = "\t [%s] %-"+nameWidth+"s %6.3f EUR%n";

        System.out.println("---------------------------------------------------------------");
        repository.getAllProducts().forEach((id, product) -> {
            BigDecimal basePrice = product.getUnitCost();
            BigDecimal markup = product.getMarkup();
            BigDecimal discountPerUnit = product.getDiscountPerUnit();
            BigDecimal realPrice = basePrice.multiply(BigDecimal.ONE.add(markup));

            // If there is a unit discount, subtract that from the actual price.
            if (discountPerUnit != null) {
                realPrice = realPrice.subtract(discountPerUnit);
            }

            // Validate the price is not less than the cost
            realPrice = realPrice.max(basePrice);

            System.out.printf(productFormat, id, product.getName(), realPrice);
        });
        System.out.println("---------------------------------------------------------------");
    }

    public String inputProductId(BufferedReader reader) throws IOException {
        String productId;
        while (true) {
            System.out.print("-> Enter the [id] of the product you will purchase (e.g. A): ");
            productId = reader.readLine().toUpperCase();
            Product product = productRepository.getProduct(productId); // Assuming getProduct returns null if not found
            if (product != null) {
                return productId;
            } else {
                System.out.println("Product with ID [" + productId + "] does not exist. Please try again.");
            }
        }
    }

    public int inputProductQuantity(BufferedReader reader, String productId) throws IOException {
        int quantity;
        while (true) {
            System.out.print("-> Enter the quantity of [" + productId + "]: ");
            try {
                quantity = Integer.parseInt(reader.readLine());
                if (quantity > 0) {
                    return quantity;
                } else {
                    System.out.println("The quantity must be positive. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for the quantity.");
            }
        }
    }
}
