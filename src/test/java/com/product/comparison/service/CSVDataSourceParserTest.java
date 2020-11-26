package com.product.comparison.service;

import com.product.comparison.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CSVDataSourceParserTest {

    private CSVDataSourceParser csvDataSourceParserUnderTest;

    @BeforeEach
    void setUp() {
        csvDataSourceParserUnderTest = new CSVDataSourceParser();
    }

    @Test
    void testParse() {
        // Setup
        Path path = Paths.get("src/test/resources/test.csv");
        byte[] content = null;
        try {
            content = Files.readAllBytes(path);
        } catch (final IOException e) {
            e.printStackTrace();
        }

        final InputStream data = new ByteArrayInputStream(content);
        final List<Product> expectedResult = Arrays.asList(
                new Product(101, "p1", "s1", "category1", "desc2", 200.10),
                new Product(102, "p2", "s1", "category2", "", 300.10),
                new Product(104, "p4", "s2", "category2", "", 500.10));

        // Run the test
        final List<Product> result = csvDataSourceParserUnderTest.parse(data);

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
