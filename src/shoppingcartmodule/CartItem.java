package shoppingcartmodule;

import order.Product;

public class CartItem {

    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        if (product == null){
            throw new IllegalArgumentException("Product can't be null");
        }
        if (quantity == 0){
            throw new IllegalArgumentException("Quantity Can't be zero");
        }
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void incrementQuantity(int amount){
        if (amount <= 0) return;
        if (quantity + amount > product.getStock()) return;

        quantity += amount;
    }

    public void decrementQuantity(int amount){

        if (amount <= 0) return;
        if(amount > quantity) return;
        quantity -= amount;

    }

    public void setQuantity(int quantity){
        if (quantity <= 0) return;
        if (quantity  > product.getStock()) return;
        this.quantity = quantity;
    }

    public double getTotalPrice(){
        return getProduct().getPrice() * quantity;
    }
}
