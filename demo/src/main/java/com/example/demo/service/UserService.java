package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final List<User> users = new ArrayList<>();

    /**
     * Registers a new user by adding them to the list.
     * Ensures that the email is unique.
     */
    public String registerUser(User user) {
        // Check if email already exists
        for (User existingUser : users) {
            if (existingUser.getEmail().equals(user.getEmail())) {
                return "Email already in use";
            }
        }
        users.add(user);
        return "User registered successfully";
    }

    /**
     * Authenticates a user based on email and password.
     * Returns true if the email and password match an existing user.
     */
    public boolean loginUser(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves all registered users.
     */
    public List<User> getAllUsers() {
        return users;
    }

    /**
     * Updates a user's information based on email.
     * Returns a success message if the user is found and updated.
     */
    public String updateUser(String email, User updatedUser) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                user.setUsername(updatedUser.getUsername());
                user.setPassword(updatedUser.getPassword());
                return "User updated successfully";
            }
        }
        return "User not found";
    }

    /**
     * Deletes a user based on email.
     * Returns a success message if the user is found and removed.
     */
    public String deleteUser(String email) {
        return users.removeIf(user -> user.getEmail().equals(email))
                ? "User deleted successfully"
                : "User not found";
    }
}