package com.example.wms_system.controllers;

import com.example.wms_system.dto.OrderDto;
import com.example.wms_system.enums.OrderStatus;
import com.example.wms_system.enums.PaymentMethod;
import com.example.wms_system.models.Order;
import com.example.wms_system.services.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/orders")
public class OrderController {
    private final OrderService orderService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Order> getAllOrders(
            @RequestParam Optional<OrderStatus> status,
            @RequestParam Optional<PaymentMethod> method,
            @RequestParam Optional<Long> customerId,
            @RequestParam Optional<Date> start,
            @RequestParam Optional<Date> end
    ) {
        if (status.isPresent())
            return orderService.getAllByStatus(status.get());
        else if (method.isPresent())
            return orderService.getAllByPaymentMethod(method.get());
        else if (customerId.isPresent())
            return orderService.getAllByCustomerId(customerId.get());
        else if (start.isPresent() && end.isPresent()) {
            return orderService.getAllByOrderDatePeriod(start.get(), end.get());
        }
        return orderService.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Order getById(@PathVariable Long id){
        return orderService.getById(id);
    }

    @PostMapping()
    ResponseEntity<?> createOrder(@RequestBody @Valid OrderDto orderDto){
        var order = orderService.createNewOrder(orderDto);
        return new ResponseEntity<>(order,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateOrder(@PathVariable Long id, @RequestBody @Valid OrderDto orderDto){
        var order = orderService.updateOrder(id,orderDto);
        return new ResponseEntity<>(order,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteOrder(@PathVariable Long id){
        orderService.deleteOrderById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
