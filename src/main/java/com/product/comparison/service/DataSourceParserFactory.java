package com.product.comparison.service;

import com.product.comparison.exception.UnsupportedDataType;

public class DataSourceParserFactory {

    public static DataSourceParser getParser(String dataType) throws UnsupportedDataType {
        if(dataType.equalsIgnoreCase("CSV")) {
            return new CSVDataSourceParser();
        } else if (dataType.equalsIgnoreCase("XML")){
            throw new UnsupportedDataType();
        } else {
            throw new UnsupportedDataType();
        }
    }

}
