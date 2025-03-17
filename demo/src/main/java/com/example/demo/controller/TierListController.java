package com.example.demo.controller;

import com.example.demo.model.TierList;
import com.example.demo.service.TierListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tierlist")
@CrossOrigin(origins = "http://localhost:5173") // Allow frontend requests
public class TierListController {

    private final TierListService tierListService;

    public TierListController(TierListService tierListService) {
        this.tierListService = tierListService;
    }

    // Handle OPTIONS requests for CORS
    @RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptions() {
        return ResponseEntity.ok().build();
    }

    /**
     * Create a new Tier List.
     * @param tierList The Tier List object containing name and items.
     * @return Response message indicating success or failure.
     */
    @PostMapping("/create")
    public ResponseEntity<String> createTierList(@RequestBody TierList tierList) {
        String result = tierListService.createTierList(tierList);
        return ResponseEntity.ok(result);
    }

    /**
     * Retrieve all existing Tier Lists.
     * @return List of all Tier Lists.
     */
    @GetMapping("/all")
    public ResponseEntity<List<TierList>> getAllTierLists() {
        List<TierList> tierLists = tierListService.getAllTierLists();
        return ResponseEntity.ok(tierLists);
    }

    /**
     * Retrieve a specific Tier List by name.
     * @param name The name of the Tier List to retrieve.
     * @return The Tier List object if found.
     */
    @GetMapping("/get/{name}")
    public ResponseEntity<TierList> getTierList(@PathVariable String name) {
        TierList tierList = tierListService.getTierListByName(name);
        if (tierList != null) {
            return ResponseEntity.ok(tierList);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Update an existing Tier List by its name.
     * @param name The name of the existing Tier List.
     * @param updatedTierList The new Tier List object containing updated data.
     * @return Response message indicating success or failure.
     */
    @PutMapping("/update/{name}")
    public ResponseEntity<String> updateTierList(@PathVariable String name, @RequestBody TierList updatedTierList) {
        String result = tierListService.updateTierList(name, updatedTierList);
        return ResponseEntity.ok(result);
    }

    /**
     * Delete an existing Tier List by its name.
     * @param name The name of the Tier List to be deleted.
     * @return Response message indicating success or failure.
     */
    @DeleteMapping("/delete/{name}")
    public ResponseEntity<String> deleteTierList(@PathVariable String name) {
        String result = tierListService.deleteTierList(name);
        return ResponseEntity.ok(result);
    }
}




