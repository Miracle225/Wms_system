package com.example.wms_system.GoodAcceptance.entity;

import com.example.wms_system.models.GoodAcceptance;
import com.example.wms_system.models.Provider;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public abstract class AcceptanceEntityData {
    public static GoodAcceptance getSingleGoodAcceptance(){
        return new GoodAcceptance(Date.valueOf(LocalDate.now()),100,100f,100f,new Provider(1L,"www.google.com","name surname"));
    }

    public static GoodAcceptance getSingleGoodAcceptanceWithId(){

        GoodAcceptance goodAcceptance = new GoodAcceptance(Date.valueOf(LocalDate.now()),100,100f,100f,new Provider(1L,"www.google.com","name surname"));
        goodAcceptance.setId(1L);
        return goodAcceptance;
    }

    public static List<GoodAcceptance> getListOfGoodAcceptance(){
        return List.of(new GoodAcceptance(Date.valueOf(LocalDate.now()),100,100f,100f,new Provider(1L,"www.google.com","name surname")));
    }
}
