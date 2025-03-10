package org.megacity.cabservice.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.megacity.cabservice.dto.vehicle_dto.VehicleDetailsDto;
import org.megacity.cabservice.dto.vehicle_dto.VehicleInsertDto;
import org.megacity.cabservice.model.User;
import org.megacity.cabservice.model.Wrappers.ResponseWrapper;
import org.megacity.cabservice.service.DriverAccService;
import org.megacity.cabservice.service.VehicleModelService;
import org.megacity.cabservice.service.VehicleService;

import java.io.IOException;
import java.util.Objects;

public class DriverVehicleController extends HttpServlet {

    private VehicleService vehicleService = new VehicleService();
    private VehicleModelService vehicleModelService = new VehicleModelService();
    private DriverAccService driverAccService = new DriverAccService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String action = req.getParameter("action");

        int modelId = req.getParameter("vehicleModel")!=null? Integer.parseInt(req.getParameter("vehicleModel")):-1;
        String color = req.getParameter("color");
        String plateNo = req.getParameter("plateNumber");
        int seatCount = req.getParameter("seatCount")!=null? Integer.parseInt(req.getParameter("seatCount")):-1;
        boolean available = Objects.equals(req.getParameter("availability"), "on");
        float price_per_km = req.getParameter("pricePerKm")!=null? Float.parseFloat(req.getParameter("pricePerKm")):-1;
        float liters_per_km = req.getParameter("litersPerKm")!=null? Float.parseFloat(req.getParameter("litersPerKm")):-1;
        int driverId = user.getId();
        int ownerId = user.getId();

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
                null

        );

        switch (action) {
            case "add":

                ResponseWrapper<VehicleInsertDto> responseWrapper = vehicleService.addVehicle(vehicleInsertDto);
                if(responseWrapper.getData()==null) {
                    req.setAttribute("message", responseWrapper.getMessage());
                    resp.sendRedirect("driver_vehicle?action=manage");
                }
                else{
                    req.setAttribute("returnVehicle", responseWrapper.getData());
                    req.setAttribute("error", responseWrapper.getMessage());
                    req.setAttribute("vehicleModels", vehicleModelService.getAllVehicleModels());
                    req.setAttribute("drivers", driverAccService.getAllEmployeeDrivers());
                    req.getRequestDispatcher("driver_add_vehicle.jsp").forward(req,resp);
                }
                break;
            case "update":
                int vehicleId = Integer.parseInt(req.getParameter("id"));
                String status = req.getParameter("status");
                if(vehicleService.updateStatus(vehicleId, status)) {
                    req.setAttribute("message", "✅ Vehicle updated");
                }
                else{
                    req.setAttribute("message", "❌ vehicle update failed");
                }
                resp.sendRedirect("driver_vehicle?action=manage");
                break;
            case "renew":
                int id = Integer.parseInt(req.getParameter("id"));
                ResponseWrapper<VehicleInsertDto> wrapper = vehicleService.updateVehicle(vehicleInsertDto,id);
                if(wrapper.getData()==null) {
                    req.setAttribute("message", wrapper.getMessage());
                    resp.sendRedirect("driver_vehicle?action=manage");
                }
                else{
                    req.setAttribute("returnVehicle", wrapper.getData());
                    req.setAttribute("message", wrapper.getMessage());
                    req.setAttribute("vehicleModels", vehicleModelService.getAllVehicleModels());
                    req.getRequestDispatcher("driver_vehicle?action=add").forward(req,resp);
                }
        }




    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute("user");

        String action = req.getParameter("action");

        switch (action){
            case "add":
                req.setAttribute("action", "add");
                req.getRequestDispatcher("driver_add_vehicle.jsp").forward(req,resp);
                break;
            case "manage":
                VehicleDetailsDto vehicle = vehicleService.getVehcileByDriverId(currentUser.getId());
                if(vehicle!=null) {
                    req.setAttribute("vehicle", vehicle);
                    req.getRequestDispatcher("driver_manage_vehicle.jsp").forward(req,resp);
                }
                else{
                    req.setAttribute("action", "add");
                    req.setAttribute("vehicleModels", vehicleModelService.getAllVehicleModels());
                    req.getRequestDispatcher("driver_add_vehicle.jsp").forward(req,resp);
                }
                break;
            case "renew":
                req.setAttribute("action", "renew");
                VehicleDetailsDto temp = vehicleService.getVehcileByDriverId(currentUser.getId());
                VehicleInsertDto vehicleInsertDto = new VehicleInsertDto(
                        temp.getModel().getModelId(),
                        temp.getColor(),
                        temp.getPlate_no(),
                        temp.getSeat_count(),
                        temp.isAvailability(),
                        temp.getPrice_per_km(),
                        temp.getLiters_per_km(),
                        temp.getDriver().getId(),
                        temp.getOwner().getId(),
                        temp.getStatus()
                );
                req.setAttribute("returnVehicle", vehicleInsertDto);
                req.setAttribute("vehicle_id", temp.getId());
                req.setAttribute("vehicleModels", vehicleModelService.getAllVehicleModels());
                req.getRequestDispatcher("driver_add_vehicle.jsp").forward(req,resp);
        }

    }
}
