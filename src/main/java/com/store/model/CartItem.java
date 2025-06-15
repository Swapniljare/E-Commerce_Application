package com.store.model;

public class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product) {
        this.product = product;
        this.quantity = 1; // default quantity is 1 when added
    }

    public Product getProduct() {
        return product;
    }

    public int getProductId() {
        return product.getId(); // so controller doesn't need to call getProduct().getId()
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }

    public void increaseQuantity() {
        this.quantity++;
    }

    public void updateQuantity(int qty) {
        this.quantity = qty;
    }

    public boolean matchesProduct(int productId) {
        return this.product.getId() == productId;
    }
}
