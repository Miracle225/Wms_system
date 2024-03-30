package com.example.wms_system.repositories;

import com.example.wms_system.models.WarehouseSector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WarehouseSectorRepository extends JpaRepository<WarehouseSector,Long> {

    List<WarehouseSector> findAllByWarehouseId(Long id);
    Optional<WarehouseSector> findByNameAndWarehouseId(String name,Long wId);

}
