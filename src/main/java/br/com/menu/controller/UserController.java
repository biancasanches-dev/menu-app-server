package br.com.menu.controller;

import br.com.menu.domain.dto.AddressDto;
import br.com.menu.domain.dto.UserCreateDto;
import br.com.menu.domain.dto.UserResponseDto;
import br.com.menu.domain.model.User;
import br.com.menu.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController (UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getUsers() {
        return ResponseEntity.ok(userService.listUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }


    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserCreateDto userDto) {
        return ResponseEntity.ok(userService.createUser(userDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestBody UserCreateDto updatedUser, @PathVariable Long id) {
        userService.findUserById(id);
        return ResponseEntity.ok(userService.updateUser(updatedUser, id));
    }

    @PutMapping("/{id}/address")
    public ResponseEntity<User> updateAddress(@RequestBody AddressDto addressDto, @PathVariable Long id) {
        userService.findUserById(id);
        return ResponseEntity.ok(userService.updateAddress(addressDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted");
    }

}
