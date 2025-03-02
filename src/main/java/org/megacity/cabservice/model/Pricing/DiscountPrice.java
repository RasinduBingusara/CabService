package org.megacity.cabservice.model.Pricing;

public class DiscountPrice implements PricingCalc{

    private double discountPercentage = 0.0;
    private double price_per_km = 0.0;

    public DiscountPrice(double discountPercentage, double price_per_km) {
        this.discountPercentage = discountPercentage;
        this.price_per_km = price_per_km;
    }

    @Override
    public double calculatePrice(double distance) {
        double price = distance * price_per_km;
        return price - (price * (discountPercentage / 100));
    }
}
