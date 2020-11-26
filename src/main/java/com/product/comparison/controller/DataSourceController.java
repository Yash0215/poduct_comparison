package com.product.comparison.controller;

import com.product.comparison.enums.DataType;
import com.product.comparison.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * This class provides endpoints that could be used import the product data from different data sources into the service
 * and ingest into service database.
 *
 */

@RestController
public class DataSourceController {

    @Autowired
    private ProductService service;

    /**
     * This method provides the endpoint to ingest a single products with given details into the service database.
     *
     * @param id: Unique ID of the product
     * @param name: Name of the product
     * @param sellerName: Name of the seller of the product (website/retail shop)
     * @param category: Category of the product
     * @param description: Short description of the product i.e. features, specification etc.
     * @param price: Price of the product
     * @return return "success" message after ingesting the product into service database or the message describing
     *          the error message if some exception occurs.
     */
    @GetMapping(value = "/insert_product")
    public @ResponseBody ResponseEntity<String> insertProduct(@RequestParam(value="product_id", required=true) Integer id,
                                            @RequestParam(value="product_name", required=true) String name,
                                            @RequestParam(value="seller_name", required=true) String sellerName,
                                            @RequestParam(value="category", required=true) String category,
                                            @RequestParam(value="description") String description,
                                            @RequestParam(value="price", required=true) Double price) {
        try {
            service.insertProduct(id, name, sellerName, category, description, price);
            return new ResponseEntity<String>("{\"message\":\"success\"}", HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<String>("{\"message\":\"" + e.getMessage() + "\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This method provides the endpoint to ingest a bulk products given in data source file products with given details
     * into the service database.
     *
     * @param file: data source contains the multiple products along with their details.
     * @param dataType: The type of the data source/file
     * @return returns "success" message after ingesting the products into service database or the message describing
     *     the error message if some exception occurs.
     */
    @PostMapping(value = "/bulk_import")
    public @ResponseBody ResponseEntity<String> bulkImport(@RequestParam(value="data", required=true) MultipartFile file,
                                                            @RequestParam(value="data_type", required=true) DataType dataType) {
        try {
            service.bulkInsert(file, dataType);
            return new ResponseEntity<String>("{\"message\":\"success\"}", HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<String>("{\"message\":\"" + e.getMessage() + "\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
