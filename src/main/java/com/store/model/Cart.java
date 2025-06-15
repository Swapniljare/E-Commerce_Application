package com.store.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public void addProduct(Product product) {
        items.add(product);
    }

    public List<Product> getItems() {
        return items;
    }

    public void removeProduct(int productId) {
        items.removeIf(p -> p.getId() == productId);
    }

    public void clear() {
        items.clear();
    }

    public double getTotalPrice() {
        double total = 0;
        for (Product p : items) {
            total += p.getPrice();
        }
        return total;
    }
}
