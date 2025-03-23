package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Register user if email and username are unique
    public String registerUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return "Username already in use";
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return "Email already in use";
        }

        userRepository.save(user);
        return "User registered successfully";
    }

    // Login using username and password
    public Optional<User> login(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    // Get user by username
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Update user by ID
    public Optional<User> updateUser(Long id, User updatedUser) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) {
            return Optional.empty();
        }

        User user = userOpt.get();
        user.setUsername(updatedUser.getUsername());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());

        return Optional.of(userRepository.save(user));
    }

    // Delete user by ID
    public String deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            return "User not found";
        }
        userRepository.deleteById(id);
        return "User deleted successfully";
    }
}
