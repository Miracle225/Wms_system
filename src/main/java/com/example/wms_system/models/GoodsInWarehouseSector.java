package com.example.wms_system.models;

import com.example.wms_system.dto.GoodOrderDto;
import com.example.wms_system.dto.GoodSectorDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "good_in_warehouse_sector")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GoodsInWarehouseSector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "good_id")
    private Good good;

    @ManyToOne
    @JoinColumn(name = "sector_id")
    private WarehouseSector sector;

    public GoodsInWarehouseSector(Integer quantity,
                                  Good good,
                                  WarehouseSector sector) {
        this.quantity = quantity;
        this.good = good;
        this.sector = sector;
    }

    public GoodsInWarehouseSector updateFields(GoodSectorDto goodDto) {
        if (goodDto.getQuantity() != null) {
            quantity = goodDto.getQuantity();
        }
        if (goodDto.getGood() != null) {
            good = goodDto.getGood();
        }
        if (goodDto.getSector() != null) {
            sector = goodDto.getSector();
        }
        return this;
    }
}
