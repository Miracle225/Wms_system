package com.example.wms_system.dto;

import com.example.wms_system.enums.OrderStatus;
import com.example.wms_system.enums.PaymentMethod;
import com.example.wms_system.models.Customer;
import com.example.wms_system.models.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDto {
   private OrderStatus status;
   private PaymentMethod method;
   private Customer customer;

   public Order toOrderEntity(){
       return new Order(
               status,
               method,
               customer);
   }
}
