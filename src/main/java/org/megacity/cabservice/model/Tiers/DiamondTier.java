package org.megacity.cabservice.model.Tiers;

import org.megacity.cabservice.repository.TierRepo;

public class DiamondTier implements UserTier{
    TierRepo repo = new TierRepo();
    private int discount = -1;
    public DiamondTier(int discount) {
        this.discount = discount;
    }

    @Override
    public int getDiscountPercentage() {
        return discount;
    }

    @Override
    public String getTierName() {
        return "Diamond";
    }
}
