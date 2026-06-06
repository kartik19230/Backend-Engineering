package order;

import shoppingcartmodule.ShoppingCart;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private User user;
    private List<Product> products = new ArrayList<>();
    private boolean paid = false;

    public Order(User user){
        this.user = user;
    }

    public void addProduct(Product product){
        products.add(product);
    }

    public double calculateTotalCost(){

        double subTotal = 0.0;

        for (Product product :
                products) {
            subTotal += product.getPrice();
        }

        return subTotal;
    }

    public void markAsPaid(){
        this.paid = true;
    }

    public boolean isPaid(){
        return paid;
    }
}
