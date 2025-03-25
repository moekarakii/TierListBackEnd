package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    // Primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Unique username
    @Column(nullable = false, unique = true)
    private String username;

    // User's email address
    @Column(nullable = false, unique = true)
    private String email;

    // User's password
    @Column(nullable = false)
    private String password;

    // User's first name
    @Column(name = "first_name", nullable = false)
    private String firstName;

    // User's last name
    @Column(name = "last_name", nullable = false)
    private String lastName;

    /**
     * A text field to store the user's tier list as JSON.
     * Example JSON structure could be something like:
     * {
     * u
     * "S Tier": ["Apple", "Orange"],
     * "A Tier": ["Banana"]
     * }
     * 
     * JSON to Map<String, List<String>> in service code.
     */
    @Lob
    @Column(name = "tier_list_json")
    private String tierListJson;
    private String category;
    @Column(name = "group_id")
    private String groupId;

    public User() {
    }

    public User(String username, String email, String password,
            String firstName, String lastName, String tierListJson,
            String category, String groupId) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.tierListJson = tierListJson;
        this.category = category;
        this.groupId = groupId;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTierListJson() {
        return tierListJson;
    }

    public void setTierListJson(String tierListJson) {
        this.tierListJson = tierListJson;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getTierList() {
        return tierListJson;
    }
}
