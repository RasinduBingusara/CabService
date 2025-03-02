package org.megacity.cabservice.model.Payments;

public class CardPayment implements PaymentMethod{

    private String cardNumber;
    private String expiryMonth;
    private String expiryYear;
    private String cvv;

    public CardPayment(String cardNumber, String expiryMonth, String expiryYear, String cvv) {
        this.cardNumber = cardNumber;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
        this.cvv = cvv;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Payment: Paid " + amount + " using Card: " + cardNumber + " " + expiryMonth + " " + expiryYear + " " + cvv);
    }
}
