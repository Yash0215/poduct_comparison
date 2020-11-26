package com.product.comparison;

import com.product.comparison.controller.DataSourceController;
import com.product.comparison.controller.ProductController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AppTests {

	@Autowired
	private ProductController productController;

	@Autowired
	private DataSourceController dataSourceController;

	@Test
	void contextLoads() {
		// Run Test
		Assertions.assertNotNull(productController);
		Assertions.assertNotNull(dataSourceController);
	}

}
