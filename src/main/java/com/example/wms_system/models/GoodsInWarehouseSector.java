package com.example.wms_system.models;

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
    @Column(name = "good_id")
    private Long goodId;

    @Id
    @Column(name = "sector_id")
    private Long sectorId;

    @Column
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "good_id", referencedColumnName = "id")
    private Good good;

    @ManyToOne
    @JoinColumn(name = "sector_id", referencedColumnName = "id")
    private WarehouseSector sector;
}
