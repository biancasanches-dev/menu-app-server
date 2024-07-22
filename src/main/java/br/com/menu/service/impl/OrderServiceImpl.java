package br.com.menu.service.impl;

import br.com.menu.domain.model.Order;
import br.com.menu.domain.repository.OrderRepository;
import br.com.menu.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        if (orderRepository.findAll().contains(order)) {
            throw new IllegalArgumentException("Order already exists");
        }
        orderRepository.save(order);
        return order;
    }

    @Override
    public Order updateOrderStatus(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        if (!orderRepository.findAll().contains(order)) {
            throw new IllegalArgumentException("Order does not exist");
        }
        orderRepository.save(order);
        return null;
    }

    @Override
    public void deleteOrder(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        Order order = findOrderById(id);
        orderRepository.delete(order);
    }

    @Override
    public Order findOrderById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        return orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Order does not exist"));
    }

    @Override
    public List<Order> listOrders() {
        return orderRepository.findAll();
    }
}
