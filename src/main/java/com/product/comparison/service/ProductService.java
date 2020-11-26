package com.product.comparison.service;


import com.product.comparison.enums.DataType;
import com.product.comparison.exception.UnsupportedDataType;
import com.product.comparison.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.product.comparison.repository.ProductRepository;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repo;

	/**
	 * The method fetches the list of the products for given name and category from the repository and returns.
	 *
	 * @param name: Name of the products to be fetched.
	 * @param category: Category of the products to be fetched
	 * @return returns the list of the products with given name and category.
	 * @throws Exception throws exception if some exception occurred
	 */
	public List<Product> list_products(String name, String category) throws Exception {
		return repo.findByNameAndCategory(name, category);
	}

	/**
	 * The method inserts the product with given details into the database only if the product with the same product ID
	 * dost not already exists in the database.
	 *
	 * @param id: Unique ID of the product
	 * @param name: Name of the product
	 * @param sellerName: Name of the seller of the product (website/retail shop)
	 * @param category: Category of the product
	 * @param description: Short description of the product i.e. features, specification etc.
	 * @param price: Price of the product
	 * @throws Exception throws exception if the product ID is already exist of some other exception occured during the
	 * 					insertion.
	 */
	public void insertProduct(Integer id, String name, String sellerName, String category, String description, Double price) throws Exception {
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

	/**
	 * The method inserts the product with given details into the database only for supported data sources. First gets
	 * the product list by parsing the data source files with the suitable implementation of the parser for given data
	 * types then persists the products list in the product repository.
	 *
	 * @param file: data source contains the multiple products along with their details.
	 * @param dataType: The type of the data source/file
	 * @throws Exception throws Exception if data type is not supported in current implementation of if some other
	 * 						exception occurred during the parsing of the data source file or bulk insertion.
	 */
	public void bulkInsert(MultipartFile file, DataType dataType) throws Exception {
		try{
			DataSourceParser parser = DataSourceParserFactory.getParser(dataType);
			List<Product> products = parser.parse(file.getInputStream());
			if(!products.isEmpty()){
				repo.saveAll(products);
			}
		} catch(UnsupportedDataType e){
			throw new UnsupportedDataType();
		} catch(Exception e){
			e.printStackTrace();
			throw new Exception("Something went wrong.");
		}
	}
}
