package com.example.wms_system.Order.entity;

import com.example.wms_system.dto.OrderDto;
import com.example.wms_system.enums.*;
import com.example.wms_system.models.Customer;

public abstract class OrderDtoData {
    public static OrderDto getOrder(){
        return new OrderDto(OrderStatus.NEW,
                PaymentMethod.CASH,
                new Customer(1L,
                        "Name Surname",
                        "fake st 110",
                        "+380987654321",
                        "example@gmail.com"));
    }

    public static OrderDto getUpdatedOrder() {
        return new OrderDto(OrderStatus.NEW,
                PaymentMethod.CASH,
                new Customer(1L,
                        "new Name Surname",
                        "new fake st 110",
                        "+380997654321",
                        "example2@gmail.com"));
    }
}
