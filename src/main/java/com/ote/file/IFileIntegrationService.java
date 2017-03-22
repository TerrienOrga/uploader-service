package com.ote.file;

import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface IFileIntegrationService {

    Pattern ROW_PATTERN = Pattern.compile("(.*) (.*) (\\d+)");

    default void persist(BufferedReader fileReader) {
        fileReader.
                lines().
                parallel().
                map(this::toRow).
                forEach(this::persist);
    }

    default Row toRow(String line) {

        Row row = new Row();

        Matcher matcher = ROW_PATTERN.matcher(line);
        if (matcher.find()) {
            row.setNodeLeft(matcher.group(1));
            row.setNodeRight(matcher.group(2));
            row.setDistance(Integer.parseInt(matcher.group(3)));
        }
        return row;
    }

    void persist(Row row);
}
