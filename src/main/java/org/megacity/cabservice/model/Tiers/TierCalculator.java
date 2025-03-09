package org.megacity.cabservice.model.Tiers;

import org.megacity.cabservice.model.Tier;
import org.megacity.cabservice.model.Users.Customer;
import org.megacity.cabservice.repository.AccountRepo;
import org.megacity.cabservice.repository.BookingRepo;
import org.megacity.cabservice.repository.TierRepo;

public class TierCalculator {

    TierRepo tierRepo = new TierRepo();
    BookingRepo bookingRepo = new BookingRepo();
    AccountRepo accountRepo = new AccountRepo();

    public UserTier getUserTier(String customerId) {
        Tier tier = tierRepo.getTierNameByCustomerId(customerId);
        if (tier != null) {
            switch (tier.getName().toLowerCase()){
                case "basic":
                    return new BasicTier();
                case "silver":
                    return new SilverTier(tier.getPercentage());
                case "gold":
                    return new GoldTier(tier.getPercentage());
                case "platinum":
                    return new PlatinumTier(tier.getPercentage());
                case "diamond":
                    return new DiamondTier(tier.getPercentage());
            }
        }
        return null;
    }

    public boolean updateCalculatedTier(String customerId) {
        int bookingCount = bookingRepo.bookingCountPerMonth(customerId);
        int tierId = 0;
        if(bookingCount < 5){
            tierId = 1;
        }
        else if(bookingCount < 10){
            tierId = 2;
        }
        else if(bookingCount < 15){
            tierId = 3;
        }
        else if(bookingCount < 20){
            tierId = 4;
        }
        else{
            tierId = 5;
        }

        return accountRepo.updateTierIfDifferent(customerId,tierId);
    }
}
