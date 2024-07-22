package br.com.menu.domain.dto;

import br.com.menu.domain.model.Order;
import br.com.menu.domain.model.OrderProduct;
import br.com.menu.domain.model.enums.OrderStatus;
import br.com.menu.domain.model.enums.Payment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderResponseDto implements Comparable<OrderResponseDto> {

    private Long id;
    private UserResponseDto user;
    private List<OrderProduct> products;
    private Double totalPrice;
    private OrderStatus status;
    private Payment payment;
    private LocalDateTime createdAt = LocalDateTime.now();

    public OrderResponseDto(Order order) {
        this.id = order.getId();
        this.user = new UserResponseDto(order.getUser());
        this.products = order.getProducts();
        this.totalPrice = order.getTotalPrice();
        this.status = order.getStatus();
        this.payment = order.getPayment();
    }

    @Override
    public int compareTo(OrderResponseDto o) {
        return this.createdAt.isAfter(o.createdAt) ? -1 : this.createdAt.isBefore(o.createdAt) ? 1 : 0;
    }
}
