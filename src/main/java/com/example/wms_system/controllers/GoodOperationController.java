package com.example.wms_system.controllers;

import com.example.wms_system.dto.GoodOperationDto;
import com.example.wms_system.dto.GoodOrderDto;
import com.example.wms_system.models.GoodOperation;
import com.example.wms_system.models.GoodOrder;
import com.example.wms_system.services.GoodOperationService;
import com.example.wms_system.services.GoodOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/operation-goods")
public class GoodOperationController {

    private final GoodOperationService goodOperationService;

    @GetMapping("{id}")
    public String getAllGoodsByOperation(@PathVariable Long id, Model model){
        List<GoodOperation> goodOperations = goodOperationService.getAllGoodsByOperation(id);
        model.addAttribute("goodOperations", goodOperations);
        return "good-operations";
    }

    @GetMapping("/add")
    public String showCreateGoodOperationForm(Model model) {
        model.addAttribute("goodOperation", new GoodOperationDto());
        return "create-good-operation";
    }

    @PostMapping("/add")
    public String createGoodOperation(@ModelAttribute @Valid GoodOperationDto operationDto) {
        goodOperationService.createNewOperationGood(operationDto);
        return "redirect:/operations";
    }

    @GetMapping("/update/{id}")
    public String showUpdateGoodOperationForm(@PathVariable Long id, Model model) {
        GoodOperation goodOperation = goodOperationService.getById(id);
        model.addAttribute("goodOperation", goodOperation);
        return "update-good-operation";

    }

    @PostMapping("/update/{id}")
    public String updateGoodOperation(@PathVariable Long id, @ModelAttribute @Valid GoodOperationDto operationDto) {
        goodOperationService.updateGoodOperation(id,operationDto);
        return "redirect:/operations";
    }

    @GetMapping("/delete/{id}")
    public String deleteGoodOperation(@PathVariable Long id) {
        goodOperationService.deleteGoodOperationById(id);
        return "redirect:/operations";
    }
}
