package com.ote.file.service;

import com.ote.row.model.Row;
import com.ote.row.service.RowPersistenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FileIntegrationService implements IFileIntegrationService {

    @Autowired
    private RowPersistenceService rowPersistenceService;

    @Override
    public void clear() {
        rowPersistenceService.deleteAll();
    }

    @Override
    public void save(Row row) {
        log.trace(String.format("creating row : %s", row.toString()));
        rowPersistenceService.create(row);
    }

    @Override
    public long count() {
        return rowPersistenceService.count();
    }
}
