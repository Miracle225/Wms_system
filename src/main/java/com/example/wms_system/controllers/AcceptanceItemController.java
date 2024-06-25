package com.example.wms_system.controllers;

import com.example.wms_system.dto.AcceptanceItemDto;
import com.example.wms_system.dto.GoodOrderDto;
import com.example.wms_system.models.AcceptanceItem;
import com.example.wms_system.services.AcceptanceItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping( "/reception-item")
public class AcceptanceItemController {

    private final AcceptanceItemService itemService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public String getAllAcceptanceItemsByAcceptance(@PathVariable Long id, Model model
    ) {
            List<AcceptanceItem> items = itemService.getAllByAcceptanceId(id);
            model.addAttribute("items",items);
            //model.addAttribute("")
        return "reception-items";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/acceptance-and-good")
    public AcceptanceItem getByAcceptanceAndGood(
            @RequestParam Optional<Long> acceptanceId,
            @RequestParam Optional<Long> goodId
    ) {
        if (acceptanceId.isPresent() && goodId.isPresent())
            return itemService.getByAcceptanceIdAndGoodId(acceptanceId.get(),goodId.get());
        return null;
    }

    /*@ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public AcceptanceItem getById(@PathVariable Long id) {
        return itemService.getById(id);
    }
*/
    @GetMapping("/add")
    public String showCreateItemForm(Model model) {
        model.addAttribute("item", new AcceptanceItemDto());
        return "create-acceptance-item";
    }
    @PostMapping("/add")
    String createAcceptanceItem(@ModelAttribute @Valid AcceptanceItemDto itemDto) {
         itemService.createNewAcceptanceItem(itemDto);
        return "redirect:/receptions";
    }

    @GetMapping("/update/{id}")
    public String shpwUpdateItemForm(@PathVariable @Valid Long id, Model model) {
        AcceptanceItem item = itemService.getById(id);
        model.addAttribute("item", item);
        return "update-acceptance-item";
    }
    @PostMapping("/update/{id}")
    String updateAcceptanceItem(@PathVariable @Valid Long id, @ModelAttribute AcceptanceItemDto itemDto) {
        itemService.updateAcceptanceItem(id, itemDto);
        return "redirect:/receptions";
    }

    @GetMapping("/delete/{id}")
    String deleteAcceptanceItem(@PathVariable @Valid Long id) {
        itemService.deleteAcceptanceItemById(id);
        return "redirect:/receptions";
    }
}

