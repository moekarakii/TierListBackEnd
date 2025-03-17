package com.example.demo.service;

import com.example.demo.model.TierList;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TierListService {

    // Temporary storage for Tier Lists (since we are not using a database)
    private final List<TierList> tierLists = new ArrayList<>();

    /**
     * Create a new Tier List.
     * @param tierList The Tier List object to add.
     * @return Response message indicating success.
     */
    public String createTierList(TierList tierList) {
        tierLists.add(tierList);
        return "Tier List created successfully!";
    }

    /**
     * Retrieve all Tier Lists.
     * @return List of all stored Tier Lists.
     */
    public List<TierList> getAllTierLists() {
        return tierLists;
    }

    /**
     * Retrieve a specific Tier List by its name.
     * @param name The name of the Tier List to retrieve.
     * @return The found Tier List or null if not found.
     */
    public TierList getTierListByName(String name) {
        for (TierList t : tierLists) {
            if (t.getName().equalsIgnoreCase(name)) {
                return t;
            }
        }
        return null;
    }

    /**
     * Update an existing Tier List based on its name.
     * @param name The name of the Tier List to be updated.
     * @param updatedTierList The new Tier List object containing updated data.
     * @return Response message indicating success or failure.
     */
    public String updateTierList(String name, TierList updatedTierList) {
        for (TierList t : tierLists) {
            if (t.getName().equalsIgnoreCase(name)) {
                t.setName(updatedTierList.getName());
                t.setItems(updatedTierList.getItems());
                return "Tier List updated successfully!";
            }
        }
        return "Tier List not found!";
    }

    /**
     * Delete an existing Tier List based on its name.
     * @param name The name of the Tier List to be deleted.
     * @return Response message indicating success or failure.
     */
    public String deleteTierList(String name) {
        for (TierList t : tierLists) {
            if (t.getName().equalsIgnoreCase(name)) {
                tierLists.remove(t);
                return "Tier List deleted successfully!";
            }
        }
        return "Tier List not found!";
    }
}



