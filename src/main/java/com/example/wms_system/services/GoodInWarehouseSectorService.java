package com.example.wms_system.services;

import com.example.wms_system.dto.GoodSectorDto;
import com.example.wms_system.exceptions.GoodNotFoundException;
import com.example.wms_system.models.GoodsInWarehouseSector;
import com.example.wms_system.repositories.GoodInWarehouseSectorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class GoodInWarehouseSectorService {

    private final GoodInWarehouseSectorRepository sectorRepository;

    public GoodsInWarehouseSector getById(Long id) {
        Optional<GoodsInWarehouseSector> loadGood = sectorRepository.findById(id);
        log.info("Loaded sector good with id: {}", id);
        return loadGood.orElseThrow(() -> {
            log.error("Order sector goods with id: {} not found", id);
            return new GoodNotFoundException(id);
        });
    }

    public List<GoodsInWarehouseSector> getAllGoodsBySector(Long id){
        return printLogInfo(sectorRepository.findAllGoodsBySector(id));
    }
    public List<GoodsInWarehouseSector> getAll(){
        return printLogInfo(sectorRepository.findAll());
    }

    public List<GoodsInWarehouseSector> getAllGoodsBySectorName(String name){
        return printLogInfo(sectorRepository.findAllByGoodsBySectorName(name));
    }
    @Transactional
    public GoodsInWarehouseSector createNewSectorGood(GoodSectorDto sectorDto) {
        GoodsInWarehouseSector sector = sectorDto.toGoodSectorEntity();
        GoodsInWarehouseSector savedSector = sectorRepository.save(sector);
        log.info("Saved good sector with id: {}", savedSector.getId());
        return savedSector;
    }

    @Transactional
    public GoodsInWarehouseSector updateGoodSector(Long id, GoodSectorDto sectorDto) {
        GoodsInWarehouseSector goodSector = sectorRepository.findById(id).orElseThrow(() -> new GoodNotFoundException(id));
        return updateGoodSectorFields(sectorDto,goodSector);
    }

    public void deleteGoodSectorById(Long id) {
        if (sectorRepository.findById(id).isPresent()) {
            sectorRepository.deleteById(id);
            log.info("Delete sector good by id: {}", id);
        }else {
            throw new GoodNotFoundException(id);
        }
    }

    private GoodsInWarehouseSector updateGoodSectorFields(GoodSectorDto sectorDto, GoodsInWarehouseSector sector) {
        GoodsInWarehouseSector updatedSector = sector.updateFields(sectorDto);
        log.info("Updated sector good with id: {}", updatedSector.getId());
        return updatedSector;
    }

    private List<GoodsInWarehouseSector> printLogInfo(List<GoodsInWarehouseSector> goods) {
        log.info("Size of loaded order sector goods from database: {}", goods.size());
        return goods;
    }
}
