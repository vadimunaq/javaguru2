package com.javaguru.shoppinglist.service.validation;

import java.math.BigDecimal;

import com.javaguru.shoppinglist.domain.Product;

public class DiscountValidationRule implements ProductValidationRule {

	private static final BigDecimal MINIMAL_PRICE_FOR_DISCOUNT = BigDecimal.valueOf(20);
	private static final BigDecimal ZERO_DISCOUT = BigDecimal.valueOf(0);

	@Override
	public void validate(Product product) {
		if (product.getDiscount().compareTo(BigDecimal.ZERO) < 0 ||
				product.getDiscount().compareTo(BigDecimal.valueOf(100)) > 0){
			product.setDiscount(ZERO_DISCOUT);
			throw new ProductValidationException("The discount must be between 0 and 100");
		} else if (product.getPrice().compareTo(MINIMAL_PRICE_FOR_DISCOUNT) < 0) {
			product.setDiscount(ZERO_DISCOUT);
			throw new ProductValidationException("Product price must be at least 20 to add a discount");
		}
	}
}
