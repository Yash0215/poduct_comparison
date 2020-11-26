package com.product.comparison.service;


import com.product.comparison.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.product.comparison.repository.ProductRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repo;

	public List<Product> list_products(String name, String category) throws Exception {
		return repo.findByNameAndCategory(name, category);
	}

	public void insert_product(Integer id, String name, String sellerName, String category, String description, Double price) throws Exception {
		Product product = new Product(id, name, sellerName, category, description, price);
		if(!repo.existsById(id)){
			try {
				repo.save(product);
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("Something went wrong.");
			}
		} else {
			throw new IllegalArgumentException("Duplicate product key.");
		}
	}

	public void bulk_insert(MultipartFile file, String dataType) throws Exception {
		try{
			DataSourceParser parser = DataSourceParserFactory.getParser(dataType);
			List<Product> products = parser.parse(file.getInputStream());
			repo.saveAll(products);
		} catch(IOException e){
			e.printStackTrace();
			throw new Exception("Something went wrong.");
		}

	}
}
