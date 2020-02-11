package com.javaguru.shoppinglist.service.validation;

import java.util.HashSet;
import java.util.Set;

import com.javaguru.shoppinglist.domain.Product;

public class ProductValidationService {

	private Set<ProductValidationRule> validationRules = new HashSet<>();

	public ProductValidationService() {
		validationRules.add(new PriceValidationRule());
		validationRules.add(new ProductNameValidationRule());
	}

	public void validate(Product product) {
		validationRules.forEach(s -> s.validate(product));
	}

	public void validateDiscount(Product product) {
		new DiscountValidationRule().validate(product);
	}
}
