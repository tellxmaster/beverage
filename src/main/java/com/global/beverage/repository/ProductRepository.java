package com.global.beverage.repository;

import com.global.beverage.model.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
public class ProductRepository {
    private final Map<String, Product> products = new HashMap<>();

    public ProductRepository() {
        // Initialize in-memory database with Product details
        products.put("A", new Product("A", "Orange Juice",new BigDecimal("0.52"), new BigDecimal("0.8"), null, null));
        products.put("B", new Product("B",  "Pepsi",new BigDecimal("0.38"), new BigDecimal("1.2"), new BigDecimal("0.3"), null));
        products.put("C", new Product("C", "CocaCola",new BigDecimal("0.41"), new BigDecimal("0.9"), null, null));
        products.put("D", new Product("D", "Water",new BigDecimal("0.55"), new BigDecimal("0.8"), null, new BigDecimal("0.9")));

    }

    public Map<String, Product> getAllProducts() {
        return new HashMap<>(products); // Devuelve una copia para evitar modificaciones externas
    }

    public Product getProduct(String productId) {
        return products.get(productId);
    }

}
