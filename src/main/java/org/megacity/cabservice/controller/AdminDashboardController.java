package org.megacity.cabservice.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.megacity.cabservice.model.Booking;
import org.megacity.cabservice.service.BookingService;
import org.megacity.cabservice.service.DriverAccService;
import org.megacity.cabservice.service.TransactionService;
import org.megacity.cabservice.service.VehicleService;

import java.io.IOException;
import java.util.List;

public class AdminDashboardController extends HttpServlet {

    private TransactionService transactionService = new TransactionService();
    private BookingService bookingService = new BookingService();
    private DriverAccService driverAccService = new DriverAccService();
    private VehicleService vehicleService = new VehicleService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int monthlyRevenue;
        List<Booking> recentBookings;

        req.setAttribute("recentBookings", bookingService.getRecentBookings(10));
        req.setAttribute("totalRevenue", transactionService.getMonthlyRevenue());
        req.setAttribute("availableVehicles", vehicleService.getTotalActiveVehicleCount());
        req.setAttribute("activeDrivers", driverAccService.getTotalActiveDriversCount());
        req.setAttribute("totalBookings",bookingService.getTotalBookingsInMonth());
        req.setAttribute("monthlyRevenueData", transactionService.getMonthlyRevenues());
        req.getRequestDispatcher("admin_dashboard.jsp").forward(req, resp);
    }
}
