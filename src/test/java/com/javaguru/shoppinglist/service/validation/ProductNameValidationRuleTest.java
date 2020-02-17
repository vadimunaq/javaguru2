package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ProductNameValidationRuleTest {

	private ProductNameValidationRule victim = new ProductNameValidationRule();
	private Product input;

	@Rule
	public final ExpectedException expectedException = ExpectedException.none();

	@Test
	public void throwsLenghtProductNameValidationRuleException() {
		input = product("123456789012345678901234567890123");

		assertThatThrownBy(() -> victim.validate(input))
				.isInstanceOf(ProductValidationException.class)
				.hasMessage("Name length must be between 3 and 32 characters");

		input = product("12");

		assertThatThrownBy(() -> victim.validate(input))
				.isInstanceOf(ProductValidationException.class)
				.hasMessage("Name length must be between 3 and 32 characters");
	}

	@Test
	public void shouldValidateSuccess() {
		input = product("Test_name");
		victim.validate(input);
	}

	private Product product(String name) {
		Product product = new Product();
		product.setName(name);
		return product;
	}

}