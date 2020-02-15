package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

public class ProductNameValidationRuleTest {

	private ProductNameValidationRule victim = new ProductNameValidationRule();
	private Product input;

	@Rule
	public final ExpectedException expectedException = ExpectedException.none();

	@Test
	public void throwsInnuficientLenghtProductNameValidationRuleException() {
		input = product("12");

		expectedException.expect(ProductValidationException.class);
		expectedException.expectMessage("Name length must be between 3 and 32 characters");

		victim.validate(input);
	}

	@Test
	public void throwsGreaterLenghtProductNameValidationRuleException() {
		input = product("123456789012345678901234567890123");

		expectedException.expect(ProductValidationException.class);
		expectedException.expectMessage("Name length must be between 3 and 32 characters");

		victim.validate(input);
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