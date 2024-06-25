package com.example.wms_system.services;

import com.example.wms_system.dto.AcceptanceItemDto;
import com.example.wms_system.dto.GoodAcceptanceDto;
import com.example.wms_system.exceptions.AcceptanceNotFoundException;
import com.example.wms_system.exceptions.GoodNotFoundException;
import com.example.wms_system.exceptions.WarehouseSectorNotFoundException;
import com.example.wms_system.models.*;
import com.example.wms_system.repositories.*;
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
    private final AcceptanceItemRepository acceptanceItemRepository;
    private final GoodRepository goodRepository;
    private final WarehouseSectorRepository warehouseSectorRepository;
    private final GoodInWarehouseSectorRepository goodInWarehouseSectorRepository;
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

    public List<GoodAcceptance> getByIdToList(Long id){
        return printLogInfo(List.of(acceptanceRepository.findById(id).get()));
    }
    public List<GoodAcceptance> getAllByAcceptanceDate(Date date) {
        return printLogInfo(acceptanceRepository.findAllByAcceptionDate(date));
    }

    public List<GoodAcceptance> getAllByDatePeriod(Date start, Date end) {
        return printLogInfo(acceptanceRepository.findAllInPeriod(start, end));
    }
    /*
    @Transactional
    public void acceptGoods(GoodAcceptanceDto goodAcceptanceDto, List<AcceptanceItemDto> itemDtos) {
        GoodAcceptance goodAcceptance = goodAcceptanceDto.toAcceptanceEntity();
        GoodAcceptance savedAcceptance = acceptanceRepository.save(goodAcceptance);

        for (AcceptanceItemDto itemDto : itemDtos) {
            AcceptanceItem item = itemDto.toAcceptanceItemEntity();
            item.setAcceptance(savedAcceptance);
            acceptanceItemRepository.save(item);

            Good good = goodRepository.findById(item.getGood().getId())
                    .orElseThrow(() -> new GoodNotFoundException(item.getGood().getId()));

            // Assuming itemDto contains sectorId
            Long sectorId = itemDto.getSectorId();
            updateWarehouseSector(sectorId, item.getReceivedQuantity(), good.getWeight(), item.getGoodId());
        }
        log.info("Goods accepted and sectors updated for acceptance id: {}", savedAcceptance.getId());
    }

    private void updateWarehouseSector(WarehouseSector sectorId, int receivedQuantity, float weight, Good goodId) {
        GoodsInWarehouseSector goodInWarehouseSector = goodInWarehouseSectorRepository
                .findBySectorAndGood(sectorId, goodId)
                .orElse(new GoodsInWarehouseSector(0, goodId,sectorId));

        goodInWarehouseSector.setQuantity(goodInWarehouseSector.getQuantity() + receivedQuantity);
        goodInWarehouseSectorRepository.save(goodInWarehouseSector);

        WarehouseSector warehouseSector = warehouseSectorRepository.findById(sectorId.getId())
                .orElseThrow(() -> new WarehouseSectorNotFoundException(sectorId.getId()));

        float additionalVolume = receivedQuantity * weight;
        warehouseSector.setAvailableVolume(warehouseSector.getAvailableVolume() - additionalVolume);
        warehouseSectorRepository.save(warehouseSector);
    }*/
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


