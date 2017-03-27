package com.ote.graph.service.persistence;

import com.ote.graph.model.GraphElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GraphRepository extends JpaRepository<GraphElement, Integer>, JpaSpecificationExecutor<GraphElement> {

    @Query( nativeQuery = true,
            value = "SELECT SOURCE FROM GRAPH UNION SELECT DESTINATION FROM GRAPH")
    List<String> findAllNodes();

    @Query(value = "SELECT destination FROM GraphElement WHERE source = :nodeName")
    List<String> findNeighbours(@Param("nodeName") String nodeName);

    @Query(value = "SELECT distance FROM GraphElement WHERE source = :source AND destination = :destination")
    Double findDistance(@Param("source") String source, @Param("destination") String destination);
}
