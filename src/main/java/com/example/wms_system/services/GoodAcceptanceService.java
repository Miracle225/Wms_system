package com.example.wms_system.services;

import com.example.wms_system.dto.GoodAcceptanceDto;
import com.example.wms_system.exceptions.AcceptanceNotFoundException;
import com.example.wms_system.models.GoodAcceptance;
import com.example.wms_system.repositories.GoodAcceptanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class GoodAcceptanceService {
    private final GoodAcceptanceRepository acceptanceRepository;

    public GoodAcceptance getById(Long id) {
        Optional<GoodAcceptance> loadAcceptance = acceptanceRepository.findById(id);
        log.info("Loaded good acceptance with id: {}", id);
        return loadAcceptance.orElseThrow(() -> {
            log.error("Good acceptance with id: {} not found", id);
            return new AcceptanceNotFoundException(id);
        });
    }

    public List<GoodAcceptance> getAll() {
        return printLogInfo(acceptanceRepository.findAll());
    }

    public List<GoodAcceptance> getAllByAcceptanceDate(Date date) {
        return printLogInfo(acceptanceRepository.findAllByAcceptionDate(date));
    }

    public List<GoodAcceptance> getAllByDatePeriod(Date start, Date end) {
        return printLogInfo(acceptanceRepository.findAllInPeriod(start, end));
    }

    @Transactional
    public GoodAcceptance createNewAcceptance(GoodAcceptanceDto acceptanceDto) {
        GoodAcceptance acceptance = acceptanceDto.toAcceptanceEntity();
        GoodAcceptance savedAcceptance = acceptanceRepository.save(acceptance);
        log.info("Saved good acceptance with id: {}", savedAcceptance.getId());
        return savedAcceptance;
    }

    @Transactional
    public GoodAcceptance updateAcceptance(Long id, GoodAcceptanceDto acceptanceDto) {
        GoodAcceptance acceptance = acceptanceRepository.findById(id).orElseThrow(() -> new AcceptanceNotFoundException(id));
        return updateAcceptanceFields(acceptance, acceptanceDto);
    }

    public void deleteAcceptanceById(Long id) {
        if(getById(id)!=null) {
            acceptanceRepository.deleteById(id);
            log.info("Delete good acceptance by id: {}", id);
        }else{
            throw new AcceptanceNotFoundException(id);
        }
    }

    private GoodAcceptance updateAcceptanceFields(GoodAcceptance acceptance, GoodAcceptanceDto acceptanceDto) {
        GoodAcceptance updatedAcceptance = acceptance.updateFields(acceptanceDto);
        log.info("Updated good acceptance with id: {}", updatedAcceptance.getId());
        return updatedAcceptance;
    }

    private List<GoodAcceptance> printLogInfo(List<GoodAcceptance> acceptances) {
        log.info("Size of loaded acceptances from database: {}", acceptances.size());
        return acceptances;
    }
}


