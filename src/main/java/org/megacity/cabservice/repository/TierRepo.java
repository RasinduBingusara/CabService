package org.megacity.cabservice.repository;

import org.megacity.cabservice.config.DatabaseConnection;
import org.megacity.cabservice.model.Tier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TierRepo {

    public int getDiscountByTier(String tierName) {
        String sql = "SELECT percentage FROM tier WHERE tier_name = ?";

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, tierName);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("percentage");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error while getting tier discount: " + e.getMessage(), e);
        }
        return -1;
    }

    public Tier getTierNameByCustomerId(int customerId) {
        String sql = "SELECT t.id, t.tier_name, t.percentage, t.updated_at FROM tier t JOIN account a ON t.id = a.tier_id " +
                "WHERE a.uid = ?;";

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setInt(1, customerId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Tier(
                            resultSet.getInt("id"),
                            resultSet.getString("tier_name"),
                            resultSet.getInt("percentage")
                    );
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error while getting tier discount: " + e.getMessage(), e);
        }
        return null;
    }
}
