package com.product.comparison.controller;

import com.product.comparison.model.Product;
import com.product.comparison.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This class provides endpoints that could be used fetch the products list/product from the service database.
 */

@RestController
public class ProductController {
	
	@Autowired
	private ProductService service;

	/**
	 * This method provides the endpoint to get the list of the products with given name and category.
	 *
	 * @param name: Name of the products to be fetched.
	 * @param category: Category of the products to be fetched
	 * @return returns the list of the products with given name and category or null if some exception occurred.
	 */
	@GetMapping(value = "/list")
	public @ResponseBody ResponseEntity<List<Product>> imageMeta(@RequestParam(value="name", required=true) String name,
																 @RequestParam(value="category", required=true) String category) {
		try {
			return ResponseEntity.ok(service.list_products(name, category));
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
