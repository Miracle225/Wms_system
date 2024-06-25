package com.example.wms_system.repositories;

import com.example.wms_system.models.GoodOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodOrderRepository extends JpaRepository<GoodOrder,Long> {

@Query("SELECT go FROM GoodOrder go WHERE go.order.id = ?1")
    List<GoodOrder> findAllByOrderId(Long id);
}
