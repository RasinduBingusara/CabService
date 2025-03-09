package org.megacity.cabservice.model.Tiers;

import org.megacity.cabservice.repository.TierRepo;

public class GoldTier implements UserTier{
    TierRepo repo = new TierRepo();
    private int discount = -1;
    public GoldTier(int discount) {
        this.discount = discount;
    }

    @Override
    public int getDiscountPercentage() {
        return discount;
    }

    @Override
    public String getTierName() {
        return "Gold";
    }
}
