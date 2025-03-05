package org.megacity.cabservice.model.Pricing;

public class FareCalculator {

    private PricingCalc pricingCalc;
    public FareCalculator(PricingCalc pricingCalc) {
        this.pricingCalc = pricingCalc;
    }

    public double calculateFare(double distance) {
        double price = pricingCalc.calculatePrice(distance);
        double factor = Math.pow(10, 2);
        return Math.ceil(price * factor) / factor;
    }
}
