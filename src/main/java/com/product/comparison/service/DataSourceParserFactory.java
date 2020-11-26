package com.product.comparison.service;

import com.product.comparison.enums.DataType;
import com.product.comparison.exception.UnsupportedDataType;

public class DataSourceParserFactory {

    public static DataSourceParser getParser(DataType dataType) throws UnsupportedDataType {
        if(dataType.equals(DataType.CSV)) {
            return new CSVDataSourceParser();
        } else if (dataType.equals(DataType.JSON)){
            throw new UnsupportedDataType();
        } else if (dataType.equals(DataType.XML)){
            throw new UnsupportedDataType();
        } else {
            throw new UnsupportedDataType();
        }
    }

}
