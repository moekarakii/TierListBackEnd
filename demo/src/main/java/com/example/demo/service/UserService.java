package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final TierComparisonService tierComparisonService;

    public UserService(UserRepository userRepository, TierComparisonService tierComparisonService) {
        this.userRepository = userRepository;
        this.tierComparisonService = tierComparisonService;
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

    /**
     * Fetch all users, compute embeddings, group them by similarity, and store the
     * groupId in each User.
     */
    public void groupUsersByTiers() {
        List<User> allUsers = userRepository.findAll();

        // 1) Compute embeddings for each user
        Map<Long, List<Double>> userEmbeddings = new HashMap<>();
        for (User user : allUsers) {
            List<Double> embedding = tierComparisonService.getTierListEmbedding(user.getTierList(), user.getCategory());
            userEmbeddings.put(user.getId(), embedding);
        }

        // 2) Group users using a naive threshold approach
        double threshold = 0.9;
        Map<Long, Integer> userToGroup = new HashMap<>();
        int nextGroupId = 1;

        for (User user : allUsers) {
            if (!userToGroup.containsKey(user.getId())) {
                // Assign a new group to this user
                userToGroup.put(user.getId(), nextGroupId);

                // Compare with others
                List<Double> embedding1 = userEmbeddings.get(user.getId());
                for (User other : allUsers) {
                    if (!other.getId().equals(user.getId()) && !userToGroup.containsKey(other.getId())) {
                        List<Double> embedding2 = userEmbeddings.get(other.getId());
                        double similarity = tierComparisonService.cosineSimilarity(embedding1, embedding2);
                        if (similarity >= threshold) {
                            userToGroup.put(other.getId(), nextGroupId);
                        }
                    }
                }
                nextGroupId++;
            }
        }

        // 3) Persist the group IDs back to the database
        for (User user : allUsers) {
            int groupId = userToGroup.get(user.getId());
            user.setGroupId("Group " + groupId);
            userRepository.save(user);
        }
    }

    /**
     * Fetch the user's group from the DB (after grouping is done).
     */
    public String getUserGroup(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found: " + userId));
        return user.getGroupId();
    }
}
