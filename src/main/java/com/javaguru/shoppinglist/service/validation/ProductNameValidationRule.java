package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

public class ProductNameValidationRule implements ProductValidationRule {

	@Override
	public void validate(Product product) {
		if (product.getName().length() < 3 || product.getName().length() > 32) {
			throw new ProductValidationException("Name length must be between 3 and 32 characters");
		}
	}
}
