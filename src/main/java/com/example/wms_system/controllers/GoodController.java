package com.example.wms_system.controllers;

import com.example.wms_system.dto.GoodDto;
import com.example.wms_system.enums.GoodCategory;
import com.example.wms_system.enums.GoodStatus;
import com.example.wms_system.enums.UnitOfMeasurement;
import com.example.wms_system.models.Good;
import com.example.wms_system.services.GoodService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
//@PreAuthorize("hasRole('ROLE_WAREHOUSE_MANAGER')")
@Controller
@RequiredArgsConstructor
@RequestMapping("/goods")
public class GoodController {
    private final GoodService goodService;

    @GetMapping
    public String getAllGoods(
            @RequestParam Optional<String> status,
            @RequestParam Optional<String> category,
            @RequestParam Optional<String> warehouseId,
            @RequestParam Optional<String> Id,
            @RequestParam Optional<String> name,
            Model model,
            @AuthenticationPrincipal UserDetails principal
    ) {
        List<Good> goods = goodService.getAllGoods();
        if (name.isPresent()) {
            try {
                goods = goodService.getAllByNamePart(name.get());
            } catch (IllegalArgumentException e) {
                model.addAttribute("error", "Invalid good name");
            }
        }
        if (Id.isPresent()) {
            try {
                Long goodId = Long.parseLong(Id.get());
                goods = goodService.getAllById(goodId);
                //goods = goods.stream().filter(good -> good.getId().equals(goodId)).collect(Collectors.toList());
            } catch (IllegalArgumentException e) {
                model.addAttribute("error", "Invalid goodId format");
            }
        }

        if (status.isPresent()) {
            try {
                GoodStatus goodStatus = GoodStatus.valueOf(status.get().toUpperCase());
                goods = goodService.getAllByStatus(goodStatus);
                //goods = goods.stream().filter(good -> good.getStatus().equals(goodStatus)).collect(Collectors.toList());
            } catch (IllegalArgumentException e) {
                model.addAttribute("error", "Invalid status value");
            }
        }

        if (category.isPresent()) {
            try {
                GoodCategory goodCategory = GoodCategory.valueOf(category.get().toUpperCase());
                goods = goodService.getAllByCategory(goodCategory);
                //goods = goods.stream().filter(good -> good.getCategory().equals(goodCategory)).collect(Collectors.toList());
            } catch (IllegalArgumentException e) {
                model.addAttribute("error", "Invalid category value");
            }
        }
        if (warehouseId.isPresent()) {
            try {
                Long warehouseIdLong = Long.parseLong(warehouseId.get());
                goods = goodService.getAllByWarehouse(warehouseIdLong);
                //goods = goods.stream().filter(good -> good.getWarehouse().getId().equals(warehouseIdLong)).collect(Collectors.toList());
            } catch (NumberFormatException e) {
                model.addAttribute("error", "Invalid warehouse ID format");
            }
        }
        if (principal != null) {
            model.addAttribute("username", principal.getUsername());
        }
        model.addAttribute("goods", goods);
        return "goods";
    }

    /* @GetMapping("/{id}")
     public String getById(@PathVariable Long id, Model model) {
         Good good = goodService.getById(id);
         model.addAttribute("good", good);
         return "good-detail";
     }

     @GetMapping("/name/{name}")
     public String getByName(@PathVariable String name, Model model) {
         Good good = goodService.getByName(name);
         model.addAttribute("good", good);
         return "good-detail";
     }

     @GetMapping("/code/{code}")
     public String getByCode(@PathVariable Long code, Model model) {
         Good good = goodService.getByCode(code);
         model.addAttribute("good", good);
         return "good-detail";
     }*/
    @GetMapping("/add")
    public String showCreateGoodForm(Model model) {
        model.addAttribute("good", new GoodDto());
        model.addAttribute("categories", GoodCategory.values());
        model.addAttribute("statuses", GoodStatus.values());
        model.addAttribute("units", UnitOfMeasurement.values());
        return "create-good";
    }

    @PostMapping("/add")
    public String createGood(@ModelAttribute @Valid GoodDto goodDto) {
        goodService.createNewGood(goodDto);
        //model.addAttribute("good", good);
        return "redirect:/goods";
    }

    @GetMapping("/update/{id}")
    public String showUpdateGoodForm(@PathVariable Long id, Model model) {
        Good good = goodService.getById(id);
        model.addAttribute("good", good);
        model.addAttribute("categories", GoodCategory.values());
        model.addAttribute("statuses", GoodStatus.values());
        model.addAttribute("units", UnitOfMeasurement.values());
        return "update-good";

    }

    @PostMapping("/update/{id}")
    public String updateGood(@PathVariable Long id, @ModelAttribute @Valid GoodDto goodDto) {
        goodService.updateGood(id, goodDto);
        return "redirect:/goods";
    }

    @GetMapping("/delete/{id}")
    public String deleteGood(@PathVariable Long id) {
        goodService.deleteGoodById(id);
        return "redirect:/goods";
    }
}

