package com.javaguru.shoppinglist.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductInMemoryRepository;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;

public class ProductService {
	private ProductInMemoryRepository repository = new ProductInMemoryRepository();
	private ProductValidationService validationService = new ProductValidationService();

	public Long createProduct(Product product) {
		validationService.validate(product);
		Product createdProduct = repository.insert(product);
		return createdProduct.getId();
	}

	public Product findByID(Long id) {
		return Optional.of(repository.findProductByID(id)).get();
	}

	public void calculatePriceAftedDiscount(Product product) {
		validationService.validateDiscount(product);
		BigDecimal discountAmount = product.getPrice().multiply(product.getDiscount().divide(BigDecimal.valueOf(100)));
		discountAmount = discountAmount.setScale(2, RoundingMode.HALF_EVEN);
		BigDecimal priceAfterDiscount = product.getPrice().subtract(discountAmount);
		product.setPrice(priceAfterDiscount);
	}
}
