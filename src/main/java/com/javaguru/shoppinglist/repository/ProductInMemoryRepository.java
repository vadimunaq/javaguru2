package com.javaguru.shoppinglist.repository;

import java.util.HashMap;
import java.util.Map;

import com.javaguru.shoppinglist.domain.Product;

public class ProductInMemoryRepository {

	private Map<Long, Product> productRepository = new HashMap<>();
	private Long productIdSequence = 0L;

	public Product insert(Product product) {
		product.setId(productIdSequence);
		productRepository.put(productIdSequence, product);
		productIdSequence++;
		return product;
	}

	public Product findProductByID(Long id) {
		return productRepository.get(id);
	}
}
