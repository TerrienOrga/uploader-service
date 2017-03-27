package com.ote.graph.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ShortestPathResult {

    private String source;
    private final List<String> way = new ArrayList<>();
    private Double distance;
}
