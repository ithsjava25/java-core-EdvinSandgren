package com.example;

import java.math.BigDecimal;
import java.util.UUID;

public class ElectronicsProduct extends Product implements  Shippable {
    public static final int SHIPPING_BASE = 79;
    public static final int SHIPPING_ADD = 49;
    int warranty;

    public ElectronicsProduct(UUID productID, String name, Category category, BigDecimal price, int warranty, BigDecimal weight) {
        super(productID, name, category, price, weight);
        if (warranty >= 0)
            this.warranty = warranty;
        else
            throw new IllegalArgumentException("Warranty months cannot be negative.");
    }

    @Override
    public BigDecimal calculateShippingCost() {
        return BigDecimal.valueOf(SHIPPING_BASE + (weight() > 5 ? SHIPPING_ADD : 0));
    }

    @Override
    public String toString() {
        return "Electronics: "+ name() + ", Warranty: " + warranty + " months";
    }
}
