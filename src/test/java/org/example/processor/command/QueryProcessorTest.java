package org.example.processor.command;

import org.example.model.Query;
import org.example.processor.parser.Parser;
import org.example.processor.parser.QueryParser;
import org.example.service.TimeLineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QueryProcessorTest {

    private TimeLineService service;
    private Parser<Query> parser;
    private QueryProcessor processor;

    @BeforeEach
    public void setUp() {
        service = mock(TimeLineService.class);
        parser = mock(QueryParser.class);
        processor = new QueryProcessor(parser, service);

    }

    @Test
    void testExecuteSuccessfully() {
        var inputValues = new String[]{"D", "1.1", "8", "P", "01.01.2012", "31.12.2012"};
        var query = new Query("1.1", "8", "P", LocalDate.parse("01.01.2012", DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                LocalDate.parse("31.12.2012", DateTimeFormatter.ofPattern("dd.MM.yyyy")));

        when(parser.parse(inputValues)).thenReturn(query);
        when(service.getAverage(query)).thenReturn("5.0");


        processor.execute(inputValues);


        verify(service, times(1)).getAverage(query);
    }

    @Test
    void testExecuteThrowsException() {
        var inputValues = new String[]{"D", "1.1", "8", "P", "01.01.2012", "31.12.2012"};

        when(parser.parse(inputValues)).thenThrow(new RuntimeException("Parsing error"));

        assertThrows(RuntimeException.class, () -> processor.execute(inputValues));

    }

    @Test
    void testGetType() {
        var expectedType = "D";
        assertEquals(expectedType, processor.getType());
    }
}