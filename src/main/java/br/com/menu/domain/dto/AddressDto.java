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
public class AddressDto {

        private String street;
        private String number;
        private String complement;
        private String district;
        private String city;

        public AddressDto(Address address) {
            this.street = address.getStreet();
            this.number = address.getNumber();
            this.complement = address.getComplement();
            this.district = address.getDistrict();
            this.city = address.getCity();
        }
}
