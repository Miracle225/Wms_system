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
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o WHERE o.customer.Id = ?1")
    List<Order> findAllByCustomerId(Long id);

    @Query("SELECT o FROM Order o WHERE o.status = ?1")
    List<Order> findAllByStatus(OrderStatus status);
    @Query("SELECT o FROM Order o WHERE o.paymentMethod = ?1")
    List<Order> findAllByPaymentMethod(PaymentMethod method);

    @Query("SELECT o FROM Order o WHERE o.orderDate>= ?1 AND o.orderDate<= ?2 ORDER BY o.orderDate DESC")
    List<Order> findAllInOrderPeriod(Date start, Date end);
}
