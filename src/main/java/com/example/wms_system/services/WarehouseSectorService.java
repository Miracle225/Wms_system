package com.example.wms_system.services;

import com.example.wms_system.dto.WarehouseSectorDto;
import com.example.wms_system.exceptions.SectorAlreadyExists;
import com.example.wms_system.exceptions.WarehouseSectorNotFoundException;
import com.example.wms_system.models.Warehouse;
import com.example.wms_system.models.WarehouseSector;
import com.example.wms_system.repositories.WarehouseRepository;
import com.example.wms_system.repositories.WarehouseSectorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class WarehouseSectorService {
    private final WarehouseSectorRepository sectorRepository;
    private final WarehouseRepository warehouseRepository;

    public WarehouseSector getById(Long id) {
        Optional<WarehouseSector> loadSector = sectorRepository.findById(id);
        log.info("Loaded warehouse sector with id: {}", id);
        return loadSector.orElseThrow(() -> {
            log.error("Warehouse sector with id: {} not found", id);
            return new WarehouseSectorNotFoundException(id);
        });
    }

    public WarehouseSector getByNameAndWarehouseId(Long id,String name) {
        Optional<WarehouseSector> loadSector = sectorRepository.findByNameAndWarehouseId(name,id);
        log.info("Loaded warehouse sector with name: {} and warehouse id: {}", name,id);
        return loadSector.orElseThrow(() -> {
            log.error("Warehouse sector with warehouse id: {} and name {} not found", id,name);
            return new WarehouseSectorNotFoundException(id);
        });
    }
    public List<WarehouseSector> getAllByWarehouse(Long id){
        return printLogInfo(sectorRepository.findAllByWarehouseId(id));
    }
    public List<WarehouseSector> getAllSectors(){
        return printLogInfo(sectorRepository.findAll());
    }

    @Transactional
    public WarehouseSector createNewSector(WarehouseSectorDto sectorDto) {
       // checkIfNameAndWarehouseIdExists();
        WarehouseSector sector = sectorDto.toSectorEntity();
       // Warehouse foundWarehouse = warehouseRepository.findById(sectorDto.getWarehouseId()).get();
       // sector.setWarehouse(foundWarehouse);
        WarehouseSector savedSector = sectorRepository.save(sector);
        log.info("Saved warehouse sector with id: {}", savedSector.getId());
        return savedSector;
    }

    @Transactional
    public WarehouseSector updateSector(Long id, WarehouseSectorDto sectorDto) {
        WarehouseSector sector = sectorRepository.findById(id).orElseThrow(() -> new WarehouseSectorNotFoundException(id));
        //Warehouse foundWarehouse = warehouseRepository.findById(sectorDto.getWarehouseId()).get();
       // sector.setWarehouse(foundWarehouse);
        // checkIfNameAndWarehouseIdExists();
        return updateSectorFields(sector,sectorDto);
    }

    public void deleteSectorById(Long id) {
        sectorRepository.deleteById(id);
        log.info("Delete warehouse sector by id: {}", id);
    }

    private void checkIfNameAndWarehouseIdExists(Long id, String name) {
        if (sectorRepository.findByNameAndWarehouseId(name,id).isPresent()) {
            log.error("Warehouse sector with this name: {} and in this warehouse: {} already exists", name,id);
            throw new SectorAlreadyExists("Sector with this name " + name + " and in this warehouse " + id + " already exists");
        }
    }
    private WarehouseSector updateSectorFields(WarehouseSector sector,WarehouseSectorDto sectorDto) {
        WarehouseSector updatedSector = sector.updateFields(sectorDto);
        log.info("Updated warehouse sector with id: {}", updatedSector.getId());
        return updatedSector;
    }

    private List<WarehouseSector> printLogInfo(List<WarehouseSector> sectors) {
        log.info("Size of loaded warehouse sectors from database: {}", sectors.size());
        return sectors;
    }
}
