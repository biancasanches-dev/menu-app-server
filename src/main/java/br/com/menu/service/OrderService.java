package br.com.menu.service;

import br.com.menu.domain.dto.OrderCreateDto;
import br.com.menu.domain.dto.OrderResponseDto;
import br.com.menu.domain.model.Order;
import br.com.menu.domain.model.User;
import br.com.menu.domain.model.enums.OrderStatus;

import java.util.List;

public interface OrderService {

        OrderResponseDto createOrder(OrderCreateDto order, User user);
        OrderResponseDto updateOrderStatus(Long id, OrderStatus newStatus);
        void deleteOrder(Long id);
        OrderResponseDto findOrderById(Long id);
        List<OrderResponseDto> listOrdersByStatus(OrderStatus status);
        List<OrderResponseDto> listOrders();
}
