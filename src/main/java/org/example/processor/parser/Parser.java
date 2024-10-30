package org.example.processor.parser;

public interface Parser <T> {
    T  parse(String[] inputValues);
}
