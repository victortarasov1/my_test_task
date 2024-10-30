package org.example.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReaderImpl implements Reader {
    private final BufferedReader reader;

    public ReaderImpl(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public List<String> readData() {
        try {
            var numOfRows = Integer.parseInt(reader.readLine());
            var lines = new ArrayList<String>();
            for (var i = 0; i < numOfRows; i++) lines.add(reader.readLine());
            return lines;
        } catch (IOException ex) {
            throw new RuntimeException("can't read the file", ex);
        }
    }
}
