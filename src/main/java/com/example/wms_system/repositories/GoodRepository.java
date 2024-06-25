package com.example.wms_system.repositories;

import com.example.wms_system.enums.GoodCategory;
import com.example.wms_system.enums.GoodStatus;
import com.example.wms_system.models.Good;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface GoodRepository extends JpaRepository<Good,Long> {
Optional<Good> findByCode(Long code);

List<Good> findAllByCategory(GoodCategory category);

Optional<Good> findByName(String name);
List<Good> findAllByStatus(GoodStatus status);

List<Good> findAllByWarehouseId(Long id);
@Query("Select g FROM Good g WHERE g.name LIKE CONCAT('%', :name, '%')")
List<Good> findAllByName(String name);

}
