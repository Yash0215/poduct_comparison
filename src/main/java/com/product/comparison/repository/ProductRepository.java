package com.product.comparison.repository;

import com.product.comparison.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Interface for creating repository for transacting between "Product" entity and database by extending "JpaRepository".
 * Also contains methods for all the required transaction with service database.
 */

public interface ProductRepository extends JpaRepository<Product, Integer> {

    /**
     * The declared method can be used to fetch list of products from database with given name and category.
     *
     * @param name: Name of the products to be fetched.
     * @param category: Category of the products to be fetched
     * @return return the list of the products with given name and category.
     */
    List<Product> findByNameAndCategory(String name, String category);

}
