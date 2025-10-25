package com.example;

import java.util.HashMap;

public class Category {
    private static final HashMap<String, Category> categories = new HashMap<>();
    private final String name;

    private Category(String name) {
        if (name == null)
            throw new IllegalArgumentException("Category name can't be null");
        else if (name.isBlank())
            throw new IllegalArgumentException("Category name can't be blank");
        else
            this.name = name;
    }

    public static Category of(String category) {
        return categories.computeIfAbsent(category, Category::new);
    }

    public String getName() {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}
