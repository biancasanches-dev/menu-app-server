package br.com.menu.service.impl;

import br.com.menu.domain.dto.OrderCreateDto;
import br.com.menu.domain.dto.OrderProductDto;
import br.com.menu.domain.dto.OrderResponseDto;
import br.com.menu.domain.model.Order;
import br.com.menu.domain.model.OrderProduct;
import br.com.menu.domain.model.Product;
import br.com.menu.domain.model.User;
import br.com.menu.domain.model.enums.OrderStatus;
import br.com.menu.domain.repository.OrderRepository;
import br.com.menu.service.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductServiceImpl productServiceImpl;

    public OrderServiceImpl(OrderRepository orderRepository, ProductServiceImpl productServiceImpl) {
        this.orderRepository = orderRepository;
        this.productServiceImpl = productServiceImpl;
    }

    @Override
    public OrderResponseDto createOrder(OrderCreateDto order, User user) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        try {
            Order newOrder = new Order();
            newOrder.setUser(user);
            newOrder.setPayment(order.getPayment());
            newOrder.setStatus(OrderStatus.PENDING);
            newOrder.setCreatedAt(LocalDateTime.now());

            List<OrderProduct> orderProducts = new ArrayList<>();
            for (OrderProductDto productDto : order.getProducts()) {
                Product product = productServiceImpl.findProductById(productDto.getProductId());
                if (product == null) {
                    throw new IllegalArgumentException("Product not found with ID: " + productDto.getProductId());
                }
                OrderProduct orderProduct = new OrderProduct();
                orderProduct.setProduct(product);
                orderProduct.setQuantity(productDto.getQuantity());
                orderProduct.setTotalPrice(product.getPrice() * productDto.getQuantity());
                orderProduct.setOrder(newOrder);
                orderProducts.add(orderProduct);
            }
            newOrder.setProducts(orderProducts);

            newOrder.setTotalPrice(orderProducts.stream().mapToDouble(OrderProduct::getTotalPrice).sum());

            orderRepository.save(newOrder);
            return new OrderResponseDto(newOrder);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error creating order" + e.getMessage());
        }
    }

    @Override
    public OrderResponseDto updateOrderStatus(Long orderId, OrderStatus newStatus) {
        if (orderId == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        Order order = orderRepository.findById(orderId).orElseThrow();

        order.setStatus(newStatus);

        orderRepository.save(order);
        return new OrderResponseDto(order);
    }

    @Override
    public void deleteOrder(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        Order order = orderRepository.findById(id).orElseThrow(null);
        orderRepository.delete(order);
    }

    @Override
    public OrderResponseDto findOrderById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        return new OrderResponseDto(orderRepository.findById(id).orElseThrow());
    }

    @Override
    public List<OrderResponseDto> listOrdersByStatus(OrderStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }

        return orderRepository.findByStatus(status)
                .stream()
                .map(OrderResponseDto::new)
                .toList();
    }

    @Override
    public List<OrderResponseDto> listOrders() {
        return orderRepository.findAll()
                .stream()
                .map(OrderResponseDto::new)
                .toList();
    }
}
