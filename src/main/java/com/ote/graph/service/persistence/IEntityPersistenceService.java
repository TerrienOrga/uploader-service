package com.ote.graph.service.persistence;

import com.ote.graph.model.Filter;
import com.ote.graph.model.GraphElement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IEntityPersistenceService<TE, TK> {

    Page<TE> getMany(Filter<TE> filter, Pageable pageRequest);

    TE getOne(TK id);

    Long count();

    Long count(Filter<TE> filter);

    void delete(TK id);

    void deleteAll();

    void deleteMany(Filter<TE> filter);

    GraphElement create(TE entity);

    GraphElement update(TK id, TE entity);

    GraphElement partialUpdate(TK id, TE entity) throws Exception;
}
