package com.ote.row.service.repository;

import com.ote.row.model.Row;
import org.springframework.stereotype.Repository;
import com.ote.framework.service.persistence.repository.IEntityRepository;

@Repository
public interface RowRepository extends IEntityRepository<Row, Integer>{
}
