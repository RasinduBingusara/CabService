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

public class CustomerBookingController extends HttpServlet {

    private BookingService bookingService = new BookingService();
    private BookingNotifier bookingNotifier = new BookingNotifier();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String action = request.getParameter("action");
        String bookingId = request.getParameter("booking_id");

        switch (action) {
            case "add":
                BookingInsertDto newBooking = new BookingInsertDto(
                        user.getId(),
                        user.getId(),
                        request.getParameter("vehicle"),
                        request.getParameter("pickup_location"),
                        request.getParameter("destination"),
                        null,
                        Double.parseDouble(request.getParameter("distance")),
                        "Pending"
                );
                Transaction newTransaction = new Transaction();
                newTransaction.setPaymentMethod(request.getParameter("payment"));

                ResponseWrapper<BookingInsertDto> responseWrapper = bookingService.addNewBooking(newBooking,newTransaction);
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
        String id = request.getParameter("id")==null && user!=null? user.getId():request.getParameter("id");
        String json = "";
        PrintWriter out = response.getWriter();

        switch (action) {
            case "view":
                request.getRequestDispatcher("customer_manage_booking.jsp").forward(request, response);
                break;
            case "add":
                request.getRequestDispatcher("customer_add_booking.jsp").forward(request, response);
            case "history":
                json = bookingService.getBookingsByCustomerId(id);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                out.print(json);
                out.flush();
                out.close();
                break;
        }


    }
}

