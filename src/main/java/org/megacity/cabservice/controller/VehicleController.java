package org.megacity.cabservice.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.megacity.cabservice.dto.vehicle_dto.VehicleInsertDto;
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
        String modelId = req.getParameter("vehicleModel");
        String color = req.getParameter("color");
        String plateNo = req.getParameter("plateNumber");
        int seatCount = Integer.parseInt(req.getParameter("seatCount"));
        boolean available = Objects.equals(req.getParameter("availability"), "on");
        float price_per_km = Float.parseFloat(req.getParameter("pricePerKm"));
        float liters_per_km = Float.parseFloat(req.getParameter("litersPerKm"));
        String driverId = req.getParameter("driver");
        String ownerId = "-1";

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
        String limit= req.getParameter("limit");
        String offset= req.getParameter("offset");
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
        }
    }
}
