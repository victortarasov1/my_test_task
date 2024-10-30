package org.example.processor.command;

public interface Command {
    void execute(String[] inputValues);

    String getType();
}
