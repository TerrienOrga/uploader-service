package com.ote.framework.controller;

import com.ote.framework.model.IEntity;
import com.ote.framework.service.persistence.IEntityPersistenceService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Optional;
import java.util.function.Supplier;

public abstract class EntityController<TE extends IEntity<TK>, TK extends Serializable> {

    protected IEntityPersistenceService<TE, TK> entityPersistenceService;

    protected EntityController(IEntityPersistenceService<TE, TK> entityPersistenceService) {
        this.entityPersistenceService = entityPersistenceService;
    }

    protected ResponseEntity<TE> getOne(Supplier<Optional<TE>> supplier) {
        return get(supplier);
    }

    protected ResponseEntity<Page<TE>> getMany(Supplier<Optional<Page<TE>>> supplier) {
        return get(supplier);
    }

    protected <T> ResponseEntity<T> get(Supplier<Optional<T>> supplier) {

        Optional<T> result = supplier.get();

        return result.isPresent() ?
                new ResponseEntity<>(result.get(), HttpStatus.FOUND) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    protected ResponseEntity<Void> delete(Supplier<IEntityPersistenceService.Status> supplier) {

        IEntityPersistenceService.Status status = supplier.get();

        switch (status) {
            case DELETED:
                return new ResponseEntity<>((Void) null, HttpStatus.NO_CONTENT);
            default:
                return new ResponseEntity<>((Void) null, HttpStatus.NOT_FOUND);
        }
    }

    protected ResponseEntity<TE> updateOrPatch(Supplier<IEntityPersistenceService.Result<TE>> supplier) {

        IEntityPersistenceService.Result<TE> result = supplier.get();

        switch (result.getStatus()) {
            case UPDATED:
                return new ResponseEntity<>(result.getEntity(), HttpStatus.OK);
            case NOT_FOUND:
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            default:
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<TE> create(Supplier<IEntityPersistenceService.Result<TE>> supplier) {

        IEntityPersistenceService.Result<TE> result = supplier.get();

        switch (result.getStatus()) {
            case CREATED:
                return new ResponseEntity<>(result.getEntity(), HttpStatus.CREATED);
            default:
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
