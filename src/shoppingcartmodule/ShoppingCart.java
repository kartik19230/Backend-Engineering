package shoppingcartmodule;

import order.Product;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

    private final Map<Integer, CartItem> items;

    public ShoppingCart(){
        items = new HashMap<>();
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void addProduct(Product product, int quantity){
        if (quantity <= 0) return;
        if (quantity > product.getStock()) return;
        items.merge(product.getId(), new CartItem(product, quantity),
                (existingItem, newItem) -> {
                    existingItem.incrementQuantity(quantity);
                    return existingItem;
                });
    }

    public void removeProduct(int productId) {
        items.remove(productId);
    }

    public void incrementQuantity(int productId, int quantity) {

        if (quantity <= 0) return;

        items.computeIfPresent(productId, (id, existingItem) -> {
            existingItem.incrementQuantity(quantity);
            return existingItem;
        });
    }

    public void decrementProduct(int productId, int quantity) {

        if (quantity <= 0) return;

        items.computeIfPresent(productId, (id, existingItem) -> {

            if (quantity >= existingItem.getQuantity()) {
                return null;
            }
            existingItem.decrementQuantity(quantity);
            return existingItem;
        });
    }

    public void clearCart(){
        items.clear();
    }

    public double calculateTotal(){

        double total = 0;
        for (CartItem item :
                items.values()) {
            total += item.getTotalPrice();
        }
        return total;
    }

    public int getTotalItems(){
        int total = 0;
        for (CartItem item :
                items.values()) {
            total += item.getQuantity();
        }
        return total;
    }

    public void displayCart() {
        if (items.isEmpty()) {
            System.out.println("Your shopping cart is empty.");
            return;
        }
        System.out.println("\n--- Shopping Cart ---");
        for (CartItem item : items.values()) {
            System.out.printf("%s x %d - $%.2f ($%.2f each)\n",
                    item.getProduct().getName(),
                    item.getQuantity(),
                    item.getTotalPrice(),
                    item.getProduct().getPrice());
        }
        System.out.printf("Total Items Count: %d\n", getTotalItems());
        System.out.printf("Total Payable: $%.2f\n---------------------\n", calculateTotal());
    }
}
