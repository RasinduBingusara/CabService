package org.megacity.cabservice.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.megacity.cabservice.model.User;

import java.io.IOException;

public class ProfileController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String update = req.getParameter("update");
        HttpSession session = req.getSession();
        User loggedUser = (User) session.getAttribute("user");

        switch (update) {
            case "password":

                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("customer_profile.jsp").forward(request, response);
    }
}
