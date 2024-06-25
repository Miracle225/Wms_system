package com.example.wms_system.repositories;

import com.example.wms_system.models.WarehouseSector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WarehouseSectorRepository extends JpaRepository<WarehouseSector, Long> {
    @Query("SELECT ws from WarehouseSector ws WHERE ws.warehouse.Id = ?1")
    List<WarehouseSector> findAllByWarehouseId(Long id);

    @Query("SELECT ws from WarehouseSector ws WHERE ws.name LIKE CONCAT('%', :name, '%')")
    List<WarehouseSector> findAllByNamePart(String name);

    Optional<WarehouseSector> findByNameAndWarehouseId(String name, Long wId);

}
