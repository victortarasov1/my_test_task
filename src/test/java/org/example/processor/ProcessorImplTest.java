package org.example.processor;

import org.example.processor.command.Command;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProcessorImplTest {
    private ProcessorImpl processor;
    private Command commandMock;

    @BeforeEach
    void setUp() {
        commandMock = mock(Command.class);
        when(commandMock.getType()).thenReturn("testCommand");
        processor = new ProcessorImpl(List.of(commandMock));
    }

    @Test
    void testProcessValidCommand() {


        var input = "testCommand arg1 arg2";

        processor.process(input);

        verify(commandMock).execute(new String[]{"testCommand", "arg1", "arg2"});
    }

    @Test
    void testProcessUnknownCommand() {
        var input = "unknownCommand arg1 arg2";


        RuntimeException exception = assertThrows(RuntimeException.class, () -> processor.process(input));
        assertEquals("unknown command", exception.getMessage());
    }
}