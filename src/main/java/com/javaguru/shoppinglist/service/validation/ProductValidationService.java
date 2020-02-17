package com.javaguru.shoppinglist.service.validation;

import java.util.Set;

import com.javaguru.shoppinglist.domain.Product;

public class ProductValidationService {

	private Set<ProductValidationRule> validationRules;

	public ProductValidationService(Set<ProductValidationRule> validationRules) {
		this.validationRules = validationRules;
	}

	public void validate(Product product) {
		validationRules.forEach(s -> s.validate(product));
	}

	public void validateDiscount(Product product) {
		new DiscountValidationRule().validate(product);
	}
}
