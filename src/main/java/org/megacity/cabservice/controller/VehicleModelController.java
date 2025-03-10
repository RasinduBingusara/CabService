package org.megacity.cabservice.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.megacity.cabservice.dto.model_dto.ModelInsertDto;
import org.megacity.cabservice.model.Wrappers.ResponseWrapper;
import org.megacity.cabservice.service.VehicleModelService;

import java.io.IOException;
import java.io.PrintWriter;

public class VehicleModelController extends HttpServlet {

    private VehicleModelService vehicleModelService = new VehicleModelService();

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        String type = req.getParameter("vehicleType");
        String modelName = req.getParameter("modelName");
        String modelYear = req.getParameter("year");
        String manufacturer = req.getParameter("manufacturer");
        String fuelType = req.getParameter("fuelType");
        String transmission = req.getParameter("transmission");

        ModelInsertDto model = new ModelInsertDto(modelName,type,manufacturer,modelYear,fuelType,transmission,"Active");
        ResponseWrapper<ModelInsertDto> responseWrapper = vehicleModelService.addVehicleModel(model);
        if(responseWrapper.getData() == null) {
            req.setAttribute("message", responseWrapper.getMessage());
            res.sendRedirect("model?action=view");
        }
        else{
            req.setAttribute("model", responseWrapper.getData());
            req.setAttribute("error", responseWrapper.getMessage());
            req.getRequestDispatcher("add_model.jsp").forward(req,res);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String action = req.getParameter("action");

        switch (action) {
            case "view" -> req.getRequestDispatcher("manage_model.jsp").forward(req, res);
            case "add" -> req.getRequestDispatcher("add_model.jsp").forward(req, res);
            case "load" -> {
                String json = vehicleModelService.getAllVehicleModelsInJason();

                res.setContentType("application/json");
                res.setCharacterEncoding("UTF-8");

                PrintWriter out = res.getWriter();
                out.print(json);
                out.flush();
            }
            case "update" -> {
                int id = Integer.parseInt(req.getParameter("model_id"));
                String status = req.getParameter("status");
                if(vehicleModelService.updateStatus(id, status)) {
                    req.setAttribute("message", "✅ Vehicle model status updated sucessfully");
                    req.getRequestDispatcher("model?action=view").forward(req, res);
                }
                else{
                    req.setAttribute("message", "❌ Vehicle model status update failed");
                    req.getRequestDispatcher("model?action=view").forward(req, res);
                }
            }
        }
    }
}
