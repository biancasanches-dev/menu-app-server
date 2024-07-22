package br.com.menu.domain.dto;

import br.com.menu.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDto {

    private Long id;
    private String name;
    private String phone;
    private AddressDto address;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.phone = user.getPhone();
        this.address = new AddressDto(user.getAddress());
    }
}
