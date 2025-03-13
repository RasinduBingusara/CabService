package org.megacity.cabservice.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.megacity.cabservice.model.User;
import org.megacity.cabservice.model.Wrappers.BooleanWrapper;
import org.megacity.cabservice.service.AccountService;

import java.io.IOException;
import java.io.PrintWriter;

public class ProfileController extends HttpServlet {

    AccountService accountService = new AccountService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        User loggedUser = (User) session.getAttribute("user");


        if(action.equals("update")){
            String newPassword = req.getParameter("newpassword");
            String confirmPassword = req.getParameter("confirmpassword");

            BooleanWrapper booleanWrapper = accountService.updateAccountPassword(loggedUser.getEmail(), newPassword, confirmPassword);
            if(booleanWrapper.isValue()){
                req.setAttribute("message",booleanWrapper.getMessage());
                req.getRequestDispatcher("customer_profile.jsp").forward(req, resp);
            }
            else{
                req.setAttribute("message",booleanWrapper.getMessage());
                req.getRequestDispatcher("customer_profile.jsp").forward(req, resp);
            }
        }
        else if(action.equals("logout")){
            System.out.println("Logged out");
            session.removeAttribute("user");
            resp.sendRedirect("/");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String action = request.getParameter("action");
        int id = user.getId();
        String json = "";
        PrintWriter out = response.getWriter();

        if(user != null) {
            switch (action) {
                case "c":
                    request.getRequestDispatcher("customer_profile.jsp").forward(request, response);
                    break;
                case "d":
                    request.getRequestDispatcher("driver_profile.jsp").forward(request, response);
                    break;
                case "history":
                    json = accountService.getProfileInfoInJson(user.getEmail());
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
}
