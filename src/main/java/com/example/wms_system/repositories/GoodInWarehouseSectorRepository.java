package com.example.wms_system.repositories;

import com.example.wms_system.models.Good;
import com.example.wms_system.models.GoodsInWarehouseSector;
import com.example.wms_system.models.WarehouseSector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GoodInWarehouseSectorRepository extends JpaRepository<GoodsInWarehouseSector, Long> {
    @Query("SELECT gw FROM GoodsInWarehouseSector gw WHERE gw.sector.id = ?1")
    List<GoodsInWarehouseSector> findAllGoodsBySector(Long id);
    @Query("SELECT gw FROM GoodsInWarehouseSector  gw WHERE gw.sector.name = ?1")
    List<GoodsInWarehouseSector> findAllByGoodsBySectorName(String name);

    Optional<GoodsInWarehouseSector> findBySectorAndGood(WarehouseSector sector, Good good);
}
