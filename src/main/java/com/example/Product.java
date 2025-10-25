package com.example;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public abstract class Product {
    private final String name;
    private final Category category;
    private final UUID productID;
    private BigDecimal price;
    private final BigDecimal weight;

    public Product(UUID productID, String name, Category category, BigDecimal price, BigDecimal weight) {
        if (price.intValue() >= 0)
            this.price = price;
        else
            throw new IllegalArgumentException("Price cannot be negative.");

        if (weight.intValue() >= 0)
            this.weight = weight;
        else
            throw new IllegalArgumentException("Weight cannot be negative.");

        if (name.isEmpty())
            throw new IllegalArgumentException("Name cannot be empty.");
        else
            this.name = name;

        this.category = category;
        this.productID = productID;
    }

    public BigDecimal price() {
        return price;
    }

    public double imprecisePrice() { return price.doubleValue(); }

    void setPrice(BigDecimal price) {
        if (price.intValue() > 0)
            this.price = price;
        else
            throw new IllegalArgumentException("Price cannot be negative or zero.");
    }

    public String name() {
        return name;
    }

    public UUID uuid() { return productID; }

    public Category category() {
        return category;
    }

    public String productDetails() {
        return toString();
    }

    public double weight() { return weight.doubleValue(); }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Product product)) return false;
        return Objects.equals(productID, product.productID);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(productID);
    }
}
