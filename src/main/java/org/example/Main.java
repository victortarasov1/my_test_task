package org.example;

import org.example.processor.Processor;
import org.example.processor.ProcessorImpl;
import org.example.processor.command.QueryProcessor;
import org.example.processor.command.TimeLineProcessor;
import org.example.processor.parser.QueryParser;
import org.example.processor.parser.WaitingTimeParser;
import org.example.repository.MockTimeLineRepoImpl;
import org.example.service.TimeLineServiceImpl;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;

public class Main {
    private final Processor processor;

    public Main() {
        var formatter = new DateTimeFormatterBuilder().append(DateTimeFormatter.ofPattern("[dd.MM.yyyy]" + "[d.MM.yyyy]" )).toFormatter();
        var repository = new MockTimeLineRepoImpl();
        var service = new TimeLineServiceImpl(repository);
        var commands = List.of(
                new TimeLineProcessor(new WaitingTimeParser(formatter), repository),
                new QueryProcessor(new QueryParser(formatter), service)
        );
        this.processor = new ProcessorImpl(commands);
    }

    public static void main(String[] args) throws IOException {
        new Main().run();

    }

    private void run() throws IOException {
        var reader = new BufferedReader(new FileReader("test_file.csv"));
        var numOfRows = Integer.parseInt(reader.readLine());
        for(var i = 0; i < numOfRows; i++) {
            processor.process(reader.readLine());
        }
    }
}