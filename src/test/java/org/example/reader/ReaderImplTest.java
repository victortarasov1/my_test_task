package org.example.reader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ReaderImplTest {
    private BufferedReader bufferedReader;
    private ReaderImpl reader;

    @BeforeEach
    void setUp() {
        bufferedReader = mock(BufferedReader.class);
        reader = new ReaderImpl(bufferedReader);
    }

    @Test
    void readData_shouldReturnListOfLines() throws IOException {

        when(bufferedReader.readLine()).thenReturn("2", "line1", "line2");

        List<String> result = reader.readData();

        assertEquals(2, result.size());
        assertEquals("line1", result.get(0));
        assertEquals("line2", result.get(1));
    }

    @Test
    void readData_shouldThrowRuntimeExceptionWhenIOExceptionOccurs() throws IOException {

        when(bufferedReader.readLine()).thenThrow(new IOException("File not found"));


        RuntimeException exception = assertThrows(RuntimeException.class, reader::readData);
        assertTrue(exception.getMessage().contains("can't read the file"));
    }

    @Test
    void readData_shouldReturnEmptyListWhenNoRowsSpecified() throws IOException {
        when(bufferedReader.readLine()).thenReturn("0");

        List<String> result = reader.readData();

        assertTrue(result.isEmpty());
    }
}