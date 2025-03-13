package org.megacity.cabservice.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.megacity.cabservice.dto.vehicle_dto.VehicleInsertDto;
import org.megacity.cabservice.model.User;
import org.megacity.cabservice.model.Wrappers.ResponseWrapper;
import org.megacity.cabservice.service.DriverAccService;
import org.megacity.cabservice.service.VehicleModelService;
import org.megacity.cabservice.service.VehicleService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

public class VehicleController extends HttpServlet {

    private VehicleService vehicleService = new VehicleService();
    private VehicleModelService vehicleModelService = new VehicleModelService();
    private DriverAccService driverAccService = new DriverAccService();

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        User user = (User) req.getSession().getAttribute("user");
        int modelId = req.getParameter("vehicleModel")!=null? Integer.parseInt(req.getParameter("vehicleModel")):-1;
        String color = req.getParameter("color");
        String plateNo = req.getParameter("plateNumber");
        int seatCount = req.getParameter("seatCount")!=null? Integer.parseInt(req.getParameter("seatCount")):-1;
        boolean available = Objects.equals(req.getParameter("availability"), "on");
        float price_per_km = Float.parseFloat(req.getParameter("pricePerKm"));
        float liters_per_km = Float.parseFloat(req.getParameter("litersPerKm"));
        int driverId = Integer.parseInt(req.getParameter("driver"));
        int ownerId = 1;

        VehicleInsertDto vehicleInsertDto = new VehicleInsertDto(
                modelId,
                color,
                plateNo,
                seatCount,
                available,
                price_per_km,
                liters_per_km,
                driverId,
                ownerId,
                "Active"

        );
        ResponseWrapper<VehicleInsertDto> responseWrapper = vehicleService.addVehicle(vehicleInsertDto);
        if(responseWrapper.getData()==null) {
            req.setAttribute("message", responseWrapper.getMessage());
            res.sendRedirect("vehicles?action=view");
        }
        else{
            req.setAttribute("returnVehicle", responseWrapper.getData());
            req.setAttribute("error", responseWrapper.getMessage());
            req.setAttribute("vehicleModels", vehicleModelService.getAllVehicleModels());
            req.setAttribute("drivers", driverAccService.getAllEmployeeDrivers());
            req.getRequestDispatcher("add_vehicle.jsp").forward(req,res);
        }

    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String action = req.getParameter("action");
        int limit = req.getParameter("limit")!=null? Integer.parseInt(req.getParameter("limit")):-1;
        int offset = req.getParameter("offset")!=null? Integer.parseInt(req.getParameter("offset")):-1;
        String status= req.getParameter("status");
        String keyword = req.getParameter("keyword");

        String json;
        PrintWriter out = res.getWriter();

        switch (action) {
            case "view":
                req.getRequestDispatcher("manage_vehicle.jsp").forward(req,res);
                break;
            case "add":
                req.setAttribute("vehicleModels", vehicleModelService.getAllVehicleModels());
                req.setAttribute("drivers", driverAccService.getAllEmployeeDrivers());
                req.getRequestDispatcher("add_vehicle.jsp").forward(req,res);
                break;
            case "portion":
                json = vehicleService.getPortionOfVehicles(limit,offset,status);
                res.setContentType("application/json");
                res.setCharacterEncoding("UTF-8");


                out.print(json);
                out.flush();
                out.close();
                break;
            case "search":
                System.out.println("Searching");
                json = vehicleService.getVehiclesBySearch(keyword,status);
                System.out.println(json);
                res.setContentType("application/json");
                res.setCharacterEncoding("UTF-8");

                out.print(json);
                out.flush();
                out.close();
                break;
            case "filter":
                json = vehicleService.getVehiclesByStatusInJson(status);
                res.setContentType("application/json");
                res.setCharacterEncoding("UTF-8");

                out.print(json);
                out.flush();
                out.close();
                break;
            case "update":
                int id = Integer.parseInt(req.getParameter("vehicle_id"));
                if(vehicleService.updateStatus(id, status)) {
                    req.setAttribute("message", "✅ Vehicle status updated sucessfully");
                    req.getRequestDispatcher("vehicles?action=view").forward(req, res);
                }
                else{
                    req.setAttribute("message", "❌ Vehicle status update failed");
                    req.getRequestDispatcher("vehicles?action=view").forward(req, res);
                }
        }
    }
}
