package com.example.wms_system.services;

import com.example.wms_system.dto.CustomerDto;
import com.example.wms_system.dto.GoodDto;
import com.example.wms_system.dto.WarehouseDto;
import com.example.wms_system.exceptions.*;
import com.example.wms_system.models.Customer;
import com.example.wms_system.models.Good;
import com.example.wms_system.models.Warehouse;
import com.example.wms_system.repositories.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;

    public Warehouse getById(Long id) {
        Optional<Warehouse> loadWarehouse = warehouseRepository.findById(id);
        log.info("Loaded warehouse with id: {}", id);
        return loadWarehouse.orElseThrow(() -> {
            log.error("Warehouse with id: {} not found", id);
            return new WarehouseNotFoundException(id);
        });
    }

    public Warehouse getByName(String name) {
        Optional<Warehouse> loadWarehouse = warehouseRepository.findByName(name);
        log.info("Loaded warehouse with name: {}", name);
        return loadWarehouse.orElseThrow(() -> {
            log.error("Warehouse with name: {} not found", name);
            return new WarehouseNotFoundException(name);
        });
    }

    public Warehouse getByAddress(String address) {
        Optional<Warehouse> loadWarehouse = warehouseRepository.findByAddress(address);
        log.info("Loaded warehouse with address: {}", address);
        return loadWarehouse.orElseThrow(() -> {
            log.error("Warehouse with address: {} not found", address);
            return new WarehouseNotFoundException(address);
        });
    }

    public List<Warehouse> getAllWarehouses() {
        return printLogInfo(warehouseRepository.findAll());
    }

    @Transactional
    public Warehouse createNewWarehouse(WarehouseDto warehouseDto) {
        checkIfAddressExists(warehouseDto.getAddress());
        checkIfNameExists(warehouseDto.getName());
        Warehouse warehouse = warehouseDto.toWarehouseEntity();
        Warehouse savedWarehouse = warehouseRepository.save(warehouse);
        log.info("Saved warehouse with id: {}", savedWarehouse.getId());
        return savedWarehouse;
    }

    @Transactional
    public Warehouse updateWarehouse(Long id, WarehouseDto warehouseDto) {
        Warehouse warehouse = warehouseRepository.findById(id).orElseThrow(() -> new WarehouseNotFoundException(id));
        //checkIfNameExists(warehouseDto.getName());
        //checkIfAddressExists(warehouseDto.getAddress());
        return updateWarehouseFields(warehouse, warehouseDto);
    }

    public void deleteWarehouseById(Long id) {
        warehouseRepository.deleteById(id);
        log.info("Delete warehouse by id: {}", id);
    }

    private void checkIfNameExists(String name) {
        if (warehouseRepository.findByName(name).isPresent()) {
            log.error("Warehouse with this name: {} already exists", name);
            throw new WarehouseAlreadyExists("Warehouse with this name " + name + " already exists");
        }
    }

    private void checkIfAddressExists(String address) {
        if (warehouseRepository.findByAddress(address).isPresent()) {
            log.error("Warehouse with this address: {} already exists", address);
            throw new GoodAlreadyExists("Warehouse with this address " + address + " already exists");
        }
    }

    private Warehouse updateWarehouseFields(Warehouse warehouse, WarehouseDto warehouseDto) {
        Warehouse updatedWarehouse = warehouse.updateFields(warehouseDto);
        log.info("Updated warehouse with id: {}", updatedWarehouse.getId());
        return updatedWarehouse;
    }

    private List<Warehouse> printLogInfo(List<Warehouse> warehouses) {
        log.info("Size of loaded warehouses from database: {}", warehouses.size());
        return warehouses;
    }

}
