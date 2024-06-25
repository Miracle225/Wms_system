package com.example.wms_system.dto;

import com.example.wms_system.models.Warehouse;
import com.example.wms_system.models.WarehouseSector;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class WarehouseSectorDto {
    @NotNull(message = "Name of sector can`t be null")
    private String name;
    @NotNull(message = "Number of total value can`t be null")
    private Float totalVolume;
    private Float availableVolume;
    private Warehouse warehouse;

    public WarehouseSectorDto(){
        availableVolume = totalVolume;
    }
    public WarehouseSector toSectorEntity() {
        return new WarehouseSector(name,
                totalVolume,
                availableVolume,
                warehouse);
    }

}
