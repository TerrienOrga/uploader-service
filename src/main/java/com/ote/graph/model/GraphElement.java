package com.ote.graph.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "GRAPH")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GraphElement {

    @Id
    @SequenceGenerator(name = "graph_id_seq", sequenceName = "graph_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "graph_id_seq")
    @Column(name = "ID", updatable = false)
    private Integer id;

    @Column(name = "SOURCE")
    private String source;

    @Column(name = "DESTINATION")
    private String destination;

    @Column(name = "DISTANCE")
    private Double distance;


}
