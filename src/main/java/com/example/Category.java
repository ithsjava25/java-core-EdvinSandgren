package com.example;

import java.util.HashMap;
import java.util.Locale;

public class Category {
    private static final HashMap<String, Category> categories = new HashMap<>();
    private final String name;

    private Category(String name) {
        this.name = name;
    }

    public static Category of(String category) {
        if (category == null)
            throw new IllegalArgumentException("Category name can't be null");
        else if (category.isBlank())
            throw new IllegalArgumentException("Category name can't be blank");

        String lowerCase = category.toLowerCase(Locale.ROOT);
        return categories.computeIfAbsent(lowerCase, Category::new);
    }

    public String getName() {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}
