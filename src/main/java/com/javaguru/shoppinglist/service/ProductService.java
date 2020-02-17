package com.javaguru.shoppinglist.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductInMemoryRepository;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;

public class ProductService {
	private ProductInMemoryRepository repository;
	private ProductValidationService validationService;

	public ProductService(ProductInMemoryRepository repository, ProductValidationService validationService) {
		this.repository = repository;
		this.validationService = validationService;
	}

	public Long createProduct(Product product) {
		validationService.validate(product);
		Product createdProduct = repository.insert(product);
		return createdProduct.getId();
	}

	public Product findByID(Long id) {
		return repository.findProductByID(id);
	}

	public void calculatePriceAftedDiscount(Product product) {
		validationService.validateDiscount(product);
		BigDecimal discountAmount = product.getPrice().multiply(product.getDiscount().divide(BigDecimal.valueOf(100)));
		discountAmount = discountAmount.setScale(2, RoundingMode.HALF_EVEN);
		BigDecimal priceAfterDiscount = product.getPrice().subtract(discountAmount);
		product.setPrice(priceAfterDiscount);
	}
}
