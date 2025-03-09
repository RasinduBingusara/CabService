package org.megacity.cabservice.model.Tiers;

import org.megacity.cabservice.repository.TierRepo;

public class PlatinumTier implements UserTier{
    TierRepo repo = new TierRepo();
    private int discount = -1;
    public PlatinumTier(int discount) {
        this.discount = discount;
    }

    @Override
    public int getDiscountPercentage() {
        return discount;
    }

    @Override
    public String getTierName() {
        return "Platinum";
    }
}
