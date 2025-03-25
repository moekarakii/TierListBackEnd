package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    // ✅ Register new user
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        String response = userService.registerUser(user);
        if (response.equals("User registered successfully")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(400).body(response);
        }
    }

    // ✅ Login using username, return firstName, lastName, username
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        Optional<User> storedUserOpt = userService.login(user.getUsername(), user.getPassword());

        if (storedUserOpt.isEmpty()) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        User storedUser = storedUserOpt.get();
        Map<String, Object> response = new HashMap<>();
        response.put("id", storedUser.getId());
        response.put("firstName", storedUser.getFirstName());
        response.put("lastName", storedUser.getLastName());
        response.put("username", storedUser.getUsername());

        return ResponseEntity.ok(response);
    }

    // ✅ New endpoint to get user by username (for dashboard)
    @GetMapping("/user")
    public ResponseEntity<?> getUser(@RequestParam String username) {
        Optional<User> userOpt = userService.getUserByUsername(username);

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(404).body("User not found");
        }

        User user = userOpt.get();
        Map<String, Object> response = new HashMap<>();
        response.put("id", user.getId());
        response.put("firstName", user.getFirstName());
        response.put("lastName", user.getLastName());
        response.put("username", user.getUsername());

        return ResponseEntity.ok(response);
    }

    // ✅ Get all users
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // ✅ Update user by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        Optional<User> updated = userService.updateUser(id, updatedUser);
        return updated.isPresent()
                ? ResponseEntity.ok("User updated successfully")
                : ResponseEntity.status(404).body("User not found");
    }

    // ✅ Delete user by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        String response = userService.deleteUser(id);
        return response.equals("User deleted successfully")
                ? ResponseEntity.ok(response)
                : ResponseEntity.status(404).body(response);
    }
}
