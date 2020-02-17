package com.javaguru.shoppinglist.consoleView;

import java.math.BigDecimal;
import java.util.Scanner;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.ProductService;

public class ConsoleUI {

	private ProductService productService;

	public ConsoleUI(ProductService productService) {
		this.productService = productService;
	}


	public void execute() {
		while (true) {
			Scanner scanner = new Scanner(System.in);
			try {
				System.out.println("1. Create product");
				System.out.println("2. Find product by id");
				System.out.println("3. Add discount");
				System.out.println("4. Exit");
				Integer userInput = Integer.valueOf(scanner.nextLine());
				switch (userInput) {
					case 1:
						System.out.println("Enter product name: ");
						String name = scanner.nextLine();
						System.out.println("Enter product price: ");
						BigDecimal price = scanner.nextBigDecimal();
						Product product = new Product();
						product.setName(name);
						product.setPrice(price);
						product.setDiscount(BigDecimal.ZERO);
						productService.createProduct(product);
						System.out.println("Result ID: " + product.getId());
						break;
					case 2:
						System.out.println("Enter product id: ");
						long id = scanner.nextLong();
						Product resultProduct = productService.findByID(id);
						System.out.println("Product Name: = " + resultProduct.getName());
						System.out.println("Product Price: = " + resultProduct.getPrice());
						System.out.println("Product Discount: = " + resultProduct.getDiscount());
						break;
					case 3:
						System.out.println("Enter product id: ");
						Product discountProduct = productService.findByID(scanner.nextLong());
						System.out.println("Enter discount: ");
						BigDecimal discountAmount = scanner.nextBigDecimal();
						discountProduct.setDiscount(discountAmount);
						System.out.println("Product Name: = " + discountProduct.getName());
						System.out.println("Product Price: = " + discountProduct.getPrice());
						productService.calculatePriceAftedDiscount(discountProduct);
						System.out.println("Product Discount: = " + discountProduct.getDiscount());
						System.out.println("Total Price After Discount: = " + discountProduct.getPrice());
						break;
					case 4:
						return;
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
