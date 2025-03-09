package org.megacity.cabservice.repository;


import org.megacity.cabservice.config.DatabaseConnection;
import org.megacity.cabservice.dto.model_dto.ModelDetailsDto;
import org.megacity.cabservice.dto.model_dto.ModelInsertDto;
import org.megacity.cabservice.model.VehicleModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleModelRepo {

    public boolean addVehicleType(ModelInsertDto vehicleModel) {
        String sql = "INSERT INTO vehicle_model(model_name, vehicle_type, manufacturer, year, fuel_type, transmission, status, added_at) " +
                "VALUES (?,?,?,?,?,?,?,now())";

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, vehicleModel.getModelName());
            statement.setString(2, vehicleModel.getVehicleType());
            statement.setString(3, vehicleModel.getManufacturer());
            statement.setString(4, vehicleModel.getYear());
            statement.setString(5, vehicleModel.getFuelType());
            statement.setString(6, vehicleModel.getTransmission());
            statement.setString(7, vehicleModel.getStatus());

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting user: " + e.getMessage(), e);
        }
    }

    public boolean isModelNameExist(String modelName) {
        String sql = "SELECT * FROM vehicle_model WHERE model_name = ?";

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, modelName);
            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    return true;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error checking model name existence: " + e.getMessage(), e);
        }
        return false;
    }
    public boolean updateStatus(int id, String status) {
        String sql = "UPDATE vehicle_model SET status = ?  WHERE model_id = ?";

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, status);
            statement.setInt(2, id);

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting user: " + e.getMessage(), e);
        }
    }
    public List<ModelDetailsDto> getAllVehicleModels() {
        String sql = "SELECT * FROM vehicle_model";
        List<ModelDetailsDto> vehicleModels = new ArrayList<>();
        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    vehicleModels.add(new ModelDetailsDto(
                            resultSet.getInt("model_id"),
                            resultSet.getString("model_name"),
                            resultSet.getString("vehicle_type"),
                            resultSet.getString("manufacturer"),
                            resultSet.getString("year"),
                            resultSet.getString("fuel_type"),
                            resultSet.getString("transmission"),
                            resultSet.getString("status"),
                            resultSet.getString("added_at")
                    ));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error checking email existence: " + e.getMessage(), e);
        }
        return vehicleModels;
    }
}
