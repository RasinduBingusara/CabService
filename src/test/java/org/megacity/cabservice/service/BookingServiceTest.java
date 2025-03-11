package org.megacity.cabservice.service;

import org.junit.Test;
import org.megacity.cabservice.dto.booking_dto.BookingInsertDto;
import org.megacity.cabservice.model.Booking;
import org.megacity.cabservice.model.Users.Customer;
import org.megacity.cabservice.model.Users.Driver;
import org.megacity.cabservice.model.Wrappers.ResponseWrapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookingServiceTest {

    private BookingService bookingService = new BookingService();

    @Test
    public void testGetPortionOfBookings() {
        String bookings = bookingService.getPortionOfBookingsInJson(5, 0, "");
        assertNotNull(bookings);
    }

    @Test
    public void testGetPortionOfBookingsWithStatus() {
        String bookings = bookingService.getPortionOfBookingsInJson(5, 0, "Active");
        assertNotNull(bookings);
    }

    @Test
    public void testGetBookingsBySearch() {
        String bookings = bookingService.getBookingBySearchInJson("John", "");
        assertNotNull(bookings);
    }

    @Test
    public void testGetBookingsBySearchWithStatus() {
        String bookings = bookingService.getBookingBySearchInJson("John", "Active");
        assertNotNull(bookings);
    }

    @Test
    public void testUpdateBookingStatusWithValidBookingId() {
        boolean updated = bookingService.setBookingStatusByBookingId(1, "Cancelled");
        assertTrue(updated);
    }

    @Test
    public void testUpdateBookingStatusWithInvalidBookingId() {
        boolean updated = bookingService.setBookingStatusByBookingId(-1, "Cancelled");
        assertFalse(updated);
    }

    @Test
    public void testAddBookingWithValidData() {
        BookingInsertDto newBooking = new BookingInsertDto(
                1,
                1,
                1,
                "pickup",
                "dropoff",
                5.5,
                "Pending"
        );
        ResponseWrapper<BookingInsertDto> result = bookingService.addNewBooking(1,newBooking,"Cash");
        assertNull(result.getData());
        assertEquals("Booking added successfully", result.getMessage());
    }

    @Test
    public void testAddBookingWithOnTripVehicleId() {
        BookingInsertDto newBooking = new BookingInsertDto(
                1,
                1,
                19,
                "pickup",
                "dropoff",
                5.5,
                "Pending"
        );
        ResponseWrapper<BookingInsertDto> result = bookingService.addNewBooking(1,newBooking,"Cash");
        assertNotNull(result.getData());
        assertEquals("Vehicle already in a trip", result.getMessage());
    }

    @Test
    public void testGetBookingsByCustomerId() {
        String bookings = bookingService.getBookingsByCustomerIdInJson(1);
        assertNotNull(bookings);
    }

    @Test
    public void testGetBookingsByInvalidCustomerId() {
        String bookings = bookingService.getBookingsByCustomerIdInJson(-1);
        assertEquals("[]", bookings);
    }

    @Test
    public void testGetBookingsByDriverId() {
        String bookings = bookingService.getBookingsByCustomerIdInJson(2);
        assertNotNull(bookings);
    }
    @Test
    public void testGetBookingsByInvalidDriverId() {
        String bookings = bookingService.getBookingsByCustomerIdInJson(2);
        assertEquals("[]", bookings);
    }

    @Test
    public void testGetCustomerByBookingId() {
        Customer customer = bookingService.getCustomerByBookingId(2);
        assertNotNull(customer);
    }
    @Test
    public void testGetCustomerByInvalidBookingId() {
        Customer customer = bookingService.getCustomerByBookingId(-2);
        assertNull(customer);
    }

    @Test
    public void testGetDriverByBookingId() {
        Driver driver = bookingService.getDriverByBookingId(2);
        assertNotNull(driver);
    }
    @Test
    public void testGetDriverByInvalidBookingId() {
        Driver driver = bookingService.getDriverByBookingId(-2);
        assertNull(driver);
    }

}