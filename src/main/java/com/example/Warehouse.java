package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Warehouse {
    private static final HashMap<String, Warehouse> warehouses = new HashMap<>();

    String warehouseName;
    List<Product> products;

    private Warehouse(String warehouseName) {
        this.products = new ArrayList<>();
        this.warehouseName = warehouseName;
    }

    public static Warehouse getInstance() {
        return warehouses.computeIfAbsent("DefaultWarehouse", Warehouse::new);
    }

    public static Warehouse getInstance(String warehouseName) {
        return warehouses.computeIfAbsent(warehouseName, Warehouse::new);
    }

    public List<Product> getProducts() {
        return products.stream().toList();
    }

    public List<Shippable> shippableProducts() {
        return products.stream()
                .filter(product -> product instanceof Shippable)
                .map(product -> (Shippable) product)
                .collect(Collectors.toList());
    }

    public void clearProducts() {
        products = new ArrayList<>();
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public void addProduct(Product product) {
        if (product != null){
            if (products.contains(product))
                throw new IllegalArgumentException("Product with that id already exists, use updateProduct for updates.");
            else
                this.products.add(product);
        } else
            throw new IllegalArgumentException("Product cannot be null.");
    }

    public void updateProductPrice(UUID productID, BigDecimal newPrice) {
        if (products.stream().anyMatch(product -> product.uuid() == productID)) {
            products.stream()
                    .filter(product -> product.uuid() == productID)
                    .forEach(product -> product.setPrice(newPrice));
        } else {
            throw new NoSuchElementException("Product not found with id: " + productID);
        }

    }

    public Optional<Product> getProductById(UUID uuid) {
        return Optional.of(products.stream().filter(product -> product.uuid() == uuid)).get().findAny();
    }

    public Map<Category, List<Product>> getProductsGroupedByCategories() {
        return products.stream()
                .collect(Collectors.groupingBy(Product::category));
    }

    public void remove(UUID uuid) {
        products.remove(getProductById(uuid).isPresent() ? getProductById(uuid).get() : null);
    }

    public List<Perishable> expiredProducts() {
        return products.stream()
                .filter(product -> product instanceof Perishable)
                .filter(product -> ((Perishable) product)
                        .expirationDate()
                        .isBefore(LocalDate.now()))
                .map(product -> (Perishable) product)
                .collect(Collectors.toList());
    }
}


