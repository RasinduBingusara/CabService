package org.megacity.cabservice.repository;

import org.megacity.cabservice.config.DatabaseConnection;
import org.megacity.cabservice.dto.model_dto.ModelDetailsDto;
import org.megacity.cabservice.model.Bill;
import org.megacity.cabservice.model.Fare;
import org.megacity.cabservice.model.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepo {

    public int addTransaction(String paymentMethod, Fare calculatedFare) {
        String sql = "INSERT INTO transaction(amount, sub_total, discount, after_discount, tax, payment_method) " +
                "VALUES (?,?,?,?,?,?)";

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setDouble(1, calculatedFare.getTotal());
            statement.setDouble(2, calculatedFare.getSubTotal());
            statement.setInt(3, calculatedFare.getDiscount());
            statement.setDouble(4, calculatedFare.getAfterDiscount());
            statement.setDouble(5, calculatedFare.getTax());
            statement.setString(6, paymentMethod);

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

    public Bill getBillByBookingId(int id) {
        String sql = "SELECT a.first_name, a.last_name, a.email, a.contact_number," +
                "b.pickup_location, b.destination, b.distance, " +
                "t.id AS transaction_id, t.amount, t.sub_total, t.discount, t.after_discount, t.tax " +
                "FROM booking b JOIN account a ON b.customer_id = a.uid LEFT " +
                "JOIN `transaction` t ON b.transaction_id = t.id " +
                "WHERE b.id = ?";

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    Bill bill = new Bill();
                    Fare fare = new Fare();
                    bill.setName(resultSet.getString("first_name") + " " + resultSet.getString("last_name"));
                    bill.setEmail(resultSet.getString("email"));
                    bill.setContactNumber(resultSet.getString("contact_number"));
                    bill.setPickupLocation(resultSet.getString("pickup_location"));
                    bill.setDropOffLocation(resultSet.getString("destination"));
                    bill.setDistance(resultSet.getDouble("distance"));

                    fare.setTotal(resultSet.getDouble("amount"));
                    fare.setSubTotal(resultSet.getDouble("sub_total"));
                    fare.setDiscount(resultSet.getInt("discount"));
                    fare.setAfterDiscount(resultSet.getDouble("after_discount"));
                    fare.setTax(resultSet.getDouble("tax"));

                    bill.setFare(fare);
                    return bill;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error getting bill: " + e.getMessage(), e);
        }
        return null;
    }

    public List<Double> getMonthlyRevenues(){
        String sql = "SELECT MONTH(paid_at) AS month, SUM(amount) AS total_amount FROM `transaction` " +
                "WHERE YEAR(paid_at) = YEAR(CURDATE()) GROUP BY MONTH(paid_at) ORDER BY MONTH(paid_at);";

        List<Double> monthlyRevenues = new ArrayList<>();
        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    monthlyRevenues.add(resultSet.getDouble("total_amount"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error getting monthly revenues: " + e.getMessage(), e);
        }
        return monthlyRevenues;
    }

    public double getMonthlyRevenue() {
        String sql = "SELECT SUM(t.amount) AS total_revenue FROM `transaction` t JOIN `booking` b ON t.id = b.transaction_id WHERE MONTH(b.booked_at) = MONTH(CURDATE()) AND YEAR(b.booked_at) = YEAR(CURDATE());\n";

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    return resultSet.getDouble("total_revenue");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error getting monthly revenue: " + e.getMessage(), e);
        }
        return 0;
    }
}
