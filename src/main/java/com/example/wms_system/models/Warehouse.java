package com.example.wms_system.models;

import com.example.wms_system.dto.WarehouseDto;
import com.example.wms_system.enums.TypeOfWarehouse;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "warehouses")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Warehouse {
    /*
   CREATE TABLE Warehouse
(
 id                    INT  NOT NULL ,
 address               VARCHAR2(255)  NOT NULL ,
 name                  VARCHAR2(255)  NOT NULL ,
 type                  VARCHAR2(30)  NOT NULL
);
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "address", columnDefinition = "VARCHAR(255)", nullable = false)
    @NotNull(message = "warehouse address can`t be null")
    private String address;
    @Column(name = "name", columnDefinition = "VARCHAR(255)", nullable = false)
    @NotNull(message = "warehouse name can`t be null")
    private String name;
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "warehouse type can`t be null")
    private TypeOfWarehouse typeOfWarehouse;
    @Column(name = "volume", nullable = false)
    private Float volume;
    /*@OneToMany(fetch = FetchType.LAZY,mappedBy = "warehouse", cascade = CascadeType.ALL)
    private List<Good> goods = new ArrayList<>();*/
    /*@OneToMany(fetch = FetchType.LAZY,mappedBy = "warehouse", cascade = CascadeType.ALL)
    private List<WarehouseSector> sectors = new ArrayList<>();*/
    public Warehouse(String address,
                     String name,
                     TypeOfWarehouse typeOfWarehouse,
                     Float volume) {
        this.address = address;
        this.name = name;
        this.typeOfWarehouse = typeOfWarehouse;
        this.volume = volume;
    }

    public Warehouse updateFields(WarehouseDto warehouseDto){
        if(warehouseDto.getName()!=null){
            name = warehouseDto.getName();
        }
        if(warehouseDto.getAddress()!=null){
            address = warehouseDto.getAddress();
        }
        if(warehouseDto.getType()!=null){
            typeOfWarehouse = warehouseDto.getType();
        }
        if(warehouseDto.getVolume()!=null){
            volume = warehouseDto.getVolume();
        }
        return this;
    }
}
