package org.example.processor;

import org.example.processor.command.Command;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProcessorImpl implements Processor {
    private final Map<String, Command> commands;

    public ProcessorImpl(List<Command> commands) {
        this.commands = commands.stream().collect(Collectors.toMap(Command::getType, Function.identity()));
    }

    @Override
    public void process(String inputValue) {
        var values = inputValue.split(" ");
        var command = commands.get(values[0]);
        if(command == null) throw new RuntimeException("unknown command");
        command.execute(values);
    }
}
