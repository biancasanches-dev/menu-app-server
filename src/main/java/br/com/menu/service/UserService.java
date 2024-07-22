package br.com.menu.service;

import br.com.menu.domain.model.User;

import java.util.List;

public interface UserService {

    User createUser(User user);
    User updateUser(User user);
    void deleteUser(Long id);
    User findUserById(Long id);
    List<User> listUsers();
}
