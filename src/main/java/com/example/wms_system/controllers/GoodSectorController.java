package com.example.wms_system.controllers;

import com.example.wms_system.dto.GoodSectorDto;
import com.example.wms_system.models.AcceptanceItem;
import com.example.wms_system.models.GoodsInWarehouseSector;
import com.example.wms_system.services.AcceptanceItemService;
import com.example.wms_system.services.GoodInWarehouseSectorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/sector-goods")
public class GoodSectorController {
    private final GoodInWarehouseSectorService sectorService;
    private final AcceptanceItemService itemService;

    @GetMapping()
    public String getAllGoodsBySector(
            @RequestParam Optional<String> id,
            @RequestParam Optional<String> name,
            Model model){
        List<GoodsInWarehouseSector> goodSectors = sectorService.getAll();
        if (id.isPresent() && !id.get().isBlank()) {
            try {
                Long sectorId = Long.parseLong(id.get());
                goodSectors = sectorService.getAllGoodsBySector(sectorId);
            } catch (IllegalArgumentException e) {
                model.addAttribute("error", "Invalid sectorId format");
            }
        }
        if (name.isPresent() && !name.get().isBlank()) {
            try {
                goodSectors = sectorService.getAllGoodsBySectorName(name.get());
            } catch (IllegalArgumentException e) {
                model.addAttribute("error", "Invalid sector name format");
            }
        }
        model.addAttribute("goodSectors", goodSectors);
        return "good-sectors";
    }

    @GetMapping("/add")
    public String showCreateGoodSectorForm( Model model) {
        model.addAttribute("goodSector", new GoodSectorDto());
        return "create-good-sector";
    }

    @PostMapping("/add")
    public String createGoodSector(@ModelAttribute @Valid GoodSectorDto goodDto) {
        //AcceptanceItem acceptanceItem = itemService.getById(acceptanceId);
        //acceptanceItem.setReceivedQuantity(acceptanceItem.getReceivedQuantity()-goodDto.getQuantity());
        sectorService.createNewSectorGood(goodDto);
        return "redirect:/sector-goods";
    }

    @GetMapping("/update/{id}")
    public String showUpdateGoodSectorForm(@PathVariable Long id, Model model) {
        GoodsInWarehouseSector sector = sectorService.getById(id);
        model.addAttribute("goodSector", sector);
        return "update-good-sector";

    }

    @PostMapping("/update/{id}")
    public String updateGoodSector(@PathVariable Long id, @ModelAttribute @Valid GoodSectorDto sectorDto) {
        sectorService.updateGoodSector(id, sectorDto);
        return "redirect:/sector-goods";
    }

    @GetMapping("/delete/{id}")
    public String deleteGoodSector(@PathVariable Long id) {
        sectorService.deleteGoodSectorById(id);
        return "redirect:/sector-goods";
    }
}
