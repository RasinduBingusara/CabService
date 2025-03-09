package org.megacity.cabservice.repository;

import org.megacity.cabservice.config.DatabaseConnection;
import org.megacity.cabservice.dto.booking_dto.BookingInsertDto;
import org.megacity.cabservice.dto.vehicle_dto.VehicleDetailsDto;
import org.megacity.cabservice.model.*;
import org.megacity.cabservice.model.Users.Customer;
import org.megacity.cabservice.model.Users.Driver;

import java.awt.print.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingRepo {

    public int addNewBooking(BookingInsertDto booking) {
        String sql = "INSERT INTO booking" +
                "(customer_id, user_id, car_id,transaction_id, pickup_location, destination, " +
                "distance, pickup_time, status, booked_at) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, now(), ?,  now())";

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, booking.getCustomerId());
            statement.setString(2, booking.getUserId());
            statement.setString(3, booking.getVehicleId());
            statement.setString(4, booking.getTransactionId());
            statement.setString(5, booking.getPickupLocation());
            statement.setString(6, booking.getDestination());
            statement.setDouble(7, booking.getDistance());
            statement.setString(8, booking.getStatus());

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
            throw new RuntimeException("Error inserting new booking: " + e.getMessage(), e);
        }
    }

    public boolean setBookingStatusByBookingId(String id, String status) {
        String sql = "UPDATE booking SET status = ? WHERE id = ?";

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, status);
            statement.setString(2, id);
            System.out.println("Booking status: " + status);
            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error updating new booking: " + e.getMessage(), e);
        }
    }

    public Customer getCustomerByBooking(String bookingId) {
        String sql = "SELECT a.uid, a.first_name, a.last_name, a.email, a.contact_number, a.nic, " +
                "a.driver_license, a.address, a.user_type, a.status, a.employment_type, a.updated_at, a.created_at " +
                "FROM account a JOIN booking b ON a.uid = b.user_id " +
                "WHERE b.id = ?;";

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, bookingId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Customer customer = new Customer();
                    customer.setId(resultSet.getString("uid"));
                    customer.setFirstName(resultSet.getString("first_name"));
                    customer.setLastName(resultSet.getString("last_name"));
                    customer.setEmail(resultSet.getString("email"));
                    customer.setContactNumber(resultSet.getString("contact_number"));
                    customer.setNic(resultSet.getString("nic"));
                    customer.setDriverLicense(resultSet.getString("driver_license"));
                    customer.setAddress(resultSet.getString("address"));
                    customer.setUserType(resultSet.getString("user_type"));
                    customer.setStatus(resultSet.getString("status"));
                    customer.setEmploymentType(resultSet.getString("employment_type"));
                    customer.setUpdatedAt(resultSet.getString("updated_at"));
                    customer.setCreatedAt(resultSet.getString("created_at"));
                    return customer;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error getting customer by booking id: " + e.getMessage(), e);
        }
        return null;
    }

    public Driver getDriverByBooking(String bookingId) {
        String sql = "SELECT a.uid, a.first_name, a.last_name, a.email, a.contact_number, a.nic, " +
                "a.driver_license, a.address, a.user_type, a.status, a.employment_type, a.updated_at, a.created_at " +
                "FROM account a JOIN vehicle v ON a.uid = v.driver_id JOIN booking b ON v.id = b.car_id " +
                "WHERE b.id = ?;";

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, bookingId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Driver driver = new Driver();
                    driver.setId(resultSet.getString("uid"));
                    driver.setFirstName(resultSet.getString("first_name"));
                    driver.setLastName(resultSet.getString("last_name"));
                    driver.setEmail(resultSet.getString("email"));
                    driver.setContactNumber(resultSet.getString("contact_number"));
                    driver.setNic(resultSet.getString("nic"));
                    driver.setDriverLicense(resultSet.getString("driver_license"));
                    driver.setAddress(resultSet.getString("address"));
                    driver.setUserType(resultSet.getString("user_type"));
                    driver.setStatus(resultSet.getString("status"));
                    driver.setEmploymentType(resultSet.getString("employment_type"));
                    driver.setUpdatedAt(resultSet.getString("updated_at"));
                    driver.setCreatedAt(resultSet.getString("created_at"));
                    return driver;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error getting customer by booking id: " + e.getMessage(), e);
        }
        return null;
    }

    public List<Booking> getBookingsByCustomerId(String customerId, String status) {
        String sql = "SELECT b.id AS booking_id, c.uid AS customer_id, c.first_name AS customer_first_name, " +
                "c.last_name AS customer_last_name, u.uid AS user_id, u.first_name AS user_first_name, " +
                "u.last_name AS user_last_name, v.id AS vehicle_id, v.plate_no, t.id AS transaction_id, " +
                "t.amount, t.payment_method, b.pickup_location, b.destination, b.distance,b.pickup_time,b.destination_time,b.status, b.booked_at FROM booking b " +
                "JOIN account c ON b.customer_id = c.uid JOIN account u ON b.user_id = u.uid JOIN vehicle v " +
                "ON b.car_id = v.id LEFT JOIN `transaction` t ON b.transaction_id = t.id " +
                "WHERE c.uid = ? AND b.status = ?";
        List<Booking> bookings = null;

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, customerId);
            statement.setString(1, status);

            bookings = getBookingsFromStatement(statement);

        } catch (SQLException e) {
            throw new RuntimeException("Error getting bookings by customer id: " + e.getMessage(), e);
        }

        return bookings;
    }

    public List<Booking> getBookingsByCustomerId(String customerId) {
        String sql = "SELECT b.id AS booking_id, c.uid AS customer_id, c.first_name AS customer_first_name, " +
                "c.last_name AS customer_last_name, u.uid AS user_id, u.first_name AS user_first_name, " +
                "u.last_name AS user_last_name, v.id AS vehicle_id, v.plate_no, t.id AS transaction_id, " +
                "t.amount, t.payment_method, b.pickup_location, b.destination, b.distance,b.pickup_time,b.destination_time,b.status, b.booked_at FROM booking b " +
                "JOIN account c ON b.customer_id = c.uid JOIN account u ON b.user_id = u.uid JOIN vehicle v " +
                "ON b.car_id = v.id LEFT JOIN `transaction` t ON b.transaction_id = t.id " +
                "WHERE c.uid = ?";
        List<Booking> bookings = null;

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, customerId);

            bookings = getBookingsFromStatement(statement);

        } catch (SQLException e) {
            throw new RuntimeException("Error getting bookings by customer id: " + e.getMessage(), e);
        }

        return bookings;
    }

    public List<Booking> getBookingsByDriverId(String id) {
        String sql = "SELECT b.id AS booking_id, c.uid AS customer_id, c.first_name AS customer_first_name, " +
                "c.last_name AS customer_last_name, u.uid AS user_id, u.first_name AS user_first_name, " +
                "u.last_name AS user_last_name, v.id AS vehicle_id, v.plate_no, t.id AS transaction_id, " +
                "t.amount, t.payment_method, b.pickup_location, b.destination, b.distance,b.pickup_time,b.destination_time,b.status, b.booked_at FROM booking b " +
                "JOIN account c ON b.customer_id = c.uid JOIN account u ON b.user_id = u.uid JOIN vehicle v " +
                "ON b.car_id = v.id LEFT JOIN `transaction` t ON b.transaction_id = t.id " +
                "WHERE v.driver_id = ?";
        List<Booking> bookings = null;

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, id);

            bookings = getBookingsFromStatement(statement);

        } catch (SQLException e) {
            throw new RuntimeException("Error getting bookings by driver id: " + e.getMessage(), e);
        }

        return bookings;
    }

    public List<Booking> getPortionOfBookings(String limit, String offset) {
        String sql = "SELECT b.id AS booking_id, c.uid AS customer_id, c.first_name AS customer_first_name, " +
                "c.last_name AS customer_last_name, u.uid AS user_id, u.first_name AS user_first_name, " +
                "u.last_name AS user_last_name, v.id AS vehicle_id, v.plate_no, t.id AS transaction_id, " +
                "t.amount, t.payment_method, b.pickup_location, b.destination, b.distance,b.pickup_time,b.destination_time,b.status, b.booked_at FROM booking b " +
                "JOIN account c ON b.customer_id = c.uid JOIN account u ON b.user_id = u.uid JOIN vehicle v " +
                "ON b.car_id = v.id LEFT JOIN `transaction` t ON b.transaction_id = t.id LIMIT ? OFFSET ?;";
        List<Booking> bookings = null;

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setInt(1, Integer.parseInt(limit));
            statement.setInt(2, Integer.parseInt(offset));

            bookings = getBookingsFromStatement(statement);

        } catch (SQLException e) {
            throw new RuntimeException("Error getting portion of bookings: " + e.getMessage(), e);
        }

        return bookings;
    }

    public List<Booking> getPortionOfBookingsWithStatus(String limit, String offset, String status) {
        String sql = "SELECT b.id AS booking_id, c.uid AS customer_id, c.first_name AS customer_first_name, " +
                "c.last_name AS customer_last_name, u.uid AS user_id, u.first_name AS user_first_name, " +
                "u.last_name AS user_last_name, v.id AS vehicle_id, v.plate_no, t.id AS transaction_id, " +
                "t.amount, t.payment_method, b.pickup_location, b.destination, b.distance, b.pickup_time,b.destination_time,b.status, b.booked_at FROM booking b " +
                "JOIN account c ON b.customer_id = c.uid JOIN account u ON b.user_id = u.uid JOIN vehicle v " +
                "ON b.car_id = v.id LEFT JOIN `transaction` t ON b.transaction_id = t.id WHERE b.status = ? LIMIT ? OFFSET ?;";
        List<Booking> bookings = null;

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, status);
            statement.setInt(2, Integer.parseInt(limit));
            statement.setInt(3, Integer.parseInt(offset));

            bookings = getBookingsFromStatement(statement);

        } catch (SQLException e) {
            throw new RuntimeException("Error getting portion of bookings: " + e.getMessage(), e);
        }
        return bookings;
    }

    public List<Booking> getBookingsBySearch(String keyword){
        String sql = "SELECT b.id AS booking_id, c.uid AS customer_id, c.first_name AS customer_first_name, " +
                "c.last_name AS customer_last_name, u.uid AS user_id, u.first_name AS user_first_name, " +
                "u.last_name AS user_last_name, v.id AS vehicle_id, v.plate_no, t.id AS transaction_id, " +
                "t.amount, t.payment_method, b.pickup_location, b.destination, b.distance, b.pickup_time, b.destination_time, " +
                "b.status, b.booked_at FROM booking b " +
                "JOIN account c ON b.customer_id = c.uid " +
                "JOIN account u ON b.user_id = u.uid " +
                "JOIN vehicle v ON b.car_id = v.id " +
                "LEFT JOIN `transaction` t ON b.transaction_id = t.id " +
                "WHERE v.plate_no LIKE ? OR t.id LIKE ?;";
        List<Booking> bookings = null;

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");

            bookings = getBookingsFromStatement(statement);

        } catch (SQLException e) {
            throw new RuntimeException("Error getting searching bookings: " + e.getMessage(), e);
        }
        return bookings;
    }

    public List<Booking> getBookingsBySearchWithStatus(String keyword, String status){

        System.out.println("Keyword: " + keyword + " Status: " + status);
        String sql = "SELECT b.id AS booking_id, c.uid AS customer_id, c.first_name AS customer_first_name, " +
                "c.last_name AS customer_last_name, u.uid AS user_id, u.first_name AS user_first_name, " +
                "u.last_name AS user_last_name, v.id AS vehicle_id, v.plate_no, t.id AS transaction_id, " +
                "t.amount, t.payment_method, b.pickup_location, b.destination, b.distance, b.pickup_time, b.destination_time, " +
                "b.status, b.booked_at FROM booking b " +
                "JOIN account c ON b.customer_id = c.uid " +
                "JOIN account u ON b.user_id = u.uid " +
                "JOIN vehicle v ON b.car_id = v.id " +
                "LEFT JOIN `transaction` t ON b.transaction_id = t.id " +
                "WHERE b.status = ? AND (v.plate_no LIKE ? OR t.id LIKE ?);";
        List<Booking> bookings = null;

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, status);
            statement.setString(2, "%" + keyword + "%");
            statement.setString(3, "%" + keyword + "%");

            bookings = getBookingsFromStatement(statement);

        } catch (SQLException e) {
            throw new RuntimeException("Error getting searching bookings: " + e.getMessage(), e);
        }
        return bookings;
    }

    public List<Booking> getBookingsByEmail(String email){
        String sql = "SELECT b.id AS booking_id, c.uid AS customer_id, c.first_name AS customer_first_name, " +
                "c.last_name AS customer_last_name, u.uid AS user_id, u.first_name AS user_first_name, " +
                "u.last_name AS user_last_name, v.id AS vehicle_id, v.plate_no, t.id AS transaction_id, " +
                "t.amount, t.payment_method, b.pickup_location, b.destination, b.distance, b.pickup_time, b.destination_time, " +
                "b.status, b.booked_at FROM booking b " +
                "JOIN account c ON b.customer_id = c.uid " +
                "JOIN account u ON b.user_id = u.uid " +
                "JOIN vehicle v ON b.car_id = v.id " +
                "LEFT JOIN `transaction` t ON b.transaction_id = t.id " +
                "WHERE c.email = ?;";
        List<Booking> bookings = null;

        try (Connection con = DatabaseConnection.connection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, email);

            bookings = getBookingsFromStatement(statement);

        } catch (SQLException e) {
            throw new RuntimeException("Error getting searching bookings: " + e.getMessage(), e);
        }
        return bookings;
    }

    private List<Booking> getBookingsFromStatement(PreparedStatement statement) throws SQLException {

        List<Booking> bookings = new ArrayList<>();
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                User customer = new User();
                customer.setId(resultSet.getString("customer_id"));
                customer.setFirstName(resultSet.getString("customer_first_name"));
                customer.setLastName(resultSet.getString("customer_last_name"));

                User user = new User();
                user.setId(resultSet.getString("user_id"));
                user.setFirstName(resultSet.getString("user_first_name"));
                user.setLastName(resultSet.getString("user_last_name"));

                Vehicle vehicle = new Vehicle(
                        resultSet.getString("vehicle_id"),
                        resultSet.getString("plate_no")
                );

                Transaction transaction = new Transaction(
                        resultSet.getString("transaction_id"),
                        resultSet.getFloat("amount"),
                        resultSet.getString("payment_method")
                );


                Booking booking = new Booking();
                booking.setBookingId(resultSet.getString("booking_id"));
                booking.setCustomer(customer);
                booking.setBookingUser(user);
                booking.setVehicle(vehicle);
                booking.setTransaction(transaction);
                booking.setPickupLocation(resultSet.getString("pickup_location"));
                booking.setDestination(resultSet.getString("destination"));
                booking.setPickupTime(resultSet.getString("pickup_time"));
                booking.setDestinationTime(resultSet.getString("destination_time"));
                booking.setDistance(resultSet.getDouble("distance"));
                booking.setStatus(resultSet.getString("status"));
                booking.setBookedAt(resultSet.getString("booked_at"));
                bookings.add(booking);
            }
        }
        return bookings;
    }
}
