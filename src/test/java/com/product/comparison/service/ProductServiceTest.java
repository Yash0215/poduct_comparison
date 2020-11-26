package com.product.comparison.service;

import com.product.comparison.exception.UnsupportedDataType;
import com.product.comparison.model.Product;
import com.product.comparison.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class ProductServiceTest {

    @Mock
    private ProductRepository mockRepo;

    @InjectMocks
    private ProductService productServiceUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void testList_products() throws Exception {
        // Setup

        // Configure ProductRepository.findByNameAndCategory(...).
        final List<Product> products = Arrays.asList(new Product(0, "name", "sellerName", "category", "description", 0.0));
        when(mockRepo.findByNameAndCategory("name", "category")).thenReturn(products);

        // Run the test
        final List<Product> result = productServiceUnderTest.list_products("name", "category");

        // Verify the results
        assertEquals();
    }

    @Test
    void testList_products_ThrowsException() {
        // Setup

        // Configure ProductRepository.findByNameAndCategory(...).
        final List<Product> products = Arrays.asList(new Product(0, "name", "sellerName", "category", "description", 0.0));
        when(mockRepo.findByNameAndCategory("name", "category")).thenReturn(products);

        // Run the test
        assertThrows(Exception.class, () -> productServiceUnderTest.list_products("name", "category"));
    }

    @Test
    void testInsert_product() throws Exception {
        // Setup
        when(mockRepo.existsById(0)).thenReturn(false);

        // Configure ProductRepository.save(...).
        final Product product = new Product(0, "name", "sellerName", "category", "description", 0.0);
        when(mockRepo.save(any(Product.class))).thenReturn(product);

        // Run the test
        productServiceUnderTest.insert_product(0, "name", "sellerName", "category", "description", 0.0);

        // Verify the results
    }

    @Test
    void testInsert_product_ThrowsException() {
        // Setup
        when(mockRepo.existsById(0)).thenReturn(false);

        // Configure ProductRepository.save(...).
        final Product product = new Product(0, "name", "sellerName", "category", "description", 0.0);
        when(mockRepo.save(any(Product.class))).thenReturn(product);

        // Run the test
        assertThrows(Exception.class, () -> productServiceUnderTest.insert_product(0, "name", "sellerName", "category", "description", 0.0));
    }

    @Test
    void testBulk_insert() throws Exception {
        // Setup
        final MultipartFile file = null;

        // Configure ProductRepository.saveAll(...).
        final List<Product> products = Arrays.asList(new Product(0, "name", "sellerName", "category", "description", 0.0));
        when(mockRepo.saveAll(Arrays.asList(new Product(0, "name", "sellerName", "category", "description", 0.0)))).thenReturn(products);

        // Run the test
        productServiceUnderTest.bulk_insert(file, "dataType");

        // Verify the results
    }

    @Test
    void testBulk_insert_ThrowsUnsupportedDataType() {
        // Setup
        final MultipartFile file = null;

        // Configure ProductRepository.saveAll(...).
        final List<Product> products = Arrays.asList(new Product(0, "name", "sellerName", "category", "description", 0.0));
        when(mockRepo.saveAll(Arrays.asList(new Product(0, "name", "sellerName", "category", "description", 0.0)))).thenReturn(products);

        // Run the test
        assertThrows(UnsupportedDataType.class, () -> productServiceUnderTest.bulk_insert(file, "dataType"));
    }

    @Test
    void testBulk_insert_ThrowsIOException() {
        // Setup
        final MultipartFile file = null;

        // Configure ProductRepository.saveAll(...).
        final List<Product> products = Arrays.asList(new Product(0, "name", "sellerName", "category", "description", 0.0));
        when(mockRepo.saveAll(Arrays.asList(new Product(0, "name", "sellerName", "category", "description", 0.0)))).thenReturn(products);

        // Run the test
        assertThrows(IOException.class, () -> productServiceUnderTest.bulk_insert(file, "dataType"));
    }
}
