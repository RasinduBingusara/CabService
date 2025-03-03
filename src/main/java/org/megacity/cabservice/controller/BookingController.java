package org.megacity.cabservice.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.megacity.cabservice.service.BookingService;

import java.io.IOException;
import java.io.PrintWriter;

public class BookingController extends HttpServlet {

    private BookingService bookingService = new BookingService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String action = req.getParameter("action");
        String limit= req.getParameter("limit");
        String offset= req.getParameter("offset");
        String status= req.getParameter("status");
        String keyword = req.getParameter("keyword");

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
                String json = bookingService.getPortionOfBookingsInJson(limit,offset,status);
                System.out.println(json);
                res.setContentType("application/json");
                res.setCharacterEncoding("UTF-8");

                PrintWriter out = res.getWriter();
                out.print(json);
                out.flush();
                out.close();
                break;
            case "search":
                System.out.println("Searching");
                String searchJson = bookingService.getBookingBySearchInJson(keyword,status);
                System.out.println(searchJson);
                res.setContentType("application/json");
                res.setCharacterEncoding("UTF-8");

                PrintWriter outs = res.getWriter();
                outs.print(searchJson);
                outs.flush();
                outs.close();
                break;
        }

    }
}
