package org.megacity.cabservice.repository;

import org.megacity.cabservice.config.DatabaseConnection;
import org.megacity.cabservice.dto.admin_dto.AdminResponseDTO;
import org.megacity.cabservice.dto.driver_dto.DriverDetailDTO;
import org.megacity.cabservice.dto.driver_dto.DriverInsertDTO;
import org.megacity.cabservice.dto.driver_dto.DriverResponseDTO;
import org.megacity.cabservice.dto.user_dto.UserDetailDTO;
import org.megacity.cabservice.dto.user_dto.UserInsertDTO;
import org.megacity.cabservice.dto.user_dto.UserResponseDTO;
import org.megacity.cabservice.mapper.AdminMapper;
import org.megacity.cabservice.mapper.DriverMapper;
import org.megacity.cabservice.mapper.UserMapper;
import org.megacity.cabservice.model.Wrappers.PasswordWrapper;
import org.megacity.cabservice.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    public boolean isNicExist(String nic) {
        String sql = "SELECT * FROM account WHERE nic = ?";
        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, nic);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error checking nic existence: " + e.getMessage(), e);
        }

    }

    public boolean isDriverLicenseExist(String driverLicense) {
        String sql = "SELECT * FROM account WHERE driver_license = ?";
        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, driverLicense);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error checking driver license existence: " + e.getMessage(), e);
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
                                    resultSet.getInt("uid"),
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
                                    resultSet.getInt("uid"),
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

    public Boolean updatePassword(String email, String newPassword) {
        String sql = "UPDATE account SET password = ? WHERE email = ?";

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, newPassword);
            statement.setString(2, email);

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error updating password: " + e.getMessage(), e);
        }
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
                "(email, first_name, last_name, password, contact_number, user_type, driver_license, nic, address,employment_type, created_at) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now())";

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, driver.getEmail());
            statement.setString(2, driver.getFirstName());
            statement.setString(3, driver.getLastName());
            statement.setString(4, driver.getPassword());
            statement.setString(5, driver.getContactNumber());
            statement.setString(6, driver.getUserType());
            statement.setString(7, driver.getDriverLicense());
            statement.setString(8, driver.getNic());
            statement.setString(9, driver.getAddress());
            statement.setString(10, driver.getEmploymentType());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting user: " + e.getMessage(), e);
        }
    }

    public boolean updateStatus(int id, String status) {
        String sql = "UPDATE account SET status= ?  WHERE uid = ? AND user_type = ?;";

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, status);
            statement.setInt(2, id);
            statement.setString(3, "Driver");

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error updating status: " + e.getMessage(), e);
        }
    }

    public boolean updateTierIfDifferent(int userId, int newTierId) {
        String selectSql = "SELECT tier_id FROM account WHERE uid = ?";
        String updateSql = "UPDATE account SET tier_id = ? WHERE uid = ? AND tier_id <> ?";

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement selectStmt = con.prepareStatement(selectSql);
             PreparedStatement updateStmt = con.prepareStatement(updateSql)) {

            selectStmt.setInt(1, userId);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                int currentTierId = rs.getInt("tier_id");

                if (currentTierId == newTierId) {
                    return true;
                }

                updateStmt.setInt(1, newTierId);
                updateStmt.setInt(2, userId);
                updateStmt.setInt(3, newTierId);

                int rowsUpdated = updateStmt.executeUpdate();
                return rowsUpdated > 0;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error updating tier: " + e.getMessage(), e);
        }
        return false;
    }


    public UserDetailDTO getUserDetails(String email) {
        String sql = "SELECT * FROM account WHERE email = ?";

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {

                    return new UserDetailDTO(
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getString("email"),
                            resultSet.getString("contact_number")
                    );
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error checking email existence: " + e.getMessage(), e);
        }
        return null;

    }

    public DriverDetailDTO getDriverDetails(String email) {
        String sql = "SELECT * FROM account WHERE email = ?";

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {

                    return new DriverDetailDTO(
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getString("email"),
                            resultSet.getString("contact_number"),
                            resultSet.getString("driver_license"),
                            resultSet.getString("nic"),
                            resultSet.getString("address")
                    );
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error checking email existence: " + e.getMessage(), e);
        }
        return null;

    }

    public List<DriverDetailDTO> getAllDrivers() {
        String sql = "SELECT * FROM account WHERE user_type = ?";
        List<DriverDetailDTO> drivers = null;

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, "Driver");
            drivers = getDriverDetailDTOS(statement);

        } catch (SQLException e) {
            throw new RuntimeException("Error checking email existence: " + e.getMessage(), e);
        }


        return drivers;
    }

    public List<DriverDetailDTO> getAllEmployeeDrivers() {
        String sql = "SELECT a.* " +
                "FROM `account` a LEFT JOIN `vehicle` v ON a.uid = v.driver_id " +
                "WHERE a.user_type = ? AND a.employment_type = ? AND v.driver_id IS NULL;";
        List<DriverDetailDTO> drivers = null;

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, "Driver");
            statement.setString(2, "Employee");
            drivers = getDriverDetailDTOS(statement);

        } catch (SQLException e) {
            throw new RuntimeException("Error getting all employee drivers: " + e.getMessage(), e);
        }


        return drivers;
    }

    public DriverDetailDTO getDriverById(int id) {
        String sql = "SELECT * FROM account WHERE uid = ?";
        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new DriverDetailDTO(
                            resultSet.getInt("uid"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getString("email"),
                            resultSet.getString("contact_number"),
                            resultSet.getString("user_type"),
                            resultSet.getString("status"),
                            resultSet.getString("driver_license"),
                            resultSet.getString("nic"),
                            resultSet.getString("address"),
                            resultSet.getString("employment_type"),
                            resultSet.getString("updated_at"),
                            resultSet.getString("created_at")
                    );
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error checking email existence: " + e.getMessage(), e);
        }
        return null;
    }

    public List<DriverDetailDTO> getPortionOfDriver(int limit, int offset) {
        String sql = "SELECT * FROM account WHERE user_type = ? LIMIT ? OFFSET ?";
        List<DriverDetailDTO> drivers = null;

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, "Driver");
            statement.setInt(2, limit);
            statement.setInt(3, offset);
            drivers = getDriverDetailDTOS(statement);

        } catch (SQLException e) {
            throw new RuntimeException("Error checking email existence: " + e.getMessage(), e);
        }


        return drivers;
    }

    public List<DriverDetailDTO> getPortionOfDriverWithStatus(int limit, int offset, String status) {
        String sql = "SELECT * FROM account WHERE user_type = ? AND status = ? LIMIT ? OFFSET ?";
        List<DriverDetailDTO> drivers = null;

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, "Driver");
            statement.setString(2, status);
            statement.setInt(3, limit);
            statement.setInt(4, offset);
            drivers = getDriverDetailDTOS(statement);

        } catch (SQLException e) {
            throw new RuntimeException("Error checking email existence: " + e.getMessage(), e);
        }


        return drivers;
    }

    private List<DriverDetailDTO> getDriverDetailDTOS(PreparedStatement statement) throws SQLException {
        List<DriverDetailDTO> drivers = new ArrayList<>();
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {

                DriverDetailDTO detailDTO = new DriverDetailDTO(
                        resultSet.getInt("uid"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getString("contact_number"),
                        resultSet.getString("user_type"),
                        resultSet.getString("status"),
                        resultSet.getString("driver_license"),
                        resultSet.getString("nic"),
                        resultSet.getString("address"),
                        resultSet.getString("employment_type"),
                        resultSet.getString("updated_at"),
                        resultSet.getString("created_at")
                );
                drivers.add(detailDTO);
            }
        }
        return drivers;
    }

    public List<DriverDetailDTO> getDriversBySearch(String keyword) {
        String sql = "SELECT * FROM account WHERE user_type = ? AND (first_name LIKE ?  OR last_name LIKE ? OR nic LIKE ? OR driver_license LIKE ?)";
        List<DriverDetailDTO> drivers = null;
        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, "Driver");
            statement.setString(2, "%" + keyword + "%");
            statement.setString(3, "%" + keyword + "%");
            statement.setString(4, "%" + keyword + "%");
            statement.setString(5, "%" + keyword + "%");

            drivers = getDriverDetailDTOS(statement);

        } catch (SQLException e) {
            throw new RuntimeException("Error checking email existence: " + e.getMessage(), e);
        }

        return drivers;
    }

    public List<DriverDetailDTO> getDriversBySearchWithStatus(String keyword, String status) {
        String sql = "SELECT * FROM account WHERE user_type = ? AND status = ? AND (first_name LIKE ?  OR last_name LIKE ? OR nic LIKE ? OR driver_license LIKE ?)";
        List<DriverDetailDTO> drivers = null;
        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, "Driver");
            statement.setString(2, status);
            statement.setString(3, "%" + keyword + "%");
            statement.setString(4, "%" + keyword + "%");
            statement.setString(5, "%" + keyword + "%");
            statement.setString(6, "%" + keyword + "%");

            drivers = getDriverDetailDTOS(statement);

        } catch (SQLException e) {
            throw new RuntimeException("Error checking email existence: " + e.getMessage(), e);
        }

        return drivers;
    }

    public int getTotalActiveDriversCount() {
        String sql = "SELECT COUNT(*) AS active_drivers FROM `account` WHERE user_type = 'driver' AND status = 'active';";

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("active_drivers");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error getting total driver Count: " + e.getMessage(), e);
        }
        return 0;
    }
}
