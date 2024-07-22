package br.com.menu.service;

import br.com.menu.domain.model.Order;

import java.util.List;

public interface OrderService {

        Order createOrder(Order order);
        Order updateOrderStatus(Order order);
        void deleteOrder(Long id);
        Order findOrderById(Long id);
        List<Order> listOrders();
}
