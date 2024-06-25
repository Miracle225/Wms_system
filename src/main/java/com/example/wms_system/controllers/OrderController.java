package com.example.wms_system.controllers;

import com.example.wms_system.dto.OrderDto;
import com.example.wms_system.enums.*;
import com.example.wms_system.models.Order;
import com.example.wms_system.services.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public String getAllOrders(
            @RequestParam Optional<String> status,
            @RequestParam Optional<String> method,
            @RequestParam Optional<String> customerId,
            @RequestParam Optional<String> start,
            @RequestParam Optional<String> end,
            @RequestParam Optional<String> Id,
            Model model
    ) {
        List<Order> orders = orderService.getAll();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (status.isPresent() && !status.get().equals("ALL")) {
            try {
                OrderStatus orderStatus = OrderStatus.valueOf(status.get().toUpperCase());
                orders = orderService.getAllByStatus(orderStatus);
            } catch (IllegalArgumentException e) {
                model.addAttribute("error", "Invalid status value");
            }
        }
        if (method.isPresent() && !method.get().equals("ALL")) {
            try {
                PaymentMethod paymentMethod = PaymentMethod.valueOf(method.get().toUpperCase());
                //orders = orders.stream().filter(order -> false).collect(Collectors.toList());
                orders = orderService.getAllByPaymentMethod(paymentMethod);
            } catch (IllegalArgumentException e) {
                model.addAttribute("error", "Invalid payment method value");
            }
        }
        if (customerId.isPresent()) {
            try {
                Long customerIdLong = Long.parseLong(customerId.get());
                orders = orderService.getAllByCustomerId(customerIdLong);
            } catch (IllegalArgumentException e) {
                model.addAttribute("error", "Invalid Customer id value");
            }
        }
        if (Id.isPresent()) {
            try {
                Long orderId = Long.parseLong(Id.get());
                orders = orderService.getByIdInList(orderId);
            } catch (IllegalArgumentException e) {
                model.addAttribute("error", "Invalid Customer id value");
            }
        }
        if (start.isPresent() && end.isPresent() && !start.get().isBlank() && !end.get().isBlank()) {
            try {
                Date startDate = dateFormat.parse(start.get());
                Date endDate = dateFormat.parse(end.get());

                // Set time to start of the day for the startDate
                Calendar startCalendar = Calendar.getInstance();
                startCalendar.setTime(startDate);
                startCalendar.set(Calendar.HOUR_OF_DAY, 0);
                startCalendar.set(Calendar.MINUTE, 0);
                startCalendar.set(Calendar.SECOND, 0);
                startCalendar.set(Calendar.MILLISECOND, 0);

                // Set time to end of the day for the endDate
                Calendar endCalendar = Calendar.getInstance();
                endCalendar.setTime(endDate);
                endCalendar.set(Calendar.HOUR_OF_DAY, 23);
                endCalendar.set(Calendar.MINUTE, 59);
                endCalendar.set(Calendar.SECOND, 59);
                endCalendar.set(Calendar.MILLISECOND, 999);
                orders = orderService.getAllByOrderDatePeriod(startCalendar.getTime(), endCalendar.getTime());
            } catch (IllegalArgumentException e) {
                model.addAttribute("error", "Start or end date are invalid");
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        model.addAttribute("orders", orders);
        return "orders";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Order getById(@PathVariable Long id) {
        return orderService.getById(id);
    }

    @GetMapping("/add")
    public String showCreateGoodForm(Model model) {
        model.addAttribute("order", new OrderDto());
        model.addAttribute("methods", PaymentMethod.values());
        model.addAttribute("statuses", OrderStatus.values());
        return "create-order";
    }
    @PostMapping("/add")
    public String createOrder(@ModelAttribute @Valid OrderDto orderDto) {
         orderService.createNewOrder(orderDto);
        return "redirect:/orders";
    }

    @GetMapping("/update/{id}")
    public String showCreateGoodForm(@PathVariable Long id, Model model) {
        Order order = orderService.getById(id);
        model.addAttribute("order", order);
        model.addAttribute("methods", PaymentMethod.values());
        model.addAttribute("statuses", OrderStatus.values());
        return "update-order";
    }
    @PostMapping("/update/{id}")
    public String updateOrder(@PathVariable Long id, @ModelAttribute @Valid OrderDto orderDto) {
         orderService.updateOrder(id, orderDto);
        return "redirect:/orders";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteOrderById(id);
        return "redirect:/orders";
    }
}
