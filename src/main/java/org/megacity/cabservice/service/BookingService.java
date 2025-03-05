package org.megacity.cabservice.service;

import org.megacity.cabservice.dto.booking_dto.BookingInsertDto;
import org.megacity.cabservice.dto.vehicle_dto.VehicleDetailsDto;
import org.megacity.cabservice.model.Booking;
import org.megacity.cabservice.model.Wrappers.BooleanWrapper;
import org.megacity.cabservice.model.Wrappers.ResponseWrapper;
import org.megacity.cabservice.repository.BookingRepo;
import org.megacity.cabservice.repository.VehicleRepo;
import org.megacity.cabservice.util.JsonBuilder;

import java.util.List;

public class BookingService {

    private BookingRepo bookingRepo = new BookingRepo();
    private VehicleRepo vehicleRepo = new VehicleRepo();

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

    public ResponseWrapper<BookingInsertDto> addNewBooking(BookingInsertDto booking){
        if(vehicleRepo.checkVehicleAvailabilityByStatus(booking.getVehicleId(),"Active")){
            return bookingRepo.addNewBooking(booking)? new ResponseWrapper<>("Booking added successfully", null)
                    :new ResponseWrapper<>("Booking adding failed", booking);
        }
        else
            return new ResponseWrapper<>("Vehicle already in a trip", booking);
    }

    public String getBookingsByCustomerId(String customerId){
        List<Booking> bookings = bookingRepo.getBookingsByCustomerId(customerId);
        return JsonBuilder.getInstance().bookingsToJson(bookings);

    }

    public String getBookingsByCustomerIdAndStatus(String customerId, String status){
        List<Booking> bookings = bookingRepo.getBookingsByCustomerId(customerId,status);
        return JsonBuilder.getInstance().bookingsToJson(bookings);
    }
}
