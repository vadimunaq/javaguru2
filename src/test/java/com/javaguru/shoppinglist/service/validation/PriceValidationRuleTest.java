package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PriceValidationRuleTest {

	private PriceValidationRule victim = new PriceValidationRule();
	private Product input;

	@Rule
	public final ExpectedException expectedException = ExpectedException.none();

	@Test
	public void throwsPriceValidationRuleException() {
		input = product(BigDecimal.valueOf(-100));

		assertThatThrownBy(() -> victim.validate(input))
				.isInstanceOf(ProductValidationException.class)
				.hasMessage("Product price must be greater than 0");
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