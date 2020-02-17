package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DiscountValidationRuleTest {

	private DiscountValidationRule victim = new DiscountValidationRule();
	private Product input;

	@Rule
	public final ExpectedException expectedException = ExpectedException.none();

	@Test
	public void throwsDiscountValidationExceptionForSmallerDiscount() {
		input = product(BigDecimal.valueOf(-1), BigDecimal.valueOf(10));

		assertThatThrownBy(() -> victim.validate(input))
				.isInstanceOf(ProductValidationException.class)
				.hasMessage("The discount must be between 0 and 100");
	}

	@Test
	public void throwsDiscountValidationExceptionForGreaterDiscount() {
		input = product(BigDecimal.valueOf(1000), BigDecimal.valueOf(10));

		assertThatThrownBy(() -> victim.validate(input))
				.isInstanceOf(ProductValidationException.class)
				.hasMessage("The discount must be between 0 and 100");
	}

	@Test
	public void throwsDiscountValidationExceptionForPrice() {
		input = product(BigDecimal.valueOf(10), BigDecimal.valueOf(10));

		assertThatThrownBy(() -> victim.validate(input))
				.isInstanceOf(ProductValidationException.class)
				.hasMessage("Product price must be at least 20 to add a discount");
	}

	@Test
	public void shouldValidateSuccess() {
		input = product(BigDecimal.valueOf(50), BigDecimal.valueOf(20));
		victim.validate(input);
	}

	private Product product(BigDecimal discount, BigDecimal price) {
		Product product = new Product();
		product.setDiscount(discount);
		product.setPrice(price);
		return product;
	}
}