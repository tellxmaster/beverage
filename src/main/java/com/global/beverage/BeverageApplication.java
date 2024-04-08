package com.global.beverage;

import com.global.beverage.model.Customer;
import com.global.beverage.repository.CustomerRepository;
import com.global.beverage.repository.ProductRepository;
import com.global.beverage.response.DiscountDetailsResponse;
import com.global.beverage.response.OrderCalculationResponse;
import com.global.beverage.service.CustomerService;
import com.global.beverage.service.OrderService;
import com.global.beverage.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class BeverageApplication implements CommandLineRunner {
	@Autowired
	private OrderService orderService;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProductService productService;
	@Autowired
	private CustomerService customerService;

	public static void main(String[] args) {
		SpringApplication.run(BeverageApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			boolean stillBuying = true;
			Map<String, Integer> productQuantities = new HashMap<>();

			System.out.println("===============================================================");
			System.out.println("\t\t\t\t  Beverage Machine Calculator");
			System.out.println("===============================================================");
			Customer customer = customerService.inputCustomer(reader);
			customerService.printDiscountsByCustomer(customer);

			while(stillBuying) {
				// Prints product List
				productService.printProducts(productRepository);
				// Read and validate Product Input
				String productId = productService.inputProductId(reader);
				// Read and validate Product Quantity
				int quantity = productService.inputProductQuantity(reader, productId);
				// Append Product and Quantity
				productQuantities.put(productId, productQuantities.getOrDefault(productId, 0) + quantity);

				System.out.print("-> ¿Do you want to continue buying [Y/N]?: ");
				String answer = reader.readLine();

				if(answer.equalsIgnoreCase("N")) {
					stillBuying = false;
				}
			}

			// Calculate Billing
			OrderCalculationResponse calculationResponse = orderService.calculateTotalBeforeDiscounts(productQuantities);
			DiscountDetailsResponse discountDetailsResponse = orderService.calculateTotalAfterDiscounts(customer.getCustomerId(), calculationResponse.getTotalBeforeDiscounts());

			orderService.printOrderDetails(calculationResponse, discountDetailsResponse, productQuantities);

		} catch (Exception e) {
			System.err.println("An error occurred while processing the entry: " + e.getMessage());
			e.printStackTrace();
		}
	}






}
