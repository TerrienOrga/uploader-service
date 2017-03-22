package com.ote.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileIntegrationService implements IFileIntegrationService {

    @Autowired
    private RowRepository rowRepository;

    @Override
    public void persist(Row row) {
        rowRepository.save(row);
    }
}
