package com.example.demo.service;

import com.example.demo.model.TierList;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TierListService {
    private final List<TierList> tierLists = new ArrayList<>();

    // Create a new Tier List
    public void createTierList(TierList tierList) {
        tierLists.add(tierList);
    }

    // Retrieve all Tier Lists
    public List<TierList> getAllTierLists() {
        return tierLists;
    }

    // Update an existing Tier List
    public String updateTierList(TierList updatedTierList) {
        for (TierList list : tierLists) {
            if (list.getName().equals(updatedTierList.getName())) {
                list.setItems(updatedTierList.getItems());
                return "Tier List updated successfully!";
            }
        }
        return "Tier List not found!";
    }

    // Delete a Tier List by name
    public String deleteTierList(String name) {
        boolean removed = tierLists.removeIf(list -> list.getName().equals(name));
        return removed ? "Tier List deleted successfully!" : "Tier List not found!";
    }
}


