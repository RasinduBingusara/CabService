package org.megacity.cabservice.repository;

import org.megacity.cabservice.config.DatabaseConnection;
import org.megacity.cabservice.dto.admin_dto.AdminResponseDTO;
import org.megacity.cabservice.dto.driver_dto.DriverInsertDTO;
import org.megacity.cabservice.dto.driver_dto.DriverResponseDTO;
import org.megacity.cabservice.dto.user_dto.UserInsertDTO;
import org.megacity.cabservice.dto.user_dto.UserResponseDTO;
import org.megacity.cabservice.mapper.AdminMapper;
import org.megacity.cabservice.mapper.DriverMapper;
import org.megacity.cabservice.mapper.UserMapper;
import org.megacity.cabservice.model.PasswordWrapper;
import org.megacity.cabservice.model.User;

import java.sql.*;

public class AccountRepo {

    public boolean isEmailExist(String email) {
        String sql = "SELECT * FROM account WHERE email = ?";
        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error checking email existence: " + e.getMessage(), e);
        }

    }

    public PasswordWrapper<User> getUserByEmail(String email) {
        String sql = "SELECT * FROM account WHERE email = ?";
        User user = null;
        String password = null;

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    switch(resultSet.getString("user_type")){
                        case "Customer":

                            UserResponseDTO responseDTO = new UserResponseDTO(
                                    resultSet.getString("first_name"),
                                    resultSet.getString("last_name"),
                                    resultSet.getString("email"),
                                    resultSet.getString("contact_number"),
                                    resultSet.getString("status")
                            );
                            user = UserMapper.getInstance().toEntity(responseDTO);
                            password = resultSet.getString("password");
                            break;

                        case "Driver":
                            DriverResponseDTO response = new DriverResponseDTO(
                                    resultSet.getString("first_name"),
                                    resultSet.getString("last_name"),
                                    resultSet.getString("email"),
                                    resultSet.getString("contact_number"),
                                    resultSet.getString("status"),
                                    resultSet.getString("driver_license"),
                                    resultSet.getString("nic"),
                                    resultSet.getString("address")
                            );
                            user = DriverMapper.getInstance().toEntity(response);
                            password = resultSet.getString("password");
                            break;

                        case "Admin":
                            AdminResponseDTO responseAdmin = new AdminResponseDTO(
                                    resultSet.getString("first_name"),
                                    resultSet.getString("last_name"),
                                    resultSet.getString("email")
                            );
                            user = AdminMapper.getInstance().toEntity(responseAdmin);
                            password = resultSet.getString("password");
                            break;
                    }

                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error checking email existence: " + e.getMessage(), e);
        }
        return new PasswordWrapper<>(password, user);
    }



    public Boolean addNewCustomer(UserInsertDTO user) {
        String sql = "INSERT INTO account (email, first_name, last_name, password, contact_number, user_type, created_at) " +
                "VALUES (?, ?, ?, ?, ?, ?, now())";

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getContactNumber());
            statement.setString(6, "Customer");

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting user: " + e.getMessage(), e);
        }

    }

    public Boolean addNewDriver(DriverInsertDTO driver) {
        String sql = "INSERT INTO account " +
                "(email, first_name, last_name, password, contact_number, user_type, driver_license, nic, address, created_at) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, now())";

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, driver.getEmail());
            statement.setString(2, driver.getFirstName());
            statement.setString(3, driver.getLastName());
            statement.setString(4, driver.getPassword());
            statement.setString(5, driver.getContactNumber());
            statement.setString(6, driver.getDriverLicense());
            statement.setString(7, driver.getNic());
            statement.setString(8, driver.getAddress());
            statement.setString(9, "Driver");

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting user: " + e.getMessage(), e);
        }
    }
}
