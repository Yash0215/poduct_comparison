package com.product.comparison.exception;

public class UnsupportedDataType extends Exception {

    public UnsupportedDataType() {
        super("Data source type is not supported.");
    }
}
