package com.example.wms_system.dto;

import com.example.wms_system.models.GoodAcceptance;
import com.example.wms_system.models.Provider;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@AllArgsConstructor
@Getter
@Setter
public class GoodAcceptanceDto {
    @NotNull(message = "Acceptance date can`t be null")
    private Date acceptionDate;
    private Float acceptsVolume;
    @NotNull(message = "Acceptance price can`t be null")
    private Float price;
    private Integer quantity;
    private Provider provider;

   public GoodAcceptanceDto(){
        quantity = 0;
        price = 0.0f;
        acceptsVolume = 0.0f;
    }

    public GoodAcceptance toAcceptanceEntity(){
    return new GoodAcceptance(
            acceptionDate,
            quantity,
            price,
            acceptsVolume,
            provider);
}
}
