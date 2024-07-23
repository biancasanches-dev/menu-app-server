package br.com.menu.domain.model;

import br.com.menu.domain.dto.AddressDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "tab_addresses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    private String street;
    private String number;
    private String complement;
    private String district;
    private String city;

    public Address(AddressDto addressDto, User user) {
        this.street = addressDto.getStreet();
        this.number = addressDto.getNumber();
        this.complement = addressDto.getComplement();
        this.district = addressDto.getDistrict();
        this.city = addressDto.getCity();
        this.user = user;
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }
}
