package com.example.wms_system.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "good_order")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GoodOrder {
    /*
    CREATE TABLE good_order
(
  id                    INTEGER  NOT NULL ,
  good_id               INT  NOT NULL ,
  order_id              INT  NOT NULL
);
     */
    @Id
    @Column(name = "good_id")
    private Long goodId;

    @Id
    @Column(name = "order_id")
    private Long orderId;

    @Column
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "good_id", referencedColumnName = "id")
    private Good good;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;
}
