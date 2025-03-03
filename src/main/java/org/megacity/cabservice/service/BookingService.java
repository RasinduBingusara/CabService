package org.megacity.cabservice.service;

import org.megacity.cabservice.dto.vehicle_dto.VehicleDetailsDto;
import org.megacity.cabservice.model.Booking;
import org.megacity.cabservice.repository.BookingRepo;
import org.megacity.cabservice.util.JsonBuilder;

import java.util.List;

public class BookingService {

    private BookingRepo bookingRepo = new BookingRepo();

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
}
