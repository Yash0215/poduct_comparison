package com.product.comparison.service;

import com.product.comparison.exception.UnsupportedDataType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DataSourceParserFactoryTest {

    @Test
    void testGetParser() throws Exception {
        // Run the test
        final DataSourceParser result = DataSourceParserFactory.getParser("CSV");

        // Verify the results
        assertEquals(result.getClass(), new CSVDataSourceParser().getClass());
    }

    @Test
    void testGetParserSmallCaseDataType() throws Exception {
        // Run the test
        final DataSourceParser result = DataSourceParserFactory.getParser("csv");

        // Verify the results
        assertEquals(result.getClass(), new CSVDataSourceParser().getClass());
    }

    @Test
    void testGetParser_ThrowsUnsupportedDataType() {
        // Run the test
        assertThrows(UnsupportedDataType.class, () -> DataSourceParserFactory.getParser("EXCEL"));
    }
}
