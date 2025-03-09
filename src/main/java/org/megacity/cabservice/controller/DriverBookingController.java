package org.megacity.cabservice.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.megacity.cabservice.dto.booking_dto.BookingInsertDto;
import org.megacity.cabservice.model.Notifiers.BookingNotifier;
import org.megacity.cabservice.model.Transaction;
import org.megacity.cabservice.model.User;
import org.megacity.cabservice.model.Wrappers.ResponseWrapper;
import org.megacity.cabservice.service.BookingService;

import java.io.IOException;
import java.io.PrintWriter;

public class DriverBookingController extends HttpServlet {

    private final BookingService bookingService = new BookingService();
    private BookingNotifier bookingNotifier = new BookingNotifier();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String action = request.getParameter("action");
        int bookingId = Integer.parseInt(request.getParameter("booking_id"));

        switch (action) {
            case "accept":
                bookingService.setBookingStatusByBookingId(bookingId,"Confirmed");
                bookingNotifier.registerListener(bookingService.getCustomerByBookingId(bookingId));
                bookingNotifier.setMessage("Driver Accepted the ride");
                bookingNotifier.removeAllListeners();
                request.getRequestDispatcher("driver_manage_booking.jsp").forward(request, response);
                break;
            case "cancel":
                bookingService.setBookingStatusByBookingId(bookingId,"Cancelled");
                bookingNotifier.registerListener(bookingService.getCustomerByBookingId(bookingId));
                bookingNotifier.setMessage("Driver Canceled the ride");
                bookingNotifier.removeAllListeners();
                request.getRequestDispatcher("driver_manage_booking.jsp").forward(request, response);
                break;
            case "completed":
                bookingService.setBookingStatusByBookingId(bookingId,"Completed");
                request.getRequestDispatcher("driver_manage_booking.jsp").forward(request, response);
                break;
            case "paid":
                bookingService.setBookingStatusByBookingId(bookingId,"Paid");
                bookingNotifier.registerListener(bookingService.getCustomerByBookingId(bookingId));
                bookingNotifier.setMessage("You have paid the ride");
                bookingNotifier.removeAllListeners();
                request.getRequestDispatcher("driver_manage_booking.jsp").forward(request, response);
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String action = request.getParameter("action");
        int id = request.getParameter("id")==null && user!=null? user.getId(): Integer.parseInt(request.getParameter("id"));
        String json = "";
        PrintWriter out = response.getWriter();

        switch (action) {
            case "view":
                request.getRequestDispatcher("driver_manage_booking.jsp").forward(request, response);
                break;
            case "add":
                request.getRequestDispatcher("customer_add_booking.jsp").forward(request, response);
            case "requests":
                json = bookingService.getBookingsByDriverId(id);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                out.print(json);
                out.flush();
                out.close();
                break;
        }


    }
}
