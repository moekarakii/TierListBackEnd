package com.example.demo.model;

import java.util.List;

public class TierList {
    private String name;
    private List<String> items;

    // default
    public TierList() {}

    // prototype TierList builder
    public TierList(String name, List<String> items) {
        this.name = name;
        this.items = items;
    }

    // getter and setter methods
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<String> getItems() { return items; }
    public void setItems(List<String> items) { this.items = items; }
}

