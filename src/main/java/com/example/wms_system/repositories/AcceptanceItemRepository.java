package com.example.wms_system.repositories;

import com.example.wms_system.models.AcceptanceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AcceptanceItemRepository extends JpaRepository<AcceptanceItem,Long> {
    List<AcceptanceItem> findAllByAcceptanceId(Long id);

    Optional<AcceptanceItem> findByAcceptanceIdAndGoodId(Long acceptId,Long goodId);
}
