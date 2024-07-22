package br.com.menu.service.impl;

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
    public User createUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (userRepository.findAll().contains(user)) {
            throw new IllegalArgumentException("User already exists");
        }
        userRepository.save(user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (!userRepository.findAll().contains(user)) {
            throw new IllegalArgumentException("User does not exist");
        }
        userRepository.save(user);
        return user;
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
    public List<User> listUsers() {
        return userRepository.findAll();
    }


}
