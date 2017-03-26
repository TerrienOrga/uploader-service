package com.ote.row.service;

import com.ote.framework.service.persistence.EntityPersistenceService;
import com.ote.row.model.Row;
import com.ote.row.service.repository.RowRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@Slf4j
public class RowPersistenceService extends EntityPersistenceService<Row, Integer> {

    public RowPersistenceService(@Autowired RowRepository personRepository) {
        super(personRepository);
    }
}
