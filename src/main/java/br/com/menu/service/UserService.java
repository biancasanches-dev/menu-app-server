package br.com.menu.service;

import br.com.menu.domain.dto.AddressDto;
import br.com.menu.domain.dto.UserCreateDto;
import br.com.menu.domain.dto.UserResponseDto;
import br.com.menu.domain.model.User;

import java.util.List;

public interface UserService {

    User createUser(UserCreateDto userDto);
    User updateUser(UserCreateDto userDto, Long id);
    User updateAddress(AddressDto addressDto, Long id);
    void deleteUser(Long id);
    User findUserById(Long id);
    List<UserResponseDto> listUsers();
}
