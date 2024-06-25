package com.example.wms_system.dto;

import com.example.wms_system.models.Good;
import com.example.wms_system.models.GoodOrder;
import com.example.wms_system.models.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GoodOrderDto {
    private Order order;
    private Good good;
    private Integer quantity;

    public GoodOrder toGoodOrderEntity(){
        return new GoodOrder(quantity,good,order);
    }

}
