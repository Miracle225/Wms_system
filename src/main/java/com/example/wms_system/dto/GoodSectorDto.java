package com.example.wms_system.dto;

import com.example.wms_system.models.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GoodSectorDto {
    private WarehouseSector sector;
    private Good good;
    private Integer quantity;

    public GoodsInWarehouseSector toGoodSectorEntity(){
        return new GoodsInWarehouseSector(quantity,good,sector);
    }

}
