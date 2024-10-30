package org.example.processor.command;

import org.example.model.WaitingTimeline;
import org.example.processor.parser.Parser;
import org.example.repository.TimeLineRepo;

public class TimeLineProcessor implements Command {
    private final Parser<WaitingTimeline> parser;
    private final TimeLineRepo repository;
    public TimeLineProcessor(Parser<WaitingTimeline> parser, TimeLineRepo repository) {
        this.parser = parser;
        this.repository = repository;
    }

    @Override
    public void execute(String[] inputValues) {
        var timeLine = parser.parse(inputValues);
        repository.save(timeLine);
    }

    @Override
    public String getType() {
        return "C";
    }
}
