package com.example.wms_system.controllers;

import com.example.wms_system.dto.WarehouseDto;
import com.example.wms_system.models.Warehouse;
import com.example.wms_system.services.WarehouseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/warehouses")
public class WarehouseController {
    private final WarehouseService warehouseService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Warehouse> getAllWarehouses() {
        return warehouseService.getAllWarehouses();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Warehouse getById(@PathVariable Long id) {
        return warehouseService.getById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/name/{name}")
    public Warehouse getByName(@PathVariable String name) {
        return warehouseService.getByName(name);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/address/{address}")
    public Warehouse getByAddress(@PathVariable String address) {
        return warehouseService.getByAddress(address);
    }

    @PostMapping()
    ResponseEntity<?> createWarehouse(@RequestBody @Valid WarehouseDto warehouseDto) {
        var warehouse = warehouseService.createNewWarehouse(warehouseDto);
        return new ResponseEntity<>(warehouse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateWarehouse(@PathVariable Long id, @RequestBody @Valid WarehouseDto warehouseDto) {
        var warehouse = warehouseService.updateWarehouse(id, warehouseDto);
        return new ResponseEntity<>(warehouse, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteWarehouse(@PathVariable Long id) {
        warehouseService.deleteWarehouseById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
