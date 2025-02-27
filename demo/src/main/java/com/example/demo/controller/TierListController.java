package com.example.demo.controller;

import com.example.demo.model.TierList;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tierlist")
public class TierListController {
    private final List<TierList> tierLists = new ArrayList<>();

    // create new TierList
    @PostMapping("/create")
    public TierList createTierList(@RequestBody TierList tierList) {
        tierLists.add(tierList);
        return tierList;
    }

    // return all Tierlist
    @GetMapping("/all")
    public List<TierList> getAllTierLists() {
        return tierLists;
    }
}
