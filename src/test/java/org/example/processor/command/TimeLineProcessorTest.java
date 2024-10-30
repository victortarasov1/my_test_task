package org.example.processor.command;

import org.example.model.WaitingTimeline;
import org.example.processor.parser.Parser;
import org.example.repository.TimeLineRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TimeLineProcessorTest {

    private Parser<WaitingTimeline> parser;
    private TimeLineRepo repository;
    private TimeLineProcessor processor;

    @BeforeEach
    void setUp() {
        parser = mock(Parser.class);
        repository = mock(TimeLineRepo.class);
        processor = new TimeLineProcessor(parser, repository);
    }

    @Test
    void testExecuteThrowsException() {
        var inputValues = new String[]{"C", "1.1", "8", "P", "01.01.2012", "83"};

        when(parser.parse(inputValues)).thenThrow(new RuntimeException("Parsing error"));

        assertThrows(RuntimeException.class, () -> processor.execute(inputValues));
    }
}