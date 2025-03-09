package org.megacity.cabservice.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.megacity.cabservice.dto.driver_dto.DriverDetailDTO;
import org.megacity.cabservice.dto.driver_dto.DriverInsertDTO;
import org.megacity.cabservice.model.Wrappers.ResponseWrapper;
import org.megacity.cabservice.model.User;
import org.megacity.cabservice.service.DriverAccService;
import org.megacity.cabservice.util.JsonBuilder;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class DriverController extends HttpServlet {

    DriverAccService driverAccService = new DriverAccService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DriverInsertDTO driver = new DriverInsertDTO(
                req.getParameter("first_name"),
                req.getParameter("last_name"),
                req.getParameter("email"),
                req.getParameter("password"),
                req.getParameter("contact_number"),
                "Active",
                req.getParameter("driver_license"),
                req.getParameter("nic"),
                req.getParameter("address"),
                "Employee"
        );
        String confirmPassword = req.getParameter("confirm_password");
        ResponseWrapper<User> responseWrapper = driverAccService.addEmployeeDriverAcc(driver, confirmPassword);
        if(responseWrapper.getData() != null) {
            req.setAttribute("message", responseWrapper.getMessage());
            resp.sendRedirect("drivers?action=view");
        }
        else {
            req.setAttribute("user", driver);
            req.setAttribute("error", responseWrapper.getMessage());
            req.getRequestDispatcher( "add_driver.jsp").forward(req, resp);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        int limit = req.getParameter("limit")!=null? Integer.parseInt(req.getParameter("limit")):-1;
        int offset = req.getParameter("offset")!=null? Integer.parseInt(req.getParameter("offset")):-1;
        String status = req.getParameter("status");
        int driverId = req.getParameter("id")!=null? Integer.parseInt(req.getParameter("id")):-1;


        switch (action) {
            case "view" -> req.getRequestDispatcher("manage_drivers.jsp").forward(req, resp);
            case "add" -> req.getRequestDispatcher("add_driver.jsp").forward(req, resp);
            case "portion" -> {

                List<DriverDetailDTO> dto = driverAccService.getPortionOfDrivers(limit, offset, status);
                String jsonList = JsonBuilder.getInstance().driverDetailDtoToJson(dto);

                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");

                PrintWriter out = resp.getWriter();
                out.print(jsonList);
                out.flush();
            }
            case "search" -> {
                System.out.println("Status:" + status);
                String keyword = req.getParameter("keyword");
                List<DriverDetailDTO> dto = driverAccService.getDriversBySearch(keyword, status);
                String jsonList = JsonBuilder.getInstance().driverDetailDtoToJson(dto);

                System.out.println(jsonList);

                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");

                PrintWriter out = resp.getWriter();
                out.print(jsonList);
                out.flush();
            }
            case "single" -> {

                String driverJson = driverAccService.getDriverByIdInJson(driverId);
                System.out.println("Single Driver: "+driverJson);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");

                PrintWriter out = resp.getWriter();
                out.print(driverJson);
                out.flush();
            }
            case "update" -> {
                if(driverAccService.updateStatus(driverId, status)) {
                    req.setAttribute("message", "✅ Driver status updated Successfully");
                    req.getRequestDispatcher("drivers?action=view").forward(req, resp);
                }
                else{
                    req.setAttribute("message", "❌ Driver status update Failed");
                    req.getRequestDispatcher("drivers?action=view").forward(req, resp);
                }
            }
        }
    }
}
