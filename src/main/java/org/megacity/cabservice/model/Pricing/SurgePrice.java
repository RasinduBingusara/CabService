package org.megacity.cabservice.model.Pricing;

public class SurgePrice implements PricingCalc{

    private double surgeMultiplier = 0.0;
    private double price_per_km = 0.0;

    public SurgePrice(double surgeMultiplier, double pricePerKm) {
        this.surgeMultiplier = surgeMultiplier;
        this.price_per_km = pricePerKm;
    }

    @Override
    public double calculatePrice(double distance) {
        return distance * price_per_km * surgeMultiplier;
    }
}
