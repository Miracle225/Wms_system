package com.example.wms_system.repositories;

import com.example.wms_system.enums.OperationType;
import com.example.wms_system.models.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
    @Query("SELECT o FROM Operation o WHERE o.operationDate = ?1")
    List<Operation> findAllByOperationDate(Date operationDate);

    @Query("SELECT o FROM Operation o WHERE o.operationDate>= ?1 AND o.operationDate<= ?2 ORDER BY o.operationDate DESC")
    List<Operation> findAllInPeriod(Date start, Date end);

    List<Operation> findAllByOperationType(OperationType type);

    List<Operation> findAllByUserId(Long id);
}
