package com.example.wms_system.controllers;

import com.example.wms_system.dto.GoodOrderDto;
import com.example.wms_system.models.GoodOrder;
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
@RequestMapping("/order-goods")
public class GoodOrderController {

    private final GoodOrderService goodOrderService;

    @GetMapping("{id}")
    public String getAllGoodsByOrder(@PathVariable Long id, Model model){
        List<GoodOrder> goodOrders = goodOrderService.getAllGoodsByOrder(id);
        model.addAttribute("goodOrders", goodOrders);
        return "good-orders";
    }

    @GetMapping("/add")
    public String showCreateGoodOrderForm(Model model) {
        model.addAttribute("goodOrder", new GoodOrderDto());
        return "create-good-order";
    }

    @PostMapping("/add")
    public String createGoodOrder(@ModelAttribute @Valid GoodOrderDto goodDto) {
        goodOrderService.createNewOrderGood(goodDto);
        return "redirect:/orders";
    }

    @GetMapping("/update/{id}")
    public String showUpdateGoodOrderForm(@PathVariable Long id, Model model) {
        GoodOrder goodOrder = goodOrderService.getById(id);
        model.addAttribute("goodOrder", goodOrder);
        return "update-good-order";

    }

    @PostMapping("/update/{id}")
    public String updateGood(@PathVariable Long id, @ModelAttribute @Valid GoodOrderDto goodDto) {
        goodOrderService.updateGoodOrder(id, goodDto);
        return "redirect:/orders";
    }

    @GetMapping("/delete/{id}")
    public String deleteGood(@PathVariable Long id) {
        goodOrderService.deleteGoodOrderById(id);
        return "redirect:/orders";
    }
}
