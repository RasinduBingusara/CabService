package org.megacity.cabservice.model;

public class Fare {

    private double subTotal;
    private double tax;
    private int discount;
    private double afterDiscount;
    private double total;

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        double factor = Math.pow(10, 2);
        this.subTotal = Math.ceil(subTotal * factor) / factor;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public double getAfterDiscount() {
        return afterDiscount;
    }

    public void setAfterDiscount(double afterDiscount) {
        double factor = Math.pow(10, 2);
        this.afterDiscount = Math.ceil(afterDiscount * factor) / factor;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
