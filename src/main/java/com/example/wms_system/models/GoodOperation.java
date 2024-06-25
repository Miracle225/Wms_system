package com.example.wms_system.models;

import com.example.wms_system.dto.GoodOperationDto;
import com.example.wms_system.dto.GoodOrderDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "good_operation")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GoodOperation {
    /*
    CREATE TABLE good_operation
(
  id                    INTEGER  NOT NULL ,
  operation_id          INT  NOT NULL ,
  good_id               INT  NOT NULL ,
  warehouse_id          INT  NOT NULL
);
     */

    public GoodOperation(Good good,
                         Operation operation,
                         Warehouse warehouse) {
        this.good = good;
        this.operation = operation;
        this.warehouse = warehouse;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "good_id",nullable = false)
    private Good good;

    @ManyToOne
    @JoinColumn(name = "operation_id",nullable = false)
    private Operation operation;

    @ManyToOne
    @JoinColumn(name = "warehouse_id",nullable = false)
    private Warehouse warehouse;

    public GoodOperation updateFields(GoodOperationDto operationDto) {
        if (operationDto.getOperation() != null) {
            operation = operationDto.getOperation();
        }
        if (operationDto.getGood() != null) {
            good = operationDto.getGood();
        }
        if (operationDto.getWarehouse() != null) {
            warehouse = operationDto.getWarehouse();
        }
        return this;
    }
}
