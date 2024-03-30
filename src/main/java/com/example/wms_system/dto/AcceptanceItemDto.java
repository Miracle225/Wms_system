package com.example.wms_system.dto;

import com.example.wms_system.models.AcceptanceItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AcceptanceItemDto {
    private Integer expectedQuantity;
    private Integer receivedQuantity;

    public AcceptanceItem toAcceptanceItemEntity(){
        return new AcceptanceItem(
                expectedQuantity,
                receivedQuantity);
    }


}
