package com.ote.row.controller;

import com.ote.framework.controller.EntityController;
import com.ote.framework.service.persistence.IEntityPersistenceService;
import com.ote.row.model.Row;
import com.ote.row.model.RowParameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;

@RestController
@RequestMapping("/rows")
@Slf4j
public class RowController extends EntityController<Row, Integer>{

    public RowController(@Autowired IEntityPersistenceService<Row, Integer> personPersistenceService) {
        super(personPersistenceService);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Page<Row>> getMany(@ModelAttribute RowParameter parameter, Pageable pageRequest) {

        return getMany(() -> entityPersistenceService.findMany(parameter, pageRequest));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @ResponseBody
    public ResponseEntity<Row> get(@PathVariable(name = "id") Integer id) {

        return getOne(() -> entityPersistenceService.findOne(id));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Integer id) {

        return delete(() -> entityPersistenceService.delete(id));
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAll() {

        return delete(() -> entityPersistenceService.deleteAll());
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Row> create(@RequestBody Row person) {

        IEntityPersistenceService.Result<Row> result = entityPersistenceService.create(person);

        switch (result.getStatus()) {
            case CREATED:
                return new ResponseEntity<>(result.getEntity(), HttpStatus.CREATED);
            default:
                return new ResponseEntity<>((Row) null, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    @ResponseBody
    public ResponseEntity<Row> update(@PathVariable(name = "id") Integer id,
                                         @RequestBody Row person) {

        return updateOrPatch(() -> {
            person.setId(id);
            return entityPersistenceService.update(person);
        });
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/{id}")
    @ResponseBody
    public ResponseEntity<Row> patch(@PathVariable(name = "id") Integer id,
                                        @RequestBody Row person) {

        return updateOrPatch(() -> {
            person.setId(id);
            return entityPersistenceService.partialUpdate(person);
        });
    }

}
