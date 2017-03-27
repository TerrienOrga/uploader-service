package com.ote.graph.service.persistence;

import com.ote.graph.model.Filter;
import com.ote.graph.model.Graph;
import com.ote.graph.model.GraphElement;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
public class GraphPersistenceService implements IEntityPersistenceService<GraphElement, Integer> {

    @Autowired
    private GraphRepository graphRepository;

    @Override
    public Page<GraphElement> getMany(Filter<GraphElement> filter, Pageable pageRequest) {

        return graphRepository.findAll(filter.getFilter(), pageRequest);
    }

    public GraphElement getOne(Integer id) {

        return graphRepository.findOne(id);
    }

    @Override
    public Long count() {
        return graphRepository.count();
    }

    @Override
    public Long count(Filter<GraphElement> filter) {
        return graphRepository.count(filter.getFilter());
    }

    public void delete(Integer id) {

        graphRepository.delete(id);
    }

    public void deleteAll(){
        graphRepository.deleteAll();
    }

    public void deleteMany(Filter<GraphElement> filter) {

       if (filter.isFiltered()) {
            List<GraphElement> elements = graphRepository.findAll(filter.getFilter());
            graphRepository.delete(elements);
        } else {
            deleteAll();
        }
    }

    public GraphElement create(GraphElement graphElement) {

        graphElement.setId(null);
        return graphRepository.save(graphElement);
    }

    public GraphElement update(Integer id, GraphElement graphElement) {
        graphElement.setId(id);
        return graphRepository.save(graphElement);
    }

    public GraphElement partialUpdate(Integer id, GraphElement graphElement) throws Exception {

        graphElement.setId(id);
        GraphElement dest = this.getOne(id);
        new BeanUtilsBean() {
            @Override
            public void copyProperty(Object dest, String name, Object value)
                    throws IllegalAccessException, InvocationTargetException {
                if (value != null) {
                    super.copyProperty(dest, name, value);
                }
            }
        }.copyProperties(dest, graphElement);
        return graphRepository.save(dest);
    }

}
