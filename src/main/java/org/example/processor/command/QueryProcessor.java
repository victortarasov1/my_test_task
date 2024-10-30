package org.example.processor.command;

import org.example.model.Query;
import org.example.processor.parser.Parser;
import org.example.service.TimeLineService;

public class QueryProcessor implements Command {
    private final TimeLineService service;
    private final Parser<Query> parser;

    public QueryProcessor(Parser<Query> parser, TimeLineService service) {
        this.service = service;
        this.parser = parser;
    }

    @Override
    public void execute(String[] inputValues) {
        var query = parser.parse(inputValues);
        System.out.println(service.getAverage(query));
    }

    @Override
    public String getType() {
        return "D";
    }
}
