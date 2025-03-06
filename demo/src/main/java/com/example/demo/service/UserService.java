package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final List<User> users = new ArrayList<>();

    // test only, add one default user
    public UserService() {
        users.add(new User("test", "test@example.com", "123456"));
    }

    // login auth
    public boolean loginUser(String username, String password) {
        return users.stream()
                .anyMatch(user -> user.getUsername().equals(username) && user.getPassword().equals(password));
    }

    // user register
    public String registerUser(User user) {
        // check if user already exists
        Optional<User> existingUser = users.stream()
                .filter(u -> u.getUsername().equals(user.getUsername()))
                .findFirst();

        if (existingUser.isPresent()) {
            return "Username already exists!";
        }

        users.add(user);
        return "User registered successfully!";
    }

    // return all users
    public List<User> getAllUsers() {
        return users;
    }
}


