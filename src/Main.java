import order.Order;
import order.Product;
import order.User;
import shoppingcartmodule.ShoppingCart;

public class Main {
    public static void main(String[] args) {

        Product mobile = new Product("Iphone","Mobile",100000,25);
        Product laptop = new Product("Asus","laptop",55000,20);

        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(mobile,2);
        cart.addProduct(laptop,2);

        cart.incrementQuantity(mobile.getId(), 1);
        cart.decrementProduct(laptop.getId(), 1);

        cart.displayCart();

        Order order1 = new Order();
        order1.addItem(mobile,2);
        order1.displayOrder();
    }
}