package org.megacity.cabservice.service;

import org.megacity.cabservice.dto.booking_dto.BookingInsertDto;
import org.megacity.cabservice.model.Booking;
import org.megacity.cabservice.model.Notifiers.BookingNotifier;
import org.megacity.cabservice.model.Pricing.DiscountPrice;
import org.megacity.cabservice.model.Pricing.FareCalculator;
import org.megacity.cabservice.model.Pricing.PricingCalc;
import org.megacity.cabservice.model.Pricing.RegularPrice;
import org.megacity.cabservice.model.Tiers.TierCalculator;
import org.megacity.cabservice.model.Tiers.UserTier;
import org.megacity.cabservice.model.Transaction;
import org.megacity.cabservice.model.Users.Customer;
import org.megacity.cabservice.model.Users.Driver;
import org.megacity.cabservice.model.Wrappers.ResponseWrapper;
import org.megacity.cabservice.repository.*;
import org.megacity.cabservice.util.JsonBuilder;

import java.util.List;

public class BookingService {

    private BookingRepo bookingRepo = new BookingRepo();
    private VehicleRepo vehicleRepo = new VehicleRepo();
    private AccountRepo accountRepo = new AccountRepo();
    private TaxRepo taxRepo = new TaxRepo();
    private TransactionRepo transactionRepo = new TransactionRepo();

    public String getPortionOfBookingsInJson(String limit,String offset, String status){
        List<Booking> bookings = status.isEmpty()? bookingRepo.getPortionOfBookings(limit, offset):
                bookingRepo.getPortionOfBookingsWithStatus(limit, offset,status);

        return JsonBuilder.getInstance().bookingsToJson(bookings);
    }

    public String getBookingBySearchInJson(String keyword,String status){
        List<Booking> bookings = status.isEmpty()? bookingRepo.getBookingsBySearch(keyword):
                bookingRepo.getBookingsBySearchWithStatus(keyword,status);

        return JsonBuilder.getInstance().bookingsToJson(bookings);
    }

    public String getBookingByEmailInJson(String email){
        List<Booking> bookings = bookingRepo.getBookingsByEmail(email);
        return JsonBuilder.getInstance().bookingsToJson(bookings);

    }

    public boolean setBookingStatusByBookingId(String id,String status){
        return bookingRepo.setBookingStatusByBookingId(id,status);
    }
    public boolean payBookingByBookingId(String bookingId, String transactionId){
        if(transactionRepo.updatePaidTime(transactionId)){
            return bookingRepo.setBookingStatusByBookingId(bookingId,"Paid");
        }
        return false;
    }

    public ResponseWrapper<BookingInsertDto> addNewBooking(String customerId,BookingInsertDto booking, Transaction transaction){
        if(vehicleRepo.checkVehicleAvailabilityByStatus(booking.getVehicleId(),"Active")){

            transaction.setAmount(calculateFare(customerId,booking.getDistance(),booking.getVehicleId()));
            int transactionId = transactionRepo.addTransaction(transaction);
            booking.setTransactionId(String.valueOf(transactionId));
            int bookingId = bookingRepo.addNewBooking(booking);
            if(bookingId > 0){

                Customer customer = getCustomerByBookingId(String.valueOf(bookingId));
                Driver driver = getDriverByBookingId(String.valueOf(bookingId));

                BookingNotifier notifier = new BookingNotifier();
                notifier.registerListener(driver);
                notifier.setMessage("Your ride has been booked!");
                notifier.removeAllListeners();

                notifier.registerListener(customer);
                notifier.setMessage("Booking successfully added");

                boolean isTierChange = new TierCalculator().updateCalculatedTier(customerId);
                if(isTierChange){
                    notifier.setMessage("Your tier has been changed!");
                }

                return new ResponseWrapper<>("Booking added successfully", null);
            }
            else{
                return new ResponseWrapper<>("Booking adding failed", booking);
            }
        }
        else
            return new ResponseWrapper<>("Vehicle already in a trip", booking);
    }

    public Customer getCustomerByBookingId(String bookingId){
        return bookingRepo.getCustomerByBooking(bookingId);
    }

    public Driver getDriverByBookingId(String bookingId){
        return bookingRepo.getDriverByBooking(bookingId);
    }

    public String getBookingsByCustomerId(String customerId){
        List<Booking> bookings = bookingRepo.getBookingsByCustomerId(customerId);
        return JsonBuilder.getInstance().bookingsToJson(bookings);

    }

    public String getBookingsByDriverId(String driverId){
        List<Booking> bookings = bookingRepo.getBookingsByDriverId(driverId);
        return JsonBuilder.getInstance().bookingsToJson(bookings);
    }

    public String getBookingsByCustomerIdAndStatus(String customerId, String status){
        List<Booking> bookings = bookingRepo.getBookingsByCustomerId(customerId,status);
        return JsonBuilder.getInstance().bookingsToJson(bookings);
    }

    public String getPriceOfBookingInJson(String customerId,double distance, String vehicleId){
        PricingCalc pricingCalc;
        double price_per_km = vehicleRepo.getVehiclePricePerKm(vehicleId);
        double tax = taxRepo.getTaxByKeyName("Service Tax");

        UserTier userTier = new TierCalculator().getUserTier(customerId);
        if(userTier.getDiscountPercentage()!=-1){
            pricingCalc = new RegularPrice(price_per_km,tax);
        }
        else{
            pricingCalc = new DiscountPrice(userTier.getDiscountPercentage(),price_per_km,tax);
        }

        FareCalculator calculator = new FareCalculator(pricingCalc);
        double total = calculator.calculateFare(distance);

        String json = "{" +
                "\"price_per_km\":\"" + escapeJson(String.valueOf(price_per_km)) + "\"," +
                "\"tax\":\"" + escapeJson(String.valueOf(tax)) + "\"," +
                "\"tier_name\":\"" + escapeJson(userTier.getTierName()) + "\"," +
                "\"discount\":\"" + escapeJson(String.valueOf(userTier.getDiscountPercentage())) + "\"," +
                "\"total\":\"" + escapeJson(String.valueOf(total)) + "\"" +
                "}";

        return json;

    }

    private double calculateFare(String customerId, double distance, String vehicleId){
        PricingCalc pricingCalc;
        double price_per_km = vehicleRepo.getVehiclePricePerKm(vehicleId);
        double tax = taxRepo.getTaxByKeyName("Service Tax");

        UserTier userTier = new TierCalculator().getUserTier(customerId);
        if(userTier.getDiscountPercentage()!=-1){
            pricingCalc = new RegularPrice(price_per_km,tax);
        }
        else{
            pricingCalc = new DiscountPrice(userTier.getDiscountPercentage(), price_per_km,tax);
        }

        FareCalculator calculator = new FareCalculator(pricingCalc);
        return calculator.calculateFare(distance);
    }

    private String escapeJson(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("\\", "\\\\")   // Escape backslashes
                .replace("\"", "\\\"")   // Escape double quotes
                .replace("\n", "\\n")    // Escape new lines
                .replace("\r", "\\r")    // Escape carriage returns
                .replace("\t", "\\t");   // Escape tabs
    }
}
