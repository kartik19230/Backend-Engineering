import order.Order;
import order.Product;
import order.User;
import shoppingcartmodule.CreditCard;
import shoppingcartmodule.PaymentMethod;
import shoppingcartmodule.ShoppingCart;

public class Main {
    public static void main(String[] args) {

        User user = new User("Kartik Shinde","kartikkshinde0103@gmail.com","12345");

        ShoppingCart cart = new ShoppingCart();

        Product laptop = new Product("Asus Laptop","A High performance laptop",56500.0,20);
        Product headphone = new Product("Boat Headphone","High performance headphone",3200,20);

        cart.addProduct(laptop,2);
        cart.addProduct(headphone,1);

        cart.displayCart();

        cart.decrementProduct(laptop.getId(), 1);
        cart.displayCart();

        Order order = new Order(user);
        order.addProduct(laptop);
        order.addProduct(headphone);

        PaymentMethod method = new CreditCard("ICICI card","Kartik Shinde","29-05-2029","23423");
        boolean success = method.processPayement(order.calculateTotalCost());

        if (success) {
            order.markAsPaid();
            System.out.println("Order paid successfully!");
        } else {
            System.out.println("Payment failed. Please try another method.");
        }

    }
}