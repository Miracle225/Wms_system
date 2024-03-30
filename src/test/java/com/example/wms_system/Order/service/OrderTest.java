package com.example.wms_system.Order.service;

import com.example.wms_system.Order.entity.OrderDtoData;
import com.example.wms_system.Order.entity.OrderEntityData;
import com.example.wms_system.dto.OrderDto;
import com.example.wms_system.enums.OrderStatus;
import com.example.wms_system.enums.PaymentMethod;
import com.example.wms_system.exceptions.OrderNotFoundException;
import com.example.wms_system.models.Order;
import com.example.wms_system.repositories.OrderRepository;
import com.example.wms_system.services.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderTest {

    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    OrderService orderService;

    @Test
    void should_return_all_orders() {
        List<Order> order = OrderEntityData.getListOfOrder();
        when(orderRepository.findAll()).thenReturn(order);
        List<Order> expected = orderService.getAll();

        assertEquals(expected, order);
        verify(orderRepository).findAll();
    }

    @Test
    void should_return_all_orders_by_payment_method() {
        List<Order> orders = OrderEntityData.getListOfOrder();
        when(orderRepository.findAllByPaymentMethod(PaymentMethod.CASH)).thenReturn(orders);
        List<Order> expected = orderService.getAllByPaymentMethod(PaymentMethod.CASH);

        assertEquals(expected, orders);
        verify(orderRepository).findAllByPaymentMethod(PaymentMethod.CASH);
    }
    @Test
    void should_return_all_orders_by_status() {
        List<Order> orders = OrderEntityData.getListOfOrder();
        when(orderRepository.findAllByStatus(OrderStatus.NEW)).thenReturn(orders);
        List<Order> expected = orderService.getAllByStatus(OrderStatus.NEW);

        assertEquals(expected, orders);
        verify(orderRepository).findAllByStatus(OrderStatus.NEW);
    }
    @Test
    void should_return_all_orders_by_customer() {
        List<Order> orders = OrderEntityData.getListOfOrder();
        when(orderRepository.findAllByCustomerId(1L)).thenReturn(orders);
        List<Order> expected = orderService.getAllByCustomerId(1L);

        assertEquals(expected, orders);
        verify(orderRepository).findAllByCustomerId(1L);
    }

    @Test
    void when_given_id_should_return_order_if_found() {
        //given
        Order order = OrderEntityData.getSingleOrderWithId();
        //when
        when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));
        orderService.deleteOrderById(order.getId());

        //then
        verify(orderRepository).deleteById(order.getId());
    }

    @Test()
    void should_throw_exception_when_order_doesnt_exist() {
        //given
        Order order = OrderEntityData.getSingleOrderWithId();

        //when
        when(orderRepository.findById(anyLong())).thenReturn(Optional.empty());
        OrderNotFoundException exception = assertThrows(OrderNotFoundException.class, () ->
               orderService.getById(order.getId()));
        //then
        assertTrue(exception.getMessage().contains("Order with this id{ 1 } not found!"));
    }


    @Test
    void when_save_order_should_return_order() {

        //given
        OrderDto orderDto = OrderDtoData.getOrder();
        Order order = OrderEntityData.getSingleOrder();

        //when
        when(orderRepository.save(any(Order.class))).thenReturn(order);
        Order created = orderService.createNewOrder(orderDto);

        //then
        assertThat(created.getStatus()).isSameAs(orderDto.getStatus());
    }


    @Test
    void when_given_id_should_update_order_if_found() {
        //given
        Order order = OrderEntityData.getSingleOrderWithId();
        OrderDto updatedOrder = OrderDtoData.getUpdatedOrder();
        Order order1 = updatedOrder.toOrderEntity();
        order1.setId(1L);

        //when
        when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));
        orderService.updateOrder(order.getId(), updatedOrder);


        //then
        verify(orderRepository).findById(order.getId());
    }

    @Test()
    void should_throw_exception_when_update_order_doesnt_exist() {
        //given
       Order order = OrderEntityData.getSingleOrderWithId();
        OrderDto orderDto = OrderDtoData.getUpdatedOrder();

        //when
        when(orderRepository.findById(anyLong())).thenReturn(Optional.empty());
        OrderNotFoundException exception = assertThrows(OrderNotFoundException.class, () ->
                orderService.updateOrder(order.getId(), orderDto));

        //then
        assertTrue(exception.getMessage().contains("Order with this id{ 1 } not found!"));

    }

    //delete user
    @Test
    void when_given_id_should_delete_order_if_found() {
        //given
        Order order = OrderEntityData.getSingleOrderWithId();

        when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));
        orderService.deleteOrderById(order.getId());

        //then
        verify(orderRepository).deleteById(order.getId());
    }

    @Test()
    void should_throw_exception_when_delete_order_doesnt_exist() {
        //given
        Order order = OrderEntityData.getSingleOrderWithId();

        //when
        when(orderRepository.findById(anyLong())).thenReturn(Optional.empty());
        OrderNotFoundException exception = assertThrows(OrderNotFoundException.class, () ->
                orderService.deleteOrderById(order.getId()));

        //then
        assertTrue(exception.getMessage().contains("Order with this id{ 1 } not found!"));
    }
}
