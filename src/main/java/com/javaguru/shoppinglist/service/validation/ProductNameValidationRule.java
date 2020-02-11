package com.javaguru.shoppinglist.service.validation;

import java.util.HashSet;
import java.util.Set;

import com.javaguru.shoppinglist.domain.Product;

public class ProductNameValidationRule implements ProductValidationRule {
	private static Set<String> assignedNames = new HashSet<String>();

	@Override
	public void validate(Product product) {
		if (product.getName().length() < 3 || product.getName().length() > 32) {
			throw new ProductValidationException("Name length must be between 3 and 32 characters");
		} else if (assignedNames.contains(product.getName())) {
			throw new ProductValidationException("Product name should be unique");
		} else {
			assignedNames.add(product.getName());
		}
	}
}
