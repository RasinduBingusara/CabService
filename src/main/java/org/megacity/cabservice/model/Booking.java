package org.megacity.cabservice.model;

import org.megacity.cabservice.model.Users.Customer;
import org.megacity.cabservice.model.Users.Driver;

public class Booking {

    private String bookingId;
    private User customer;
    private User bookingUser;
    private Vehicle vehicle;
    private String pickupLocation;
    private String destination;
    private String pickupTime;
    private String destinationTime;
    private Transaction transaction;
    private double distance;
    private String status;
    private String bookedAt;

    public Booking() {
    }

    public Booking(String bookingId, User customer, User bookingUser, Vehicle vehicle, String pickupLocation, String destination, String pickupTime, String destinationTime, Transaction transaction, double distance, String status, String bookedAt) {
        this.bookingId = bookingId;
        this.customer = customer;
        this.bookingUser = bookingUser;
        this.vehicle = vehicle;
        this.pickupLocation = pickupLocation;
        this.destination = destination;
        this.pickupTime = pickupTime;
        this.destinationTime = destinationTime;
        this.transaction = transaction;
        this.distance = distance;
        this.status = status;
        this.bookedAt = bookedAt;
    }

    public String getBookingId() {
        return bookingId;
    }

    public User getCustomer() {
        return customer;
    }

    public User getBookingUser() {
        return bookingUser;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public String getDestination() {
        return destination;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public String getDestinationTime() {
        return destinationTime;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public double getDistance() {
        return distance;
    }

    public String getStatus() {
        return status;
    }

    public String getBookedAt() {
        return bookedAt;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public void setBookingUser(User bookingUser) {
        this.bookingUser = bookingUser;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

    public void setDestinationTime(String destinationTime) {
        this.destinationTime = destinationTime;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setBookedAt(String bookedAt) {
        this.bookedAt = bookedAt;
    }

    public String toJson() {

        StringBuilder json = new StringBuilder();

        json.append("{")
                .append("\"booking_id\":\"").append(escapeJson(bookingId)).append("\",")
                .append("\"customer\":{")
                .append("\"id\":\"").append(escapeJson(customer.getId())).append("\",")
                .append("\"first_name\":\"").append(escapeJson(customer.getFirstName())).append("\",")
                .append("\"last_name\":\"").append(escapeJson(customer.getLastName())).append("\"},")
                .append("\"booking_user\":{")
                .append("\"id\":\"").append(escapeJson(bookingUser.getId())).append("\",")
                .append("\"first_name\":\"").append(escapeJson(bookingUser.getFirstName())).append("\",")
                .append("\"last_name\":\"").append(escapeJson(bookingUser.getLastName())).append("\"},")
                .append("\"vehicle\":{")
                .append("\"id\":\"").append(escapeJson(vehicle.getId())).append("\",")
                .append("\"plate_no\":\"").append(escapeJson(vehicle.getPlate_no())).append("\"},") // Fixed key name
                .append("\"transaction\":{")
                .append("\"id\":\"").append(escapeJson(transaction.getId())).append("\",")
                .append("\"payment_method\":\"").append(escapeJson(transaction.getPaymentMethod())).append("\",")
                .append("\"amount\":\"").append(escapeJson(String.valueOf(transaction.getAmount()))).append("\"},")
                .append("\"location\":{")
                .append("\"pickup\":\"").append(escapeJson(pickupLocation)).append("\",")
                .append("\"destination\":\"").append(escapeJson(destination)).append("\"},")
                .append("\"time\":{")
                .append("\"pickup\":\"").append(escapeJson(pickupTime)).append("\",")
                .append("\"destination\":\"").append(escapeJson(destinationTime)).append("\"},")
                .append("\"distance\":\"").append(escapeJson(String.valueOf(distance))).append("\",")
                .append("\"status\":\"").append(escapeJson(status)).append("\",")
                .append("\"booked_at\":\"").append(escapeJson(bookedAt)).append("\"")
                .append("}");

        return json.toString();
    }

    private String escapeJson(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("\\", "\\\\")   // Escape backslashes
                .replace("\"", "\\\"")   // Escape double quotes
                .replace("\n", "\\n")    // Escape new lines
                .replace("\r", "\\r")    // Escape carriage returns
                .replace("\t", "\\t");   // Escape tabs
    }
}
