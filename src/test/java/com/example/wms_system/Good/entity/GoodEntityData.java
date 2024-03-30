package com.example.wms_system.Good.entity;

import com.example.wms_system.enums.GoodCategory;
import com.example.wms_system.enums.GoodStatus;
import com.example.wms_system.enums.TypeOfWarehouse;
import com.example.wms_system.enums.UnitOfMeasurement;
import com.example.wms_system.models.Good;
import com.example.wms_system.models.Warehouse;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public abstract class GoodEntityData {
    public static Good getSingleGood(){
        return new Good("name",
                "description",
                12345567L,
                100f,
                1000f,
                UnitOfMeasurement.KG,
                Date.valueOf(LocalDate.now()),
                GoodStatus.ON_STOCK,
                GoodCategory.FOOD_AND_BEVERAGE,
                new Warehouse("new st 100",
                        "warehouse",
                        TypeOfWarehouse.FOOD_WAREHOUSE,
                        1000f));
    }

    public static Good getSingleGoodWithId(){

        Good good = new Good("name",
                "description",
                12345567L,
                100f,
                1000f,
                UnitOfMeasurement.KG,
                Date.valueOf(LocalDate.now()),
                GoodStatus.ON_STOCK,
                GoodCategory.FOOD_AND_BEVERAGE,
                new Warehouse("new st 100",
                        "warehouse",
                        TypeOfWarehouse.FOOD_WAREHOUSE,
                        1000f));
        good.setId(1L);
        return good;
    }

    public static List<Good> getListOfGood(){
        return List.of(new Good("name",
                "description",
                12345567L,
                100f,
                1000f,
                UnitOfMeasurement.KG,
                Date.valueOf(LocalDate.now()),
                GoodStatus.ON_STOCK,
                GoodCategory.FOOD_AND_BEVERAGE,
                new Warehouse(1L,"new st 100",
                        "warehouse",
                        TypeOfWarehouse.FOOD_WAREHOUSE,
                        1000f)));
    }
}
