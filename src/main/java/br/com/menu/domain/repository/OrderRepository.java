package br.com.menu.domain.repository;

import br.com.menu.domain.model.Order;
import br.com.menu.domain.model.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    public List<Order> findByStatus(OrderStatus status);
}
