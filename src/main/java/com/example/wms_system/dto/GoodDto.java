package com.example.wms_system.dto;

import com.example.wms_system.enums.GoodCategory;
import com.example.wms_system.enums.GoodStatus;
import com.example.wms_system.enums.UnitOfMeasurement;
import com.example.wms_system.models.Good;
import com.example.wms_system.models.Warehouse;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GoodDto {
    @NotNull(message = "The name must not be null")
    private String name;
    private String description;
    @DateTimeFormat(pattern = "DD/MM/YYYY")
    private Date expirationTerm;
    @NotNull(message = "Price can`t be null")
    private Float price;
    private Float weight;
    @NotNull(message = "Code can`t be null")
    private Long code;
    private GoodCategory category;
    private GoodStatus status;
    private UnitOfMeasurement unit;
    private Warehouse warehouse;


public Good toGoodEntity(){
    return new Good(
            name,
            description,
            code,
            price,
            weight,
            unit,
            expirationTerm,
            status,
            category,
            warehouse);
}
}
