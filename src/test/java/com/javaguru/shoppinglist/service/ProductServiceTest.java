package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductInMemoryRepository;
import com.javaguru.shoppinglist.service.validation.ProductValidationRule;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

	@Mock
	private ProductInMemoryRepository repository;

	@Mock
	private ProductValidationService validationService;

	@Captor
	private ArgumentCaptor<Product> productCaptor;

	@InjectMocks
	private ProductService victim;

	@Test
	public void shouldCreateProduct() {
		Product product = product();
		when(repository.insert(product)).thenReturn(product);

		Long resultID = victim.createProduct(product);

		verify(validationService).validate(productCaptor.capture());
		Product captorResult = productCaptor.getValue();

		assertThat(captorResult).isEqualTo(product);
		assertThat(product.getId()).isEqualTo(resultID);
	}

	@Test
	public void shouldFindByID() {
		Product product = product();
		when(repository.findProductByID(1L)).thenReturn(product);
		Product result = victim.findByID(1L);
		assertEquals(product, result);
	}

	@Test
	public void shouldCalculatePriceAftedDiscount() {
		Product product = product();
		victim.calculatePriceAftedDiscount(product);

		BigDecimal actualPrice = product.getPrice();
		BigDecimal expectedPrice = BigDecimal.valueOf(90).setScale(2, RoundingMode.HALF_EVEN);

		assertEquals(actualPrice, expectedPrice);
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