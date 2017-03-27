package com.ote.graph.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Node {

    private final String name;
    private final List<Edge> edges;
}
