package org.megacity.cabservice.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.megacity.cabservice.dto.booking_dto.BookingInsertDto;
import org.megacity.cabservice.model.Booking;
import org.megacity.cabservice.model.Pricing.RegularPrice;
import org.megacity.cabservice.model.User;
import org.megacity.cabservice.model.Wrappers.ResponseWrapper;
import org.megacity.cabservice.service.BookingService;

import java.io.IOException;
import java.io.PrintWriter;

public class BookingController extends HttpServlet {

    private BookingService bookingService = new BookingService();


    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String action = req.getParameter("action");
        String limit= req.getParameter("limit");
        String offset= req.getParameter("offset");
        String status= req.getParameter("status");
        String keyword = req.getParameter("keyword");

        String json = "";
        PrintWriter out = res.getWriter();

        switch (action) {
            case "view":
                req.getRequestDispatcher("manage_booking.jsp").forward(req,res);
                break;
            case "portion":
                json = bookingService.getPortionOfBookingsInJson(limit,offset,status);
                res.setContentType("application/json");
                res.setCharacterEncoding("UTF-8");

                out.print(json);
                out.flush();
                out.close();
                break;
            case "search":
                json = bookingService.getBookingBySearchInJson(keyword,status);
                res.setContentType("application/json");
                res.setCharacterEncoding("UTF-8");

                out.print(json);
                out.flush();
                out.close();
                break;
            case "calculate":
                double distance = Double.parseDouble(req.getParameter("distance"));
                double discount = req.getParameter("discount")!=null ? Double.parseDouble(req.getParameter("discount")):-1;
                String vehicleId = req.getParameter("vehicle_id");

                json = bookingService.getPriceOfBookingInJson(distance,vehicleId,discount);
                res.setContentType("application/json");
                res.setCharacterEncoding("UTF-8");

                out.print(json);
                out.flush();
                out.close();
        }

    }
}
