package org.megacity.cabservice.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.megacity.cabservice.dto.user_dto.UserAuthDTO;
import org.megacity.cabservice.model.Wrappers.ResponseWrapper;
import org.megacity.cabservice.model.User;
import org.megacity.cabservice.service.AccountService;

import java.io.IOException;

public class LoginController extends HttpServlet {

    private AccountService accountService = new AccountService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserAuthDTO userAuthDTO = new UserAuthDTO(email, password);

        ResponseWrapper<User> authResponse = accountService.login(userAuthDTO);

        if(authResponse.getData() != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", authResponse.getData());
            switch (authResponse.getData().getUserType()){
                case "Customer":
                    request.setAttribute("message", authResponse.getMessage());
                    request.getRequestDispatcher("home.jsp").forward(request, response);
                    break;
                case "Driver":
                    request.setAttribute("message", authResponse.getMessage());
                    request.getRequestDispatcher("driver_dashboard.jsp").forward(request, response);
                    break;
                case "Admin":
                    request.setAttribute("message", authResponse.getMessage());
                    request.getRequestDispatcher("admin_dashboard.jsp").forward(request, response);
                    break;
            }
        }
        else {
            request.setAttribute("error", authResponse.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
