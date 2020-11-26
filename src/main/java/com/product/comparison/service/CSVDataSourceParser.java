package com.product.comparison.service;

import com.product.comparison.model.Product;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class provide the implementation for parsing the "CSV" type of the data source.
 */
public class CSVDataSourceParser implements DataSourceParser {

    /**
     * the overridden method parse the data contained inside the "csv' data source file. It takes InputStream of the
     * file as input then reads the lines one by one using "BufferReader", splits the lines with delimiter "," then
     * extract the product info from the respective indexes and creates the list of products and returns.
     *
     * @param data: the InputStream contains the products and their details.
     * @return returns the list of valid products.
     */
    @Override
    public List<Product> parse(InputStream data) {
        return new BufferedReader(new InputStreamReader(data)).lines().skip(1).map(line -> {
            try {
                String[] values = line.split(",");
                Integer id = Integer.parseInt(values[0]);
                String name = values[1];
                String seller_name = values[2];
                String category = values[3];
                String description = values[4];
                Double price = Double.parseDouble(values[5]);
                return new Product(id, name, seller_name, category, description, price);
            } catch (Exception e) {
                System.out.println("Error parsing line: " + line);
                return null;
            }
        }).filter(product -> product != null).collect(Collectors.toList());
    }
}
