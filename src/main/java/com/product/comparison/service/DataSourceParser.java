package com.product.comparison.service;

import com.product.comparison.model.Product;

import java.io.InputStream;
import java.util.List;

public interface DataSourceParser {

    public List<Product> parse(InputStream data);
}
