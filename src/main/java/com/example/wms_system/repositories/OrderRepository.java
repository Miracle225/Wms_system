package com.example.wms_system.repositories;

import com.example.wms_system.enums.OrderStatus;
import com.example.wms_system.enums.PaymentMethod;
import com.example.wms_system.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findAllByCustomerId(Long id);

    List<Order> findAllByStatus(OrderStatus status);

    List<Order> findAllByPaymentMethod(PaymentMethod method);

    @Query("SELECT o FROM Order o WHERE o.orderDate> ?1 AND o.orderDate< ?2")
    List<Order> findAllInOrderPeriod(Date start, Date end);
}
