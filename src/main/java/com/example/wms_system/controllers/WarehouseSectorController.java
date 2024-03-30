package com.example.wms_system.controllers;

import com.example.wms_system.dto.WarehouseDto;
import com.example.wms_system.dto.WarehouseSectorDto;
import com.example.wms_system.models.Order;
import com.example.wms_system.models.Warehouse;
import com.example.wms_system.models.WarehouseSector;
import com.example.wms_system.services.WarehouseSectorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/sectors")
public class WarehouseSectorController {
    private final WarehouseSectorService sectorService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<WarehouseSector> getAllWarehouseSectors(@RequestParam Optional<Long> warehouseId) {
        if (warehouseId.isPresent()) {
            return sectorService.getAllByWarehouse(warehouseId.get());
        }
        return sectorService.getAllSectors();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public WarehouseSector getById(@PathVariable Long id) {
        return sectorService.getById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}/{name}")
    public WarehouseSector getByWarehouseIdAndName(@PathVariable Long id,
                                                   @PathVariable String name) {
        return sectorService.getByNameAndWarehouseId(id, name);
    }

    @PostMapping()
    ResponseEntity<?> createWarehouseSector(@RequestBody @Valid WarehouseSectorDto sectorDto) {
        var sector = sectorService.createNewSector(sectorDto);
        return new ResponseEntity<>(sector, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateWarehouseSector(@PathVariable Long id, @RequestBody @Valid WarehouseSectorDto sectorDto) {
        var sector = sectorService.updateSector(id, sectorDto);
        return new ResponseEntity<>(sector, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteWarehouseSector(@PathVariable Long id) {
        sectorService.deleteSectorById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
