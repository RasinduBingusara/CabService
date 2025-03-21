package org.megacity.cabservice.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.megacity.cabservice.model.Wrappers.ResponseWrapper;
import org.megacity.cabservice.model.User;
import org.megacity.cabservice.service.AccountService;

import java.io.IOException;

public class SignupController extends HttpServlet {

    private AccountService accountService = new AccountService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String contactNo = request.getParameter("contact_number");
        String nic = request.getParameter("nic");
        String address = request.getParameter("address");
        String driverLicense = request.getParameter("driver_license");
        String usertype = request.getParameter("user_type");

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setContactNumber(contactNo);
        user.setNic(nic);
        user.setAddress(address);
        user.setDriverLicense(driverLicense);
        user.setUserType(usertype);


        ResponseWrapper<User> responseWrapper = accountService.createAccount(user, confirmPassword);

        if(responseWrapper.getData() != null) {
            response.sendRedirect("login.jsp");
        }
        else{
            System.out.println("Error creating account");
            request.setAttribute("user", user);
            request.setAttribute("error", responseWrapper.getMessage());
            request.setAttribute("type", usertype);
            request.getRequestDispatcher("signup.jsp").forward(request,response);
        }


    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        switch (type) {
            case "Driver":
                request.setAttribute("type", "Driver");
                request.getRequestDispatcher("signup.jsp").forward(request,response);
                break;
            case "Customer":
                request.setAttribute("type", "Customer");
                request.getRequestDispatcher("signup.jsp").forward(request,response);
                break;
        }
    }
}
