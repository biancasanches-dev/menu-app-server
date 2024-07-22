package br.com.menu.domain.dto;

import br.com.menu.domain.model.Order;
import br.com.menu.domain.model.OrderProduct;
import br.com.menu.domain.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductDto {

        private Long productId;
        private Integer quantity;

}
