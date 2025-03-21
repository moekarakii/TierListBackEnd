package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// This interface provides built-in CRUD operations for the User entity
public interface UserRepository extends JpaRepository<User, Long> {

    // Custom method to find a user by username and password (used for login)
    Optional<User> findByUsernameAndPassword(String username, String password);

    // Check if a user exists by username
    Optional<User> findByUsername(String username);

    // Check if a user exists by email
    Optional<User> findByEmail(String email);
}

