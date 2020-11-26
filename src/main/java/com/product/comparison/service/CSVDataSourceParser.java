package com.product.comparison.service;

import com.product.comparison.model.Product;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class CSVDataSourceParser implements DataSourceParser{

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
