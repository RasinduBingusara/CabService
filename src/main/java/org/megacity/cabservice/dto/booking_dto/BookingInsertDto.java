package org.megacity.cabservice.dto.booking_dto;

import org.megacity.cabservice.model.Transaction;
import org.megacity.cabservice.model.User;
import org.megacity.cabservice.model.Vehicle;

public class BookingInsertDto {

    private String customerId;
    private String userId;
    private String vehicleId;
    private String pickupLocation;
    private String destination;
    private String pickupTime;
    private double distance;
    private String status;
    private String transactionId;

    public BookingInsertDto(String customerId, String userId, String vehicleId, String pickupLocation, String destination, String pickupTime, double distance, String status) {
        this.customerId = customerId;
        this.userId = userId;
        this.vehicleId = vehicleId;
        this.pickupLocation = pickupLocation;
        this.destination = destination;
        this.pickupTime = pickupTime;
        this.distance = distance;
        this.status = status;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getUserId() {
        return userId;
    }

    public String getVehicleId() {
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

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
