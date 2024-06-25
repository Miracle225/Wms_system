package com.example.wms_system.models;

import com.example.wms_system.dto.OrderDto;
import com.example.wms_system.enums.OrderStatus;
import com.example.wms_system.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Entity
@Table(name = "orders")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {
  /*
  CREATE TABLE Order
(
  id                    INT  NOT NULL ,
  order_date            DATE  NOT NULL ,
  status                VARCHAR2(30)  NOT NULL ,
  payment_method        VARCHAR2(30)  NOT NULL ,
  customer_id           INT  NOT NULL
);
   */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "order_date", columnDefinition = "DATETIME",nullable = false)
    private Date orderDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    public Order(OrderStatus status,
                 PaymentMethod paymentMethod,
                 Customer customer) {
        this.orderDate = new Date(System.currentTimeMillis());
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.customer = customer;
    }

    public Order updateFields(OrderDto orderDto){
        if(orderDto.getStatus()!=null){
            status = orderDto.getStatus();
        }
        if(orderDto.getMethod()!=null){
            paymentMethod = orderDto.getMethod();
        }
        if(orderDto.getCustomer()!=null){
            customer = orderDto.getCustomer();
        }
        return this;
    }
}
