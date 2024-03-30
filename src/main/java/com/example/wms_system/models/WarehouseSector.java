package com.example.wms_system.models;

import com.example.wms_system.dto.WarehouseSectorDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "warehouse_sector")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class WarehouseSector {
    /*
    CREATE TABLE Warehouse_sector
(
  id                    INT  NOT NULL ,
  Name                  VARCHAR2(20)  NOT NULL ,
  warehouse_id          INT  NOT NULL
);
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "VARCHAR(30)")
    @NotNull(message = "sector name can`t be null")
    private String name;
    @ManyToOne
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;
    @Column(name = "total_volume")
    private Float totalVolume;
    @Column(name = "availableVolume")
    private Float availableVolume;

    public WarehouseSector(String name,
                           Float totalVolume,
                           Float availableVolume,
                           Warehouse warehouse) {
        this.name = name;
        this.totalVolume = totalVolume;
        this.availableVolume = availableVolume;
        this.warehouse = warehouse;
    }

    public WarehouseSector updateFields(WarehouseSectorDto sectorDto){
        if(sectorDto.getName()!=null){
            name = sectorDto.getName();
        }
        if(sectorDto.getTotalVolume()!=null){
            totalVolume = sectorDto.getTotalVolume();
        }
        if(sectorDto.getAvailableVolume()!=null){
            availableVolume = sectorDto.getAvailableVolume();
        }
        if(sectorDto.getWarehouse()!=null){
            warehouse = sectorDto.getWarehouse();
        }
        return this;
    }

}

