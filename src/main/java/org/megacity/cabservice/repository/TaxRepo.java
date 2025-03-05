package org.megacity.cabservice.repository;

import org.megacity.cabservice.config.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaxRepo {

    public double getTaxByKeyName(String KeyName) {
        String sql = "SELECT percentage FROM taxes WHERE key_name = ?";

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, KeyName);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble("percentage");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error while getting tax percentage: " + e.getMessage(), e);
        }
        return -1;
    }
}
