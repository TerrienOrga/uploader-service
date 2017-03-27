package com.ote.graph.model;

import com.ote.graph.model.Node;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Edge {

    private Double distance;
    private Node destination;
}
