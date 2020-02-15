package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

public class PriceValidationRuleTest {

	private PriceValidationRule victim = new PriceValidationRule();
	private Product input;

	@Rule
	public final ExpectedException expectedException = ExpectedException.none();

	@Test
	public void throwsPriceValidationRuleException() {
		input = product(BigDecimal.valueOf(-100));

		expectedException.expect(ProductValidationException.class);
		expectedException.expectMessage("Product price must be greater than 0");

		victim.validate(input);
	}

	@Test
	public void shouldValidateSuccess() {
		input = product(BigDecimal.valueOf(10));

		victim.validate(input);
	}

	private Product product(BigDecimal price) {
		Product product = new Product();
		product.setPrice(price);
		return product;
	}
}