package org.megacity.cabservice.repository;

import org.megacity.cabservice.config.DatabaseConnection;
import org.megacity.cabservice.dto.driver_dto.DriverDetailDTO;
import org.megacity.cabservice.dto.vehicle_dto.VehicleDetailsDto;
import org.megacity.cabservice.dto.vehicle_dto.VehicleInsertDto;
import org.megacity.cabservice.model.User;
import org.megacity.cabservice.model.VehicleModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleRepo {

    public boolean isPlateNoExist(String plateNo) {
        String sql = "SELECT * FROM vehicle WHERE plate_no = ?";

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, plateNo);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error checking plate no exist: " + e.getMessage(), e);
        }
        return false;
    }

    public boolean checkVehicleAvailabilityByStatus(String id, String status) {
        String sql = "SELECT * FROM vehicle WHERE id = ? AND status = ?";
        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            System.err.println("id: " + id);
            statement.setString(1, id);
            statement.setString(2, status);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    System.err.println("Vehicle already exists with plate no: " + id);
                    return true;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error checking vehicle availability by status: " + e.getMessage(), e);
        }
        return false;
    }

    public boolean addVehicle(VehicleInsertDto vehicle) {
        String sql = "INSERT INTO vehicle(model, color, plate_no, seat_count, availability, price_per_km, liter_per_km, driver_id, owner_id, added_at) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, now())";

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, vehicle.getModelId());
            statement.setString(2, vehicle.getColor());
            statement.setString(3, vehicle.getPlate_no());
            statement.setString(4, String.valueOf(vehicle.getSeat_count()));

            statement.setString(5, vehicle.isAvailability() ?"1":"0");
            statement.setString(6, String.valueOf(vehicle.getPrice_per_km()));
            statement.setString(7, String.valueOf(vehicle.getLiters_per_km()));
            statement.setString(8, String.valueOf(vehicle.getDriverId()));
            statement.setString(9, String.valueOf(vehicle.getOwnerId()));

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting new vehicle: " + e.getMessage(), e);
        }
    }

    public boolean updateStatus(String id, String status) {
        String sql = "UPDATE vehicle SET status = ? WHERE id = ?;";

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, status);
            statement.setString(2, id);

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting new vehicle: " + e.getMessage(), e);
        }
    }

    public double getVehiclePricePerKm(String vehicleId) {
        String sql = "SELECT price_per_km FROM vehicle WHERE id = ?";

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, vehicleId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble("price_per_km");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error while getting vehicle price per km: " + e.getMessage(), e);
        }
        return -1;
    }

    public List<VehicleDetailsDto> getAllVehicles() {
        String sql = "SELECT v.id, v.model AS model_id, vm.model_name, v.color, v.plate_no, v.seat_count, " +
                "v.availability, v.price_per_km, v.liter_per_km, v.driver_id, d.first_name AS driver_first_name, " +
                "d.last_name AS driver_last_name, v.owner_id, o.first_name AS owner_first_name, " +
                "o.last_name AS owner_last_name, v.status, v.updated_at, v.added_at FROM vehicle v " +
                "JOIN vehicle_model vm ON v.model = vm.model_id JOIN account d ON v.driver_id = d.uid " +
                "JOIN account o ON v.owner_id = o.uid;";

        List<VehicleDetailsDto> vehicles = new ArrayList<>();
        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            vehicles = getVehicleDetailsDto(statement);

        } catch (SQLException e) {
            throw new RuntimeException("Error while getting all vehicles: " + e.getMessage(), e);
        }
        return vehicles;
    }
    public List<VehicleDetailsDto> getVehiclesByStatus(String status) {
        String sql = "SELECT v.id, v.model AS model_id, vm.model_name, v.color, v.plate_no, v.seat_count, " +
                "v.availability, v.price_per_km, v.liter_per_km, v.driver_id, d.first_name AS driver_first_name, " +
                "d.last_name AS driver_last_name, v.owner_id, o.first_name AS owner_first_name, " +
                "o.last_name AS owner_last_name, v.status, v.updated_at, v.added_at FROM vehicle v " +
                "JOIN vehicle_model vm ON v.model = vm.model_id JOIN account d ON v.driver_id = d.uid " +
                "JOIN account o ON v.owner_id = o.uid " +
                "WHERE v.status = ?";

        List<VehicleDetailsDto> vehicles = new ArrayList<>();
        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, status);
            vehicles = getVehicleDetailsDto(statement);

        } catch (SQLException e) {
            throw new RuntimeException("Error while getting vehicles by status: " + e.getMessage(), e);
        }
        return vehicles;
    }

    public List<VehicleDetailsDto> getPortionOfVehicles(String limit, String offset) {
        String sql = "SELECT v.id, v.model AS model_id, vm.model_name, v.color, v.plate_no, v.seat_count, " +
                "v.availability, v.price_per_km, v.liter_per_km, v.driver_id, d.first_name AS driver_first_name, " +
                "d.last_name AS driver_last_name, v.owner_id, o.first_name AS owner_first_name, " +
                "o.last_name AS owner_last_name, v.status, v.updated_at, v.added_at FROM vehicle v " +
                "JOIN vehicle_model vm ON v.model = vm.model_id JOIN account d ON v.driver_id = d.uid " +
                "JOIN account o ON v.owner_id = o.uid LIMIT ? OFFSET ?;";
        List<VehicleDetailsDto> vehicles = null;

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setInt(1, Integer.parseInt(limit));
            statement.setInt(2, Integer.parseInt(offset));

            vehicles = getVehicleDetailsDto(statement);

        } catch (SQLException e) {
            throw new RuntimeException("Error getting portion of vehicles: " + e.getMessage(), e);
        }


        return vehicles;
    }

    public List<VehicleDetailsDto> getPortionOfVehiclesWithStatus(String limit, String offset,String status) {
        String sql = "SELECT v.id, v.model AS model_id, vm.model_name, v.color, v.plate_no, v.seat_count, " +
                "v.availability, v.price_per_km, v.liter_per_km, v.driver_id, d.first_name AS driver_first_name, " +
                "d.last_name AS driver_last_name, v.owner_id, o.first_name AS owner_first_name, " +
                "o.last_name AS owner_last_name, v.status, v.updated_at, v.added_at FROM vehicle v " +
                "JOIN vehicle_model vm ON v.model = vm.model_id JOIN account d ON v.driver_id = d.uid " +
                "JOIN account o ON v.owner_id = o.uid WHERE v.status = ? LIMIT ? OFFSET ?;";
        List<VehicleDetailsDto> vehicles = null;

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, status);
            statement.setInt(2, Integer.parseInt(limit));
            statement.setInt(3, Integer.parseInt(offset));
            System.out.println("Database vehicle: " + status);

            vehicles = getVehicleDetailsDto(statement);

        } catch (SQLException e) {
            throw new RuntimeException("Error getting portion of vehicles: " + e.getMessage(), e);
        }


        return vehicles;
    }

    public List<VehicleDetailsDto> getVehiclesBySearch(String keyword) {
        String sql = "SELECT v.id, v.model AS model_id, vm.model_name, v.color, v.plate_no, v.seat_count, " +
                "v.availability, v.price_per_km, v.liter_per_km, v.driver_id, d.first_name AS driver_first_name, " +
                "d.last_name AS driver_last_name, v.owner_id, o.first_name AS owner_first_name, " +
                "o.last_name AS owner_last_name, v.status, v.updated_at, v.added_at FROM vehicle v " +
                "JOIN vehicle_model vm ON v.model = vm.model_id JOIN account d ON v.driver_id = d.uid " +
                "JOIN account o ON v.owner_id = o.uid WHERE " +
                "(plate_no LIKE ?);";
        List<VehicleDetailsDto> vehicles = null;
        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, "%" + keyword + "%");
            vehicles = getVehicleDetailsDto(statement);

        } catch (SQLException e) {
            throw new RuntimeException("Error checking email existence: " + e.getMessage(), e);
        }

        return vehicles;
    }

    public List<VehicleDetailsDto> getVehiclesBySearchWithStatus(String keyword, String status) {
        String sql = "SELECT v.id, v.model AS model_id, vm.model_name, v.color, v.plate_no, v.seat_count, " +
                "v.availability, v.price_per_km, v.liter_per_km, v.driver_id, d.first_name AS driver_first_name, " +
                "d.last_name AS driver_last_name, v.owner_id, o.first_name AS owner_first_name, " +
                "o.last_name AS owner_last_name, v.status, v.updated_at, v.added_at FROM vehicle v " +
                "JOIN vehicle_model vm ON v.model = vm.model_id JOIN account d ON v.driver_id = d.uid " +
                "JOIN account o ON v.owner_id = o.uid WHERE v.status = ? " +
                "AND (plate_no LIKE ?  OR seat_count LIKE ?);";
        List<VehicleDetailsDto> vehicles = null;
        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, status);
            statement.setString(2, "%" + keyword + "%");
            statement.setString(3, "%" + keyword + "%");

            vehicles = getVehicleDetailsDto(statement);

        } catch (SQLException e) {
            throw new RuntimeException("Error checking email existence: " + e.getMessage(), e);
        }

        return vehicles;
    }

    private List<VehicleDetailsDto> getVehicleDetailsDto(PreparedStatement statement) throws SQLException {
        List<VehicleDetailsDto> vehicles = new ArrayList<>();
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                VehicleDetailsDto dto = new VehicleDetailsDto(
                        resultSet.getString("id"),
                        new VehicleModel(resultSet.getString("model_id"),resultSet.getString("model_name")),
                        resultSet.getString("color"),
                        resultSet.getString("plate_no"),
                        resultSet.getInt("seat_count"),
                        resultSet.getInt("availability") == 1,
                        resultSet.getFloat("price_per_km"),
                        resultSet.getFloat("liter_per_km"),
                        new User(resultSet.getString("driver_id"),resultSet.getString("driver_first_name"), resultSet.getString("driver_last_name")),
                        new User(resultSet.getString("owner_id"),resultSet.getString("owner_first_name"), resultSet.getString("owner_last_name")),
                        resultSet.getString("status"),
                        resultSet.getString("updated_at"),
                        resultSet.getString("added_at")
                );
                vehicles.add(dto);
            }
        }
        return vehicles;
    }
}
