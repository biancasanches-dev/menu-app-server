package br.com.menu.domain.dto;

import br.com.menu.domain.model.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDto {

    private String name;
    private String phone;
    private String password;
    private AddressDto addressDto;
}
