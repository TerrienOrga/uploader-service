package com.ote.graph.service;

import com.ote.graph.model.GraphElement;
import com.ote.graph.service.persistence.GraphRepository;
import com.ote.graph.service.persistence.IEntityPersistenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class FileIntegrationService implements IFileIntegrationService {

    private static final Pattern ROW_PATTERN = Pattern.compile("a (.*) (.*) (\\p{Digit}+)");

    @Autowired
    private IEntityPersistenceService<GraphElement, Integer> graphPersistenceService;

    @Override
    public long persist(BufferedReader fileReader) {

        clear();

        AtomicLong count = new AtomicLong(0);

        fileReader.
                lines().
                parallel().
                map(this::toRow).
                peek(p -> {
                    if (count.getAndIncrement() % 10000 == 0)
                        log.info(String.format("Number of lines : %d", count.get()));
                }).
                forEach(this::save);

        log.info("Finish");
        return this.count();
    }

    private GraphElement toRow(String line) {

        GraphElement row = new GraphElement();

        Matcher matcher = ROW_PATTERN.matcher(line);
        if (matcher.find()) {
            row.setSource(matcher.group(1));
            row.setDestination(matcher.group(2));
            row.setDistance(Double.parseDouble(matcher.group(3)));
        }
        return row;
    }

    private void clear() {
        graphPersistenceService.deleteAll();
    }

    private void save(GraphElement graphElement) {
        log.trace(String.format("creating graphElement : %s", graphElement.toString()));
        graphPersistenceService.create(graphElement);
    }

    private long count() {
        return graphPersistenceService.count();
    }
}
