package org.megacity.cabservice.model.Tiers;

import org.megacity.cabservice.repository.TierRepo;

public class BasicTier implements UserTier{

    TierRepo repo = new TierRepo();
    private int discount = -1;
    @Override
    public int getDiscountPercentage() {
        return discount;
    }

    @Override
    public String getTierName() {
        return "Basic";
    }
}
