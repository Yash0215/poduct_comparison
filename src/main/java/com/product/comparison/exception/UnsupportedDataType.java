package com.product.comparison.exception;

/**
 * Class defines a user-defined exception for unsupported data type exceptions.
 */
public class UnsupportedDataType extends Exception {

    public UnsupportedDataType() {
        super("Data source type is not supported.");
    }
}
