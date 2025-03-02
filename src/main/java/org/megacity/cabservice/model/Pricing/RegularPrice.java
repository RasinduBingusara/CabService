package org.megacity.cabservice.model.Pricing;

public class RegularPrice implements PricingCalc{

    private double Price_Per_KM = 0.0;

    public RegularPrice(double Price_Per_KM) {
        this.Price_Per_KM = Price_Per_KM;
    }

    @Override
    public double calculatePrice(double distance) {
        return Price_Per_KM * distance;
    }
}
