package com.example.wms_system.dto;

import com.example.wms_system.models.AcceptanceItem;
import com.example.wms_system.models.Good;
import com.example.wms_system.models.GoodAcceptance;
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
    private GoodAcceptance acceptance;
    private Good good;
    private Boolean isDistributed;

    public AcceptanceItem toAcceptanceItemEntity() {
        return new AcceptanceItem(
                expectedQuantity,
                receivedQuantity,
                acceptance,
                good);
    }


}
