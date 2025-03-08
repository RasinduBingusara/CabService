package org.megacity.cabservice.model.Payments;

import org.megacity.cabservice.service.TransactionService;

public class CardPayment implements PaymentMethod{

    private TransactionService transactionService = new TransactionService();
    private String cardHolder;
    private String cardNumber;
    private String expiryMonth;
    private String expiryYear;
    private String cvv;

    public CardPayment(String cardHolder, String cardNumber, String expiryMonth, String expiryYear, String cvv) {
        this.cardHolder = cardHolder;
        this.cardNumber = cardNumber;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
        this.cvv = cvv;
    }

    @Override
    public boolean pay(double amount) {
        System.out.println("Card Holder: " + cardHolder);
        return transactionService.makeTransactionWithCard(cardHolder, cardNumber, expiryMonth, expiryYear, cvv, amount);
    }
}
