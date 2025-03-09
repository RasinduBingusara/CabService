package org.megacity.cabservice.repository;

import org.megacity.cabservice.config.DatabaseConnection;
import org.megacity.cabservice.model.Transaction;

import java.sql.*;

public class TransactionRepo {

    public int addTransaction(Transaction transaction) {
        String sql = "INSERT INTO `transaction`(amount, payment_method) VALUES (?, ?)";

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setDouble(1, transaction.getAmount());
            statement.setString(2, transaction.getPaymentMethod());

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }

            return -1;

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting new transaction: " + e.getMessage(), e);
        }
    }

    public boolean updatePaidTime(int id) {
        String sql = "UPDATE `transaction` SET paid_at= now() WHERE id = ?";

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setInt(1, id);

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error updating transaction time: " + e.getMessage(), e);
        }
    }

}
