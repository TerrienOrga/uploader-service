package com.ote.file.service;

import com.ote.row.model.Row;

import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface IFileIntegrationService {

    Pattern ROW_PATTERN = Pattern.compile("(.*) (.*) (\\p{Digit}+)");

    default long persist(BufferedReader fileReader) {

        clear();

        fileReader.
                lines().
                parallel().
                map(this::toRow).
                forEach(this::save);

        return this.count();
    }

    default Row toRow(String line) {

        Row row = new Row();

        Matcher matcher = ROW_PATTERN.matcher(line);
        if (matcher.find()) {
            row.setNodeLeft(matcher.group(1));
            row.setNodeRight(matcher.group(2));
            row.setDistance(Double.parseDouble(matcher.group(3)));
        }
        return row;
    }

    void clear();

    void save(Row row);

    long count();
}
