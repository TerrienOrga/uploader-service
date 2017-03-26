package com.ote.row.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ote.framework.model.IEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "T_ROW")
@Data
@NoArgsConstructor
public class Row implements IEntity<Integer> {

    @Id
    @SequenceGenerator(name = "t_row_id_seq", sequenceName = "t_row_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_row_id_seq")
    @Column(name = "ID", updatable = false)
    private Integer id;

    @Column(name = "NODE_LEFT")
    private String nodeLeft;

    @Column(name = "NODE_RIGHT")
    private String nodeRight;

    @Column(name = "DISTANCE")
    private Double distance;


}
