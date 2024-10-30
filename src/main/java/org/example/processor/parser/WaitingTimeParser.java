package org.example.processor.parser;

import org.example.model.WaitingTimeline;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WaitingTimeParser implements Parser<WaitingTimeline> {
    private final DateTimeFormatter formatter;

    public WaitingTimeParser(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public WaitingTimeline parse(String[] inputValues) {
        if(inputValues.length  != 6) throw new RuntimeException("Invalid number of input values");
        return new WaitingTimeline(inputValues[1], inputValues[2], inputValues[3], LocalDate.parse(inputValues[4], formatter), Integer.parseInt(inputValues[5]));
    }
}
