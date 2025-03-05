package org.megacity.cabservice.model.Pricing;

public class RegularPrice implements PricingCalc{

    private double Price_Per_KM = 0.0;
    private double TAX = 0.0;

    public RegularPrice(double Price_Per_KM, double TAX) {
        this.Price_Per_KM = Price_Per_KM;
        this.TAX = TAX;
    }

    @Override
    public double calculatePrice(double distance) {
        double price = Price_Per_KM * distance;
        return price * ((TAX + 100)/100);
    }
}
