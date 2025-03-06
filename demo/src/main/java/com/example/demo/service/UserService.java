package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final List<User> users = new ArrayList<>();

    // for test only (add one user)
    public UserService() {
        users.add(new User("usui", "usui@example.com", "123456"));
    }

    // login auth (check if account exists)
    public boolean loginUser(String username, String password) {
        return users.stream()
                .anyMatch(user -> user.getUsername().equals(username) && user.getPassword().equals(password));
    }

    // return users
    public List<User> getAllUsers() {
        return users;
    }
}

