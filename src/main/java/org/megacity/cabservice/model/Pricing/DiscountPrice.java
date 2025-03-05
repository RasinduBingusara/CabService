package org.megacity.cabservice.model.Pricing;

public class DiscountPrice implements PricingCalc{

    private double discountPercentage = 0.0;
    private double price_per_km = 0.0;
    private double TAX = 0.0;

    public DiscountPrice(double discountPercentage, double price_per_km, double TAX) {
        this.discountPercentage = discountPercentage;
        this.price_per_km = price_per_km;
        this.TAX = TAX;
    }

    @Override
    public double calculatePrice(double distance) {
        double price = distance * price_per_km;
        price = price - (price * (discountPercentage / 100));
        return price * ((TAX + 100)/100);
    }
}
