package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ProductInMemoryRepositoryTest {

	private static final String PRODUCT_NAME = "PRODUCT_NAME";
	private static final String PRODUCT_DESCRIPTION = "PRODUCT_DESCRIPTION";
	private static final long PRODUCT_ID = 0L;

	private ProductInMemoryRepository victim;
	private Product product;

	@Before
	public void setUp() throws Exception {

		victim = new ProductInMemoryRepository();
		product = product();
	}

	@Test
	public void shouldInsert() {
		Product result = victim.insert(product);

		assertThat(result).isEqualTo(expectedProduct());
	}

	@Test
	public void shouldFindById() {
		victim.insert(product);

		Optional<Product> result = victim.findProductByID(PRODUCT_ID);
		assertThat(result).isNotEmpty();
		assertThat(result).hasValue(expectedProduct());
	}

	@Test
	public void isNameExistTest() {
		victim.insert(product);

		boolean result = victim.isNameExist(PRODUCT_NAME);
		assertThat(result).isTrue();
	}

	private Product expectedProduct() {
		Product task = new Product();
		task.setName(PRODUCT_NAME);
		task.setDescription(PRODUCT_DESCRIPTION);
		task.setId(PRODUCT_ID);
		return task;
	}

	private Product product() {
		Product task = new Product();
		task.setName(PRODUCT_NAME);
		task.setDescription(PRODUCT_DESCRIPTION);
		return task;
	}
}