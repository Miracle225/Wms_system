package com.example.wms_system.Order.entity;

import com.example.wms_system.enums.*;
import com.example.wms_system.models.Customer;
import com.example.wms_system.models.Order;
import java.util.List;

public abstract class OrderEntityData {
    public static Order getSingleOrder() {
        return new Order(OrderStatus.NEW,
                PaymentMethod.CASH,
                new Customer(1L,
                        "new Name Surname",
                        "new fake st 110",
                        "+380997654321",
                        "example2@gmail.com"));
    }

    public static Order getSingleOrderWithId() {

        Order order = new Order(OrderStatus.NEW,
                PaymentMethod.CASH,
                new Customer(1L,
                        "new Name Surname",
                        "new fake st 110",
                        "+380997654321",
                        "example2@gmail.com"));
        order.setId(1L);
        return order;
    }

    public static List<Order> getListOfOrder() {
        return List.of(new Order(OrderStatus.NEW,
                PaymentMethod.CASH,
                new Customer(1L,
                        "new Name Surname",
                        "new fake st 110",
                        "+380997654321",
                        "example2@gmail.com")));
    }
}
