package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173") // Allow frontend access
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Handle OPTIONS requests for CORS
    @RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptions() {
        return ResponseEntity.ok().build();
    }

    /**
     * Handles user registration.
     * Accepts a JSON request containing username, email, and password.
     * Returns a success message if registration is successful.
     */
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        String response = userService.registerUser(user);

        if (response.equals("User registered successfully")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(400).body(response);
        }
    }

    /**
     * Handles user login.
     * Accepts a JSON request with email and password.
     * Returns a success message if credentials are valid, otherwise returns an error.
     */
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        boolean isAuthenticated = userService.loginUser(user.getEmail(), user.getPassword());

        return isAuthenticated
                ? ResponseEntity.ok("Login successful")
                : ResponseEntity.status(401).body("Invalid credentials");
    }

    /**
     * Retrieves all registered users.
     * Returns a list of users currently stored.
     */
    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    /**
     * Updates user information based on email.
     * Accepts a JSON request with updated user data.
     */
    @PutMapping("/update/{email}")
    public ResponseEntity<String> updateUser(@PathVariable String email, @RequestBody User updatedUser) {
        String response = userService.updateUser(email, updatedUser);

        return response.equals("User updated successfully")
                ? ResponseEntity.ok(response)
                : ResponseEntity.status(404).body(response);
    }

    /**
     * Deletes a user based on email.
     * Returns success or failure message.
     */
    @DeleteMapping("/delete/{email}")
    public ResponseEntity<String> deleteUser(@PathVariable String email) {
        String response = userService.deleteUser(email);

        return response.equals("User deleted successfully")
                ? ResponseEntity.ok(response)
                : ResponseEntity.status(404).body(response);
    }
}