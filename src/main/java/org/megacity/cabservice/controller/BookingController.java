package org.megacity.cabservice.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.megacity.cabservice.dto.booking_dto.BookingInsertDto;
import org.megacity.cabservice.model.Booking;
import org.megacity.cabservice.model.User;
import org.megacity.cabservice.model.Wrappers.ResponseWrapper;
import org.megacity.cabservice.service.BookingService;

import java.io.IOException;
import java.io.PrintWriter;

public class BookingController extends HttpServlet {

    private BookingService bookingService = new BookingService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        BookingInsertDto newBooking = new BookingInsertDto(
                user.getId(),
                user.getId(),
                request.getParameter("vehicle_id"),
                request.getParameter("pickup_location"),
                request.getParameter("destination"),
                null,
                Double.parseDouble(request.getParameter("distance")),
                "Pending"
        );

        ResponseWrapper<BookingInsertDto> responseWrapper = bookingService.addNewBooking(newBooking);
        if(responseWrapper.getData() == null){
            request.setAttribute("message", responseWrapper.getMessage());
            request.getRequestDispatcher("path").forward(request, response);
        }
        else{
            request.setAttribute("error", responseWrapper.getMessage());
            request.getRequestDispatcher("path").forward(request, response);
        }

    }

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
            case "add":
//                req.setAttribute("vehicleModels", vehicleModelService.getAllVehicleModels());
//                req.setAttribute("drivers", driverAccService.getAllEmployeeDrivers());
//                req.getRequestDispatcher("add_vehicle.jsp").forward(req,res);
                break;
            case "portion":
                System.out.println("Portion");
                json = bookingService.getPortionOfBookingsInJson(limit,offset,status);
                System.out.println(json);
                res.setContentType("application/json");
                res.setCharacterEncoding("UTF-8");

                out.print(json);
                out.flush();
                out.close();
                break;
            case "search":
                System.out.println("Searching");
                json = bookingService.getBookingBySearchInJson(keyword,status);
                System.out.println(json);
                res.setContentType("application/json");
                res.setCharacterEncoding("UTF-8");

                out.print(json);
                out.flush();
                out.close();
                break;
        }

    }
}
