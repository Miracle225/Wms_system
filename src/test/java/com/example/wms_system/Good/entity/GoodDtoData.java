package com.example.wms_system.Good.entity;

import com.example.wms_system.dto.GoodAcceptanceDto;
import com.example.wms_system.dto.GoodDto;
import com.example.wms_system.enums.GoodCategory;
import com.example.wms_system.enums.GoodStatus;
import com.example.wms_system.enums.TypeOfWarehouse;
import com.example.wms_system.enums.UnitOfMeasurement;
import com.example.wms_system.models.Warehouse;

import java.sql.Date;
import java.time.LocalDate;

public abstract class GoodDtoData {
    public static GoodDto getGood(){
        return new GoodDto("name",
                "description",
                 Date.valueOf(LocalDate.now()),
                100f,
                1000f,
                12345567L,
                 GoodCategory.FOOD_AND_BEVERAGE,
                 GoodStatus.ON_STOCK,
                 UnitOfMeasurement.KG,
                new Warehouse("new st 100",
                        "warehouse",
                        TypeOfWarehouse.FOOD_WAREHOUSE,
                        1000f),
                1.25);
    }

    public static GoodDto getUpdatedGood() {
        return new GoodDto("new name",
                "new description",
                Date.valueOf(LocalDate.now()),
                200f,
                2000f,
                13245567L,
                GoodCategory.FOOD_AND_BEVERAGE,
                GoodStatus.ON_STOCK,
                UnitOfMeasurement.KG,
                new Warehouse("new st 100",
                        "new warehouse",
                        TypeOfWarehouse.FOOD_WAREHOUSE,
                        1000f),
                2.56);
    }

}
