package org.megacity.cabservice.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.megacity.cabservice.model.User;
import org.megacity.cabservice.service.BookingService;

import java.io.IOException;
import java.io.PrintWriter;

public class CustomerBookingController extends HttpServlet {

    private BookingService bookingService = new BookingService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
            case "history":
                System.out.println("History");
                json = bookingService.getBookingsByCustomerId(id);
                System.out.println(json);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                out.print(json);
                out.flush();
                out.close();
                break;
        }


    }
}

