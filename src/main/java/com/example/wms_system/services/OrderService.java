package com.example.wms_system.services;

import com.example.wms_system.dto.OrderDto;
import com.example.wms_system.enums.OrderStatus;
import com.example.wms_system.enums.PaymentMethod;
import com.example.wms_system.exceptions.OrderNotFoundException;
import com.example.wms_system.models.Order;
import com.example.wms_system.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;

    public Order getById(Long id) {
        Optional<Order> loadOrder = orderRepository.findById(id);
        log.info("Loaded order with id: {}", id);
        return loadOrder.orElseThrow(() -> {
            log.error("Order with id: {} not found", id);
            return new OrderNotFoundException(id);
        });
    }

    public List<Order> getAll() {
        return printLogInfo(orderRepository.findAll());
    }

    public List<Order> getAllByCustomerId(Long id) {
        return printLogInfo(orderRepository.findAllByCustomerId(id));
    }

    public List<Order> getAllByStatus(OrderStatus status) {
        return printLogInfo(orderRepository.findAllByStatus(status));
    }

    public List<Order> getAllByPaymentMethod(PaymentMethod method) {
        return printLogInfo((orderRepository.findAllByPaymentMethod(method)));
    }

    public List<Order> getByIdInList(Long id) {
        return List.of(orderRepository.findById(id).get());
    }

    public List<Order> getAllByOrderDatePeriod(Date start, Date end) {
        if (start.after(end)) {
            throw new NumberFormatException("Start date can`t be after end date");
        }
        return printLogInfo(orderRepository.findAllInOrderPeriod(start, end));
    }

    @Transactional

    public Order createNewOrder(OrderDto orderDto) {
        Order order = orderDto.toOrderEntity();
        Order savedOrder = orderRepository.save(order);
        log.info("Saved order with id: {}", savedOrder.getId());
        return savedOrder;
    }

    @Transactional
    public Order updateOrder(Long id, OrderDto orderDto) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        return updateOrderFields(order, orderDto);
    }

    public void deleteOrderById(Long id) {
        if (getById(id) != null) {
            orderRepository.deleteById(id);
            log.info("Delete order by id: {}", id);
        } else {
            throw new OrderNotFoundException(id);
        }
    }

    private Order updateOrderFields(Order order, OrderDto orderDto) {
        Order updatedOrder = order.updateFields(orderDto);
        log.info("Updated order with id: {}", updatedOrder.getId());
        return updatedOrder;
    }

    private List<Order> printLogInfo(List<Order> orders) {
        log.info("Size of loaded orders from database: {}", orders.size());
        return orders;
    }
}
