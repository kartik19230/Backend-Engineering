package order;

import shoppingcartmodule.CartItem;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {

    public enum OrderStatus{
        PENDING, PAID, SHIPPED, CANCELLED
    }

    private final String orderId;
    private final List<OrderItem> items;
    private OrderStatus status;
    private double totalAmount;

    public Order(){
        this.orderId = UUID.randomUUID().toString();
        items = new ArrayList<>();
        this.status = OrderStatus.PENDING;
        totalAmount = 0.0;
    }

    public boolean addItem(Product product, int quantity){
        if (status != OrderStatus.PENDING){
            System.out.println("Order cannot be modify");
            return false;
        }
        if (product.reduceStock(quantity)){
            items.add(new OrderItem(product, quantity));
            recalculateTotal();
            return true;
        }
        System.out.println("Not sufficient stock for " + product.getName());
        return false;
    }

    public boolean removeItem(Product product,int quantity){
        if (status != OrderStatus.PENDING){
            System.out.println("Order cannot be modify");
            return false;
        } else if (product.getStock() <  quantity) {
            items.remove(product);
            return true;
        }else if (product.getStock() >  quantity){
            for (OrderItem item : items){
                if (item.getProduct().getName().equals(product.getName())){
                    item.decrementQuantity(quantity);
                }
            }
            recalculateTotal();
            return true;
        }
        return false;
    }

    private void recalculateTotal(){
        this.totalAmount = items.stream()
                .mapToDouble(OrderItem::getTotalPrice)
                .sum();
    }

    public void displayOrder(){
        if (items.isEmpty()){
            System.out.println("No items in the order");
            return;
        }
        System.out.println(orderId);
        System.out.println("\n--- Order ---");
        for (OrderItem item : items) {
            System.out.printf("%s x %d - $%.2f ($%.2f each)\n",
                    item.getProduct().getName(),
                    item.getQuantity(),
                    item.getTotalPrice(),
                    item.getProduct().getPrice());
        }
        System.out.println(status);
    }
}
