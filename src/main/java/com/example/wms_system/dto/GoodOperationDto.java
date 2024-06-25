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
public class GoodOperationDto {
    private Operation operation;
    private Good good;
    private Warehouse warehouse;

    public GoodOperation toGoodOperationEntity(){
        return new GoodOperation(good,operation,warehouse);
    }
}






