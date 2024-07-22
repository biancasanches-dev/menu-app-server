package br.com.menu.controller;

import br.com.menu.domain.dto.OrderCreateDto;
import br.com.menu.domain.dto.OrderResponseDto;
import br.com.menu.domain.model.User;
import br.com.menu.domain.model.enums.OrderStatus;
import br.com.menu.service.OrderService;
import br.com.menu.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getOrdersOpen() {
        return ResponseEntity.ok(orderService.listOrdersByStatus(OrderStatus.PENDING));
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderResponseDto>> getOrders() {
        return ResponseEntity.ok(orderService.listOrders());
    }

    @PostMapping("/user/{userId}/create")
    public ResponseEntity<OrderResponseDto> createOrder(@PathVariable Long userId, @RequestBody OrderCreateDto order) {
        User user = userService.findUserById(userId);
        return ResponseEntity.ok(orderService.createOrder(order, user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDto> updateOrderStatus(@PathVariable Long id, @RequestBody OrderStatus newStatus) {
        return ResponseEntity.ok(orderService.updateOrderStatus(id, newStatus));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok("Order deleted");
    }
}
