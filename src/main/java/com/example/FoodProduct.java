package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class FoodProduct extends Product implements Perishable, Shippable {
    public static final int SHIPPING_MULTIPLIER = 50;
    LocalDate expirationDate;

    public FoodProduct(UUID productID, String name, Category category, BigDecimal price, LocalDate expirationDate, BigDecimal weight) {
        super(productID, name, category, price, weight);
        this.expirationDate = expirationDate;
    }

    @Override
    public LocalDate expirationDate() {
        return expirationDate;
    }

    @Override
    public String toString() {
        return "Food: " + name() + ", Expires: " + expirationDate;
    }

    @Override
    public BigDecimal calculateShippingCost() {
        return BigDecimal.valueOf(weight()).multiply(BigDecimal.valueOf(SHIPPING_MULTIPLIER));
    }
}