package com.example.wms_system.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "good_operation")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GoodOperation {
    /*
    CREATE TABLE good_operation
(
  id                    INTEGER  NOT NULL ,
  operation_id          INT  NOT NULL ,
  good_id               INT  NOT NULL ,
  warehouse_id          INT  NOT NULL
);
     */

    @Id
    @Column(name = "operation_id")
    private Long operation_id;

    @Id
    @Column(name = "good_id")
    private Long good_id;

    @Id
    @Column(name = "warehouse_id")
    private Long warehouse_id;

    @ManyToOne
    @JoinColumn(name = "good_id", referencedColumnName = "id")
    private Good good;

    @ManyToOne
    @JoinColumn(name = "operation_id", referencedColumnName = "id")
    private Operation operation;

    @ManyToOne
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id")
    private Warehouse warehouse;
}
