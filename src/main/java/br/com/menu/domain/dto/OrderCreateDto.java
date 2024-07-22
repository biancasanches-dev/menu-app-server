package br.com.menu.domain.dto;

import br.com.menu.domain.model.enums.Payment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateDto {

    private List<OrderProductDto> products;
    private Double totalPrice;
    private Payment payment;

}
