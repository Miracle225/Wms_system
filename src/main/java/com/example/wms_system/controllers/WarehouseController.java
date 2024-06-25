package com.example.wms_system.controllers;

import com.example.wms_system.dto.GoodDto;
import com.example.wms_system.dto.WarehouseDto;
import com.example.wms_system.enums.GoodCategory;
import com.example.wms_system.enums.GoodStatus;
import com.example.wms_system.enums.TypeOfWarehouse;
import com.example.wms_system.enums.UnitOfMeasurement;
import com.example.wms_system.models.Good;
import com.example.wms_system.models.Warehouse;
import com.example.wms_system.services.WarehouseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/warehouses")
public class WarehouseController {
    private final WarehouseService warehouseService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public String getAllWarehouses(Model model) {
        List<Warehouse> warehouses = warehouseService.getAllWarehouses();
        model.addAttribute("warehouses",warehouses);
        return "warehouses";
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

    @GetMapping("/add")
    public String showCreateWarehouseForm(Model model) {
        model.addAttribute("warehouse", new WarehouseDto());
        model.addAttribute("types", TypeOfWarehouse.values());
        return "create-warehouse";
    }

    @PostMapping("/add")
    public String createWarehouse(@ModelAttribute @Valid WarehouseDto warehouseDto) {
        warehouseService.createNewWarehouse(warehouseDto);
        return "redirect:/admin/warehouses";
    }

    @GetMapping("/update/{id}")
    public String showUpdateWarehouseForm(@PathVariable Long id, Model model) {
        Warehouse warehouse = warehouseService.getById(id);
        model.addAttribute("warehouse", warehouse);
        model.addAttribute("types", TypeOfWarehouse.values());
        return "update-warehouse";

    }

    @PostMapping("/update/{id}")
    public String updateWarehouse(@PathVariable Long id, @ModelAttribute @Valid WarehouseDto warehouseDto) {
       warehouseService.updateWarehouse(id,warehouseDto);
        return "redirect:/admin/warehouses";
    }

    @GetMapping("/delete/{id}")
    public String deleteWarehouse(@PathVariable Long id) {
        warehouseService.deleteWarehouseById(id);
        return "redirect:/admin/warehouses";
    }
}
