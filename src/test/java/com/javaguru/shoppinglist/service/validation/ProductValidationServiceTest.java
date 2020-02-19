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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

public class ProductValidationServiceTest {

	@Mock
	private ProductUniqueNameValidationRule uniqueNameValidationRule;

	@Mock
	private ProductNameValidationRule productNameValidationRule;

	@Mock
	private PriceValidationRule priceValidationRule;

	@Captor
	private ArgumentCaptor<Product> captor;

	private Product input;
	private ProductValidationService victim;

	@Before
	public void setUp() {
		input = product();
		Set<ProductValidationRule> rules = new HashSet<>();
		rules.add(uniqueNameValidationRule);
		rules.add(productNameValidationRule);
		rules.add(priceValidationRule);

		victim = new ProductValidationService(rules);
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
		product.setPrice(BigDecimal.valueOf(100));
		product.setDiscount(BigDecimal.valueOf(10));
		product.setName("TEST_NAME");
		product.setDescription("TEST_DESCRIPTION");
		product.setId(1L);
		return product;
	}
}