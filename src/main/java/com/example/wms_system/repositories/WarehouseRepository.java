package com.example.wms_system.repositories;

import com.example.wms_system.models.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse,Long> {
    Optional<Warehouse> findByAddress(String address);
    Optional<Warehouse> findByName(String name);
}
