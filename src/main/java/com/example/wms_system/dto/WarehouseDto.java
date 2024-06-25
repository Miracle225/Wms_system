package com.example.wms_system.dto;

import com.example.wms_system.enums.TypeOfWarehouse;
import com.example.wms_system.models.Warehouse;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WarehouseDto {
    @NotNull(message = "Address can`t be null")
    private String address;
    @NotNull(message = "Name can`be null")
    private String name;
    //@NotNull(message = "Type of warehouse can`t be null")
    private TypeOfWarehouse type;
    private Float volume;


    public Warehouse toWarehouseEntity(){
    return new Warehouse(
            address,
            name,
            type,
            volume);
}
}
