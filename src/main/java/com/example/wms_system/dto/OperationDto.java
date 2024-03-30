package com.example.wms_system.dto;

import com.example.wms_system.enums.OperationType;
import com.example.wms_system.models.Operation;
import com.example.wms_system.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OperationDto {
    private String description;
    private OperationType type;
    private User user;

    public Operation toOperationEntity(){
        return new Operation(
                type,
                description,
                user);
    }
}
