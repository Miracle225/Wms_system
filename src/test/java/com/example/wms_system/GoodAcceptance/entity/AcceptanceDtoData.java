package com.example.wms_system.GoodAcceptance.entity;

import com.example.wms_system.dto.GoodAcceptanceDto;
import com.example.wms_system.models.Provider;

import java.sql.Date;
import java.time.LocalDate;

public abstract class AcceptanceDtoData {
    public static GoodAcceptanceDto getGoodAcceptance(){
        return new GoodAcceptanceDto(Date.valueOf(LocalDate.now()),
                100f,
                100f,
                100,
                new Provider(1L,"www.google.com","name surname"));
    }

    public static GoodAcceptanceDto getUpdatedGoodAcceptance() {
        return new GoodAcceptanceDto(Date.valueOf(LocalDate.now()),
                200f,
                200f,
                200,
                new Provider(1L, "www.google.com", "name surname"));
    }
}
