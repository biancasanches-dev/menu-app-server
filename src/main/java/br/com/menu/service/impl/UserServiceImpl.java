package br.com.menu.service.impl;

import br.com.menu.domain.dto.AddressDto;
import br.com.menu.domain.dto.UserCreateDto;
import br.com.menu.domain.dto.UserResponseDto;
import br.com.menu.domain.model.Address;
import br.com.menu.domain.model.User;
import br.com.menu.domain.repository.UserRepository;
import br.com.menu.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(UserCreateDto userDto) {
        if (userRepository.findAll().contains(userDto.getPhone())) {
            throw new IllegalArgumentException("Phone already exists");
        }
        try {
            User user = new User();
            user.setName(userDto.getName());
            user.setPhone(userDto.getPhone());
            user.setPassword(userDto.getPassword());
            user.setAddress(new Address(userDto.getAddressDto(), user));
            user.setOrders(null);

            userRepository.save(user);
            return user;
        } catch (Exception e) {
            throw new IllegalArgumentException("Error updating user: " + e.getMessage());
        }
    }

    @Override
    public User updateUser(UserCreateDto newUser, Long id) {
        User user = findUserById(id);
        System.out.println(user.getName());
        try {
            if (!user.getName().equals(newUser.getName())) {
                user.setName(newUser.getName());
            }
            if (!user.getPhone().equals(newUser.getPhone())) {
                user.setPhone(newUser.getPhone());
            }
            if (!user.getPassword().equals(newUser.getPassword())) {
                user.setPassword(newUser.getPassword());
            }

            userRepository.save(user);

            return user;
        } catch (Exception e) {
            throw new IllegalArgumentException("Error updating user: " + e.getMessage());
        }
    }

    @Override
    public User updateAddress(AddressDto addressDto, Long id) {
        User user = findUserById(id);

        try {
            user.getAddress().setCity(addressDto.getCity());
            user.getAddress().setStreet(addressDto.getStreet());
            user.getAddress().setNumber(addressDto.getNumber());
            user.getAddress().setComplement(addressDto.getComplement());
            user.getAddress().setDistrict(addressDto.getDistrict());

            userRepository.save(user);

            return user;
        } catch (Exception e) {
            throw new IllegalArgumentException("Error updating address: " + e.getMessage());
        }
    }

    @Override
    public User findUserById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User does not exist"));
    }

    @Override
    public void deleteUser(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        User user = findUserById(id);
        userRepository.delete(user);
    }

    @Override
    public List<UserResponseDto> listUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDto::new)
                .toList();
    }


}
