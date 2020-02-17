package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductValidationServiceTest {
	private ProductValidationService victim;
	private Product input;

	@Mock
	private ProductUniqueNameValidationRule uniqueNameValidationRule;

	@Mock
	private ProductNameValidationRule productNameValidationRule;

	@Mock
	private PriceValidationRule priceValidationRule;

	@Captor
	private ArgumentCaptor<Product> captor;

	@Before
	public void setUp() {
		Set<ProductValidationRule> rules = new HashSet<>();
		rules.add(uniqueNameValidationRule);
		rules.add(productNameValidationRule);
		rules.add(priceValidationRule);

		victim = new ProductValidationService(rules);
		input = product();
	}

	@Test
	public void shouldValidate() {
		victim.validate(input);
		verify(uniqueNameValidationRule).validate(captor.capture());
		verify(priceValidationRule).validate(captor.capture());
		verify(productNameValidationRule).validate(captor.capture());

		List<Product> resultList = captor.getAllValues();
		assertThat(resultList).containsOnly(input);
	}

	private Product product() {
		Product product = new Product();
		product.setId(1L);
		product.setPrice(BigDecimal.valueOf(100));
		product.setDiscount(BigDecimal.valueOf(0));
		product.setDescription("TEST_DESCRIPTION");
		product.setName("TEST_NAME");
		return product;
	}
}