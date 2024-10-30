package org.example.processor.parser;

import org.example.model.Query;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class QueryParser implements Parser<Query> {
    private final DateTimeFormatter formatter;

    public QueryParser(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public Query parse(String[] inputValues) {
        if (inputValues.length != 5)
            throw new RuntimeException("Invalid number of input values");
        var serviceId = inputValues[1];
        var questionTypeId = inputValues[2];
        var responseType = inputValues[3];
        if (inputValues[4].contains("-"))
            return createQueryWithDateRange(inputValues, serviceId, questionTypeId, responseType);
        return new Query(serviceId, questionTypeId, responseType, LocalDate.parse(inputValues[4], formatter), null);

    }

    private Query createQueryWithDateRange(String[] inputValues, String serviceId, String questionTypeId, String responseType) {
        var dateRange = inputValues[4].split("-");
        var dateFrom = LocalDate.parse(dateRange[0], formatter);
        var dateTo = LocalDate.parse(dateRange[1], formatter);
        return new Query(serviceId, questionTypeId, responseType, dateFrom, dateTo);
    }

}
