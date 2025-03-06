package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // login
    @PostMapping("/login")
    public String loginUser(@RequestBody User user) {
        boolean isValid = userService.loginUser(user.getUsername(), user.getPassword());
        return isValid ? "Login successful!" : "Invalid username or password";
    }
}

