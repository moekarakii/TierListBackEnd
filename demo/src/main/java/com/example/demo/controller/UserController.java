package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    

    /**
     * Endpoint to trigger grouping.
     * E.g., POST /api/users/group
     */
    @PostMapping("/group")
    public void groupAllUsers() {
        userService.groupUsersByTiers();
    }

    /**
     * Endpoint to see what group a user belongs to.
     * E.g., GET /api/users/5/group
     */
    @GetMapping("/{id}/group")
    public String getUserGroup(@PathVariable Long id) {
        return userService.getUserGroup(id);
    }
}
