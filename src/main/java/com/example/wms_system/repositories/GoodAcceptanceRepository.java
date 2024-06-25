package com.example.wms_system.repositories;

import com.example.wms_system.models.GoodAcceptance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface GoodAcceptanceRepository extends JpaRepository<GoodAcceptance,Long> {
List<GoodAcceptance> findAllByAcceptionDate(Date date);

@Query("SELECT a FROM GoodAcceptance a WHERE a.acceptionDate>= ?1 AND a.acceptionDate<= ?2")
List<GoodAcceptance> findAllInPeriod(Date start, Date end);
}
