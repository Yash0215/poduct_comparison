package com.product.comparison.repository;

import com.product.comparison.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByNameAndCategory(String name, String category);

}
