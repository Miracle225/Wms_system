package com.example.wms_system.models;

import com.example.wms_system.dto.GoodDto;
import com.example.wms_system.enums.GoodCategory;
import com.example.wms_system.enums.GoodStatus;
import com.example.wms_system.enums.UnitOfMeasurement;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Entity
@Table(name = "good")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Good {
    /*
    CREATE TABLE Good
(
  id                    INT  NOT NULL ,
  code                  VARCHAR2(20)  NOT NULL ,
  weight                FLOAT  NOT NULL ,
  description           LONG VARCHAR  NULL ,
  name                  VARCHAR2(255)  NOT NULL ,
  status                VARCHAR2(50)  NOT NULL ,
  price                 DECIMAL(19,4)  NOT NULL ,
  expiration_term       DATE  NULL ,
  unit_of_measurement   VARCHAR2(50)  NOT NULL ,
  category              VARCHAR2(30)  NOT NULL ,
  warehouse_id          INT  NOT NULL
);
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", columnDefinition = "VARCHAR(100)", nullable = false)
    private String name;
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    @Column(name = "code", nullable = false)
    private Long code;
    @Column
    @NotNull(message = "good price can`t be null")
    private Float price;
    @Column(name = "weight")
    private Float weight;
    @Enumerated(EnumType.STRING)
    private UnitOfMeasurement unitOfMeasurement;
    @Column(columnDefinition = "DATE")
    @DateTimeFormat(pattern = "DD/MM/YYYY")
    private Date expirationTerm;
    @Enumerated(EnumType.STRING)
    private GoodStatus status;
    @Enumerated(EnumType.STRING)
    private GoodCategory category;
    @ManyToOne
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;

    public Good(String name,
                String description,
                Long code,
                Float price,
                Float weight,
                UnitOfMeasurement unitOfMeasurement,
                Date expirationTerm,
                GoodStatus status,
                GoodCategory category,
                Warehouse warehouse) {
        this.name = name;
        this.description = description;
        this.code = code;
        this.price = price;
        this.weight = weight;
        this.unitOfMeasurement = unitOfMeasurement;
        this.expirationTerm = expirationTerm;
        this.status = status;
        this.category = category;
        this.warehouse = warehouse;
    }

    public Good updateFields(GoodDto goodDto) {
        if (goodDto.getCode() != null) {
            code = goodDto.getCode();
        }
        if (goodDto.getExpirationTerm() != null) {
            expirationTerm = goodDto.getExpirationTerm();
        }
        if (goodDto.getPrice() != null) {
            price = goodDto.getPrice();
        }
        if (goodDto.getWeight() != null) {
            weight = goodDto.getWeight();
        }
        if (goodDto.getCategory() != null) {
            category = goodDto.getCategory();
        }
        if (goodDto.getDescription() != null) {
            description = goodDto.getDescription();
        }
        if (goodDto.getName() != null) {
            name = goodDto.getName();
        }
        if (goodDto.getStatus() != null) {
            status = goodDto.getStatus();
        }
        if (goodDto.getUnit() != null) {
            unitOfMeasurement = goodDto.getUnit();
        }
        if(goodDto.getWarehouse()!=null){
            warehouse = goodDto.getWarehouse();
        }
        return this;
    }
}
