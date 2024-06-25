package com.example.wms_system.models;

import com.example.wms_system.dto.GoodDto;
import com.example.wms_system.dto.GoodOrderDto;
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

    public GoodOrder(Integer quantity,
                     Good good,
                     Order order) {
        this.quantity = quantity;
        this.good = good;
        this.order = order;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "good_id")
    private Good good;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public GoodOrder updateFields(GoodOrderDto goodDto) {
        if (goodDto.getQuantity() != null) {
            quantity = goodDto.getQuantity();
        }
        if (goodDto.getGood() != null) {
            good = goodDto.getGood();
        }
        if (goodDto.getOrder() != null) {
           order = goodDto.getOrder();
        }
        return this;
    }
}
