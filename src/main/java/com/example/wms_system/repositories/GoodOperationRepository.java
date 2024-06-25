package com.example.wms_system.repositories;

import com.example.wms_system.models.GoodOperation;
import com.example.wms_system.models.GoodOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodOperationRepository extends JpaRepository<GoodOperation,Long> {
    @Query("SELECT go FROM GoodOperation go WHERE go.operation.id = ?1")
    List<GoodOperation> findAllByOperationId(Long id);
}
