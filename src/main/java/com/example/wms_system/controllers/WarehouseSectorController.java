package com.example.wms_system.controllers;

import com.example.wms_system.dto.WarehouseSectorDto;
import com.example.wms_system.models.GoodsInWarehouseSector;
import com.example.wms_system.models.WarehouseSector;
import com.example.wms_system.services.GoodInWarehouseSectorService;
import com.example.wms_system.services.WarehouseSectorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/sectors")
public class WarehouseSectorController {
    private final WarehouseSectorService sectorService;
    private final GoodInWarehouseSectorService goodSectorService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public String getAllSectors(
            @RequestParam Optional<String> id,
            @RequestParam Optional<String> name,
            @RequestParam Optional<String> warehouse,
            Model model
    ) {
        List<WarehouseSector> sectors = sectorService.getAllSectors();
        for (WarehouseSector sector : sectors) {
            float occupiedVolume = 0.0f;
            for(GoodsInWarehouseSector goods:goodSectorService.getAllGoodsBySector(sector.getId())){
                Integer quantity = goods.getQuantity();
                Double volume = goods.getGood().getVolume();
                occupiedVolume = quantity*volume.floatValue();
            }
            sector.setAvailableVolume(sector.getTotalVolume()-occupiedVolume);
        }
        if (id.isPresent() && !id.get().isBlank()) {
            try {
                Long sectorId = Long.parseLong(id.get());
                sectors = sectorService.getByIdToList(sectorId);
            } catch (IllegalArgumentException e) {
                model.addAttribute("error", "Invalid id value");
            }
        }
        if (name.isPresent() && !name.get().isBlank()) {
            try {
                sectors = sectorService.getAllBySectorNamePart(name.get());
            } catch (IllegalArgumentException e) {
                model.addAttribute("error", "Invalid sector name value");
            }
        }
        if (warehouse.isPresent() && !warehouse.get().isBlank()) {
            try {
                Long warehouseIdLong = Long.parseLong(warehouse.get());
                sectors = sectorService.getAllByWarehouse(warehouseIdLong);
            } catch (IllegalArgumentException e) {
                model.addAttribute("error", "Invalid Warehouse id value");
            }
        }
        model.addAttribute("sectors", sectors);
        return "sectors";
    }

    @GetMapping("/add")
    public String showCreateSectorForm(Model model) {
        model.addAttribute("sector", new WarehouseSectorDto());
        return "create-sector";
    }

    @PostMapping("/add")
    public String createSector(@ModelAttribute @Valid WarehouseSectorDto sectorDto) {
        sectorService.createNewSector(sectorDto);
        return "redirect:/admin/sectors";
    }

    @GetMapping("/update/{id}")
    public String showCreateSectorForm(@PathVariable Long id, Model model) {
        WarehouseSector sector = sectorService.getById(id);
        model.addAttribute("sector", sector);
        return "update-sector";
    }

    @PostMapping("/update/{id}")
    public String updateSector(@PathVariable Long id, @ModelAttribute @Valid WarehouseSectorDto sectorDto) {
        sectorService.updateSector(id, sectorDto);
        return "redirect:/admin/sectors";
    }

    @GetMapping("/delete/{id}")
    public String deleteSector(@PathVariable Long id) {
        sectorService.deleteSectorById(id);
        return "redirect:/admin/sectors";
    }
}
