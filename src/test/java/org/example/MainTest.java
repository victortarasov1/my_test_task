package org.example;

import org.example.processor.Processor;
import org.example.reader.Reader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MainTest {
    private Processor processorMock;
    private Reader readerMock;
    private Main main;

    @BeforeEach
    void setUp() {
        processorMock = mock(Processor.class);
        readerMock = mock(Reader.class);
        main = new Main(processorMock, readerMock);
    }

    @Test
    void run_shouldProcessAllDataFromReader() {
        when(readerMock.readData()).thenReturn(List.of("command1", "command2", "command3"));

        main.run();

        verify(processorMock).process("command1");
        verify(processorMock).process("command2");
        verify(processorMock).process("command3");

        verifyNoMoreInteractions(processorMock);
    }
}