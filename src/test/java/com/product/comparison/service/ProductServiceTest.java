package com.product.comparison.service;

import com.product.comparison.enums.DataType;
import com.product.comparison.exception.UnsupportedDataType;
import com.product.comparison.model.Product;
import com.product.comparison.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        assertEquals(products.size(), result.size());
        assertEquals(products.get(0), result.get(0));

    }

    @Test
    void testList_products_ThrowsException() {
        // Setup

        // Configure ProductRepository.findByNameAndCategory(...).
        final List<Product> products = Arrays.asList(new Product(0, "name", "sellerName", "category", "description", 0.0));
        when(mockRepo.findByNameAndCategory("name", "category")).thenThrow(RuntimeException.class);

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
        productServiceUnderTest.insertProduct(0, "name", "sellerName", "category", "description", 0.0);

        // Verify the results
    }

    @Test
    void testInsert_product_ThrowsException() {
        // Setup
        when(mockRepo.existsById(0)).thenReturn(false);

        // Configure ProductRepository.save(...).
        final Product product = new Product(0, "name", "sellerName", "category", "description", 0.0);
        when(mockRepo.save(any(Product.class))).thenThrow(RuntimeException.class);

        // Run the test
        assertThrows(Exception.class,
                () -> productServiceUnderTest.insertProduct(0, "name", "sellerName", "category", "description", 0.0));
    }

    @Test
    void testBulk_insert() throws Exception {
        // Setup
        Path path = Paths.get("test.csv");
        String name = "test.csv";
        String originalFileName = "test.csv";
        String contentType = "text/csv";
        byte[] content = null;
        try {
            content = Files.readAllBytes(path);
        } catch (final IOException e) {
            e.printStackTrace();
        }
        final MultipartFile file = new MockMultipartFile(name,
                originalFileName, contentType, content);

        // Configure ProductRepository.saveAll(...).
        final List<Product> products = Arrays.asList(new Product(0, "name", "sellerName", "category", "description", 0.0));
        when(mockRepo.saveAll(Arrays.asList(new Product(0, "name", "sellerName", "category", "description", 0.0)))).thenReturn(products);

        // Run the test
        productServiceUnderTest.bulkInsert(file, DataType.CSV);

    }

    @Test
    void testBulk_insert_ThrowsUnsupportedDataType() {
        // Setup
        final MultipartFile file = null;

        // Configure ProductRepository.saveAll(...).
        final List<Product> products = Arrays.asList(new Product(0, "name", "sellerName", "category", "description", 0.0));
        when(mockRepo.saveAll(Arrays.asList(new Product(0, "name", "sellerName", "category", "description", 0.0)))).thenReturn(products);

        // Run the test
        assertThrows(UnsupportedDataType.class, () -> productServiceUnderTest.bulkInsert(file, DataType.XML));
    }

}
