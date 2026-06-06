package shoppingcartmodule;

public class CreditCard implements PaymentMethod{

    private String cardName;
    private String cardHolderName;
    private String expiry;
    private String cvv;

    public CreditCard(String cardName, String cardHolderName, String expiry, String cvv) {
        this.cardName = cardName;
        this.cardHolderName = cardHolderName;
        this.expiry = expiry;
        this.cvv = cvv;
    }

    @Override
    public boolean processPayement(double amount) {
        System.out.println("Processing credit card payment of $" + amount + " for " + cardHolderName);
        return true;
    }
}
