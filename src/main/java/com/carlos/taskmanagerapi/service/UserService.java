package com.carlos.taskmanagerapi.service;

import com.carlos.taskmanagerapi.model.User;
import com.carlos.taskmanagerapi.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.carlos.taskmanagerapi.model.Role;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository repository,
                       PasswordEncoder passwordEncoder) {

        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public User save(User user) {

        user.setRole(Role.USER);

        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        return repository.save(user);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
