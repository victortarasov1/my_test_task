package org.example.processor.parser;

import org.example.model.Query;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class QueryParserTest {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private final QueryParser parser = new QueryParser(formatter);

    @Test
    void testParseValidQueryWithSingleDate() {
        var inputValues = new String[]{"command", "service1", "questionType1", "responseType1", "01.01.2020"};

        var query = parser.parse(inputValues);

        assertEquals("service1", query.serviceId());
        assertEquals("questionType1", query.questionTypeId());
        assertEquals("responseType1", query.responseType());
        assertEquals(LocalDate.of(2020, 1, 1), query.dateFrom());
        assertNull(query.dateTo());
    }

    @Test
    void testParseValidQueryWithDateRange() {
        var inputValues = new String[]{"command", "service2", "questionType2", "responseType2", "01.01.2020-31.12.2020"};

        var query = parser.parse(inputValues);

        assertEquals("service2", query.serviceId());
        assertEquals("questionType2", query.questionTypeId());
        assertEquals("responseType2", query.responseType());
        assertEquals(LocalDate.of(2020, 1, 1), query.dateFrom());
        assertEquals(LocalDate.of(2020, 12, 31), query.dateTo());
    }

    @Test
    void testParseInvalidInputLength() {
        var inputValues = new String[]{"command", "service1", "questionType1"};

        var exception = assertThrows(RuntimeException.class, () -> parser.parse(inputValues));
        assertEquals("Invalid number of input values", exception.getMessage());
    }

    @Test
    void testParseInvalidDateFormat() {
        var inputValues = new String[]{"command", "service1", "questionType1", "responseType1", "01-01-2020"};

        var exception = assertThrows(RuntimeException.class, () -> parser.parse(inputValues));
        assertEquals("Text '01' could not be parsed at index 2", exception.getMessage());
    }

    @Test
    void testParseInvalidDateRangeFormat() {
        var inputValues = new String[]{"command", "service1", "questionType1", "responseType1", "01.01.2020-31-12-2020"};

        var exception = assertThrows(RuntimeException.class, () -> parser.parse(inputValues));
        assertEquals("Text '31' could not be parsed at index 2", exception.getMessage());
    }
}