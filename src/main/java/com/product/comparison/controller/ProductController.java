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

@RestController
public class ProductController {
	
	@Autowired
	private ProductService service;
	  
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
