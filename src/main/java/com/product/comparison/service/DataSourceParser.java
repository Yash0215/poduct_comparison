package com.product.comparison.service;

import com.product.comparison.model.Product;

import java.io.InputStream;
import java.util.List;

/**
 * This Interface declares the required method to be implemented by the parser class that will be used to parse the
 * data source file.
 */
public interface DataSourceParser {

    /**
     * The declared method should accept the data as InputStream and provide the list of the products.
     *
     * @param data: the InputStream contains the products and their details.
     * @return: the list of the valid products the data contains.
     */
    public List<Product> parse(InputStream data);
}
