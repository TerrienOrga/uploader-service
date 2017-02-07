package com.ote.file;

import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface IFileIntegrationService {

    Pattern ROW_PATTERN = Pattern.compile("(.*) (.*) (\\d+)");

    default void persist(String fileName, BufferedReader fileReader) {
        fileReader.
                lines().
                parallel().
                map(this::toRow).
                forEach(p -> persist(fileName, p));
    }

    default Row toRow(String line) {
        Row.RowBuilder row = Row.builder();
        Matcher matcher = ROW_PATTERN.matcher(line);
        if (matcher.find()) {
            row.
                    nodeLeft(matcher.group(1)).
                    nodeRight(matcher.group(2)).
                    distance(Integer.parseInt(matcher.group(3)));
        }
        return row.build();
    }

    void persist(String fileName, Row row);
}
