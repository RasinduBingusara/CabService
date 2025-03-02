package org.megacity.cabservice.model.Pricing;

public class FareCalculator {

    private PricingCalc pricingCalc;
    public FareCalculator(PricingCalc pricingCalc) {
        this.pricingCalc = pricingCalc;
    }

    public double calculateFare(double distance) {
        return pricingCalc.calculatePrice(distance);
    }
}
