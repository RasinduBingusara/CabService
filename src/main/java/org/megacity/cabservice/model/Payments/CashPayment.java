package org.megacity.cabservice.model.Payments;

public class CashPayment implements PaymentMethod{

    @Override
    public boolean pay(double amount) {

        System.out.println(amount + " cash payment has been placed");
        return true;
    }
}
