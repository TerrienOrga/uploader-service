package com.ote.file;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ROW")
@Data
@NoArgsConstructor
public class Row {

    @Id
    @SequenceGenerator(name="row_id_seq", sequenceName="row_id_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="row_id_seq")
    @Column(name = "ID", updatable = false)
    private int id;

    @Column(name = "NODE_LEFT")
    private String nodeLeft;

    @Column(name = "NODE_RIGHT")
    private String nodeRight;

    @Column(name = "DISTANCE")
    private int distance;
}
