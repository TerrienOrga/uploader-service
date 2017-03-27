package com.ote.graph.controller;

import com.ote.graph.model.GraphElement;
import com.ote.graph.model.GraphFilter;
import com.ote.graph.service.persistence.IEntityPersistenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/graphs")
@Slf4j
public class GraphPersistenceController {

    @Autowired
    private IEntityPersistenceService<GraphElement, Integer> graphPersistenceService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Page<GraphElement> getMany(@ModelAttribute GraphFilter parameter, Pageable pageRequest) {

        return graphPersistenceService.getMany(parameter, pageRequest);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @ResponseBody
    public GraphElement getOne(@PathVariable(name = "id") Integer id) {

        return graphPersistenceService.getOne(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable(name = "id") Integer id) {

        graphPersistenceService.delete(id);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteMany(@ModelAttribute GraphFilter parameter) {

        graphPersistenceService.deleteMany(parameter);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public GraphElement create(@RequestBody GraphElement graphElement) {

        return graphPersistenceService.create(graphElement);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    @ResponseBody
    public GraphElement update(@PathVariable(name = "id") Integer id,
                               @RequestBody GraphElement graphElement) {

        return graphPersistenceService.update(id, graphElement);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/{id}")
    @ResponseBody
    public GraphElement partialUpdate(@PathVariable(name = "id") Integer id,
                                      @RequestBody GraphElement partialGraphElement) throws Exception {

        return graphPersistenceService.partialUpdate(id, partialGraphElement);
    }


}
