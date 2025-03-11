package org.megacity.cabservice.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.megacity.cabservice.dto.booking_dto.BookingInsertDto;
import org.megacity.cabservice.model.Notifiers.BookingNotifier;
import org.megacity.cabservice.model.User;
import org.megacity.cabservice.model.Wrappers.ResponseWrapper;
import org.megacity.cabservice.service.BookingService;

import java.io.IOException;
import java.io.PrintWriter;

public class CustomerBookingController extends HttpServlet {

    private BookingService bookingService = new BookingService();
    private BookingNotifier bookingNotifier = new BookingNotifier();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String action = request.getParameter("action");
        int bookingId = Integer.parseInt(request.getParameter("booking_id"));
        int vehicleId = Integer.parseInt(request.getParameter("vehicle"));

        switch (action) {
            case "add":
                BookingInsertDto newBooking = new BookingInsertDto(
                        user.getId(),
                        user.getId(),
                        vehicleId,
                        request.getParameter("pickup_location"),
                        request.getParameter("destination"),
                        Double.parseDouble(request.getParameter("distance")),
                        "Pending"
                );

                ResponseWrapper<BookingInsertDto> responseWrapper = bookingService.addNewBooking(user.getId(), newBooking,request.getParameter("payment"));
                if(responseWrapper.getData() == null){
                    request.setAttribute("message", responseWrapper.getMessage());
                    request.getRequestDispatcher("customer_manage_booking.jsp").forward(request, response);
                }
                else{
                    request.setAttribute("error", responseWrapper.getMessage());
                    request.getRequestDispatcher("customer_add_booking.jsp").forward(request, response);
                }
                break;
            case "cancel":
                bookingService.setBookingStatusByBookingId(bookingId,"Cancelled");
                bookingNotifier.registerListener(bookingService.getDriverByBookingId(bookingId));
                bookingNotifier.setMessage("User Canceled the booking");
                bookingNotifier.removeAllListeners();
                request.getRequestDispatcher("customer_manage_booking.jsp").forward(request, response);
                break;
            case "paid":
                bookingService.setBookingStatusByBookingId(bookingId,"Paid");
                bookingNotifier.registerListener(bookingService.getDriverByBookingId(bookingId));
                bookingNotifier.setMessage("Customer Paid the booking");
                bookingNotifier.removeAllListeners();
                request.getRequestDispatcher("customer_manage_booking.jsp").forward(request, response);
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
                request.getRequestDispatcher("customer_manage_booking.jsp").forward(request, response);
                break;
            case "add":
                request.getRequestDispatcher("customer_add_booking.jsp").forward(request, response);
            case "history":
                json = bookingService.getBookingsByCustomerIdInJson(id);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                out.print(json);
                out.flush();
                out.close();
                break;
        }


    }
}

