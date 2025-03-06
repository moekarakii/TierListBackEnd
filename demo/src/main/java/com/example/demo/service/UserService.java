package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final List<User> users = new ArrayList<>();

    // this method is only for test (adding one user to the local list)
    public UserService() {
        users.add(new User("usui", "usui@example.com", "123456"));
    }

    // login auth (only check if the account exists)
    public boolean loginUser(String username, String password) {
        Optional<User> user = users.stream()
                .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
                .findFirst();
        return user.isPresent();
    }
}
