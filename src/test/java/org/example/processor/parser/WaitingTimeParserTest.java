package org.example.processor.parser;

import org.example.model.WaitingTimeline;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class WaitingTimeParserTest {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private final WaitingTimeParser parser = new WaitingTimeParser(formatter);

    @Test
    void testParseValidWaitingTimeline() {
        var inputValues = new String[]{"command", "service1", "questionType1", "responseType1", "01.01.2020", "30"};

        var timeline = parser.parse(inputValues);

        assertEquals("service1", timeline.serviceId());
        assertEquals("questionType1", timeline.questionTypeId());
        assertEquals("responseType1", timeline.responseType());
        assertEquals(LocalDate.of(2020, 1, 1), timeline.date());
        assertEquals(30, timeline.waitingTime());
    }

    @Test
    void testParseInvalidInputLength() {
        var inputValues = new String[]{"command", "service1", "questionType1"};

        var exception = assertThrows(RuntimeException.class, () -> parser.parse(inputValues));
        assertEquals("Invalid number of input values", exception.getMessage());
    }

    @Test
    void testParseInvalidDateFormat() {
        var inputValues = new String[]{"command", "service1", "questionType1", "responseType1", "01-01-2020", "30"};

        var exception = assertThrows(RuntimeException.class, () -> parser.parse(inputValues));
        assertTrue(exception.getMessage().contains("Text '01-01-2020' could not be parsed"));
    }

    @Test
    void testParseInvalidWaitingTime() {
        var inputValues = new String[]{"command", "service1", "questionType1", "responseType1", "01.01.2020", "thirty"};

        var exception = assertThrows(NumberFormatException.class, () -> parser.parse(inputValues));
        assertEquals("For input string: \"thirty\"", exception.getMessage());
    }
}