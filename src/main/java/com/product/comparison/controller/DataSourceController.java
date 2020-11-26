package com.product.comparison.controller;

import com.product.comparison.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class DataSourceController {

    @Autowired
    private ProductService service;

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

    @PostMapping(value = "/bulk_import")
    public @ResponseBody ResponseEntity<String> bulkImport(@RequestParam(value="data", required=true) MultipartFile file,
                                                            @RequestParam(value="data_type", required=true) String dataType) {
        try {
            service.bulkInsert(file, dataType);
            return new ResponseEntity<String>("{\"message\":\"success\"}", HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<String>("{\"message\":\"" + e.getMessage() + "\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
