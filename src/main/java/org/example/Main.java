package org.example;

import org.example.processor.Processor;
import org.example.processor.ProcessorImpl;
import org.example.processor.command.QueryProcessor;
import org.example.processor.command.TimeLineProcessor;
import org.example.processor.parser.QueryParser;
import org.example.processor.parser.WaitingTimeParser;
import org.example.reader.Reader;
import org.example.reader.ReaderImpl;
import org.example.repository.MockTimeLineRepoImpl;
import org.example.service.TimeLineServiceImpl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;

public class Main {
    private final Processor processor;
    private final Reader reader;

    public Main(Processor processor, Reader reader) {
        this.processor = processor;
        this.reader = reader;
    }
    public static void main(String[] args) throws FileNotFoundException {
        var processor = createProcessor();
        var reader = createReader();
        new Main(processor, reader).run();
    }

    void run() {
        reader.readData().forEach(processor::process);
    }

    private static Reader createReader() throws FileNotFoundException {
        var bufferedReader = new BufferedReader(new FileReader("test_file.csv"));
        return new ReaderImpl(bufferedReader);
    }

    private static Processor createProcessor() {
        var formatter = new DateTimeFormatterBuilder().append(DateTimeFormatter.ofPattern("[dd.MM.yyyy]" + "[d.MM.yyyy]" )).toFormatter();
        var repository = new MockTimeLineRepoImpl();
        var service = new TimeLineServiceImpl(repository);
        var commands = List.of(
                new TimeLineProcessor(new WaitingTimeParser(formatter), repository),
                new QueryProcessor(new QueryParser(formatter), service)
        );
        return new ProcessorImpl(commands);
    }
}