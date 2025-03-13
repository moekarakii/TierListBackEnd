package com.example.demo.controller;

import com.example.demo.model.TierList;
import com.example.demo.service.TierListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tierlist")
@CrossOrigin(origins = {"http://localhost:5173", "https://project2db.herokuapp.com"}) // Allow frontend access
public class TierListController {

    private final TierListService tierListService;

    public TierListController(TierListService tierListService) {
        this.tierListService = tierListService;
    }

    // Create a new Tier List
    @PostMapping("/create")
    public ResponseEntity<String> createTierList(@RequestBody TierList tierList) {
        tierListService.createTierList(tierList);
        return ResponseEntity.ok("Tier List created successfully!");
    }

    // Get all Tier Lists
    @GetMapping("/all")
    public ResponseEntity<List<TierList>> getAllTierLists() {
        return ResponseEntity.ok(tierListService.getAllTierLists());
    }

    // Update an existing Tier List
    @PutMapping("/update")
    public ResponseEntity<String> updateTierList(@RequestBody TierList updatedTierList) {
        String response = tierListService.updateTierList(updatedTierList);
        return ResponseEntity.ok(response);
    }

    // Delete a Tier List by name
    @DeleteMapping("/delete/{name}")
    public ResponseEntity<String> deleteTierList(@PathVariable String name) {
        String response = tierListService.deleteTierList(name);
        return ResponseEntity.ok(response);
    }
}



