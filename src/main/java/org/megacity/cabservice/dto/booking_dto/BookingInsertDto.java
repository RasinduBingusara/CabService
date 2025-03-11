package org.megacity.cabservice.dto.booking_dto;

import org.megacity.cabservice.model.Transaction;
import org.megacity.cabservice.model.User;
import org.megacity.cabservice.model.Vehicle;

public class BookingInsertDto {

    private int customerId;
    private int userId;
    private int vehicleId;
    private String pickupLocation;
    private String destination;
    private String pickupTime;
    private double distance;
    private String status;
    private int transactionId;

    public BookingInsertDto(int customerId, int userId, int vehicleId, String pickupLocation, String destination, String pickupTime, double distance, String status) {
        this.customerId = customerId;
        this.userId = userId;
        this.vehicleId = vehicleId;
        this.pickupLocation = pickupLocation;
        this.destination = destination;
        this.pickupTime = pickupTime;
        this.distance = distance;
        this.status = status;
    }

    public BookingInsertDto(int customerId, int userId, int vehicleId, String pickupLocation, String destination, double distance, String status) {
        this.customerId = customerId;
        this.userId = userId;
        this.vehicleId = vehicleId;
        this.pickupLocation = pickupLocation;
        this.destination = destination;
        this.distance = distance;
        this.status = status;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getUserId() {
        return userId;
    }

    public int getVehicleId() {
        return vehicleId;
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

    public double getDistance() {
        return distance;
    }

    public String getStatus() {
        return status;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }
}
