package org.megacity.cabservice.model;

public class Vehicle {

    private String id;
    private String modelId;
    private String color;
    private String plate_no;
    private int seat_count;
    private boolean availability;
    private float price_per_km;
    private float liters_per_km;
    private String driverId;
    private String ownerId;
    private String status;
    private String added_At;

    public Vehicle(String id, String modelId, String color, String plate_no, int seat_count, boolean availability, float price_per_km, float liters_per_km, String driverId, String ownerId, String status, String added_At) {
        this.id = id;
        this.modelId = modelId;
        this.color = color;
        this.plate_no = plate_no;
        this.seat_count = seat_count;
        this.availability = availability;
        this.price_per_km = price_per_km;
        this.liters_per_km = liters_per_km;
        this.driverId = driverId;
        this.ownerId = ownerId;
        this.status = status;
        this.added_At = added_At;
    }

    public String getId() {
        return id;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPlate_no() {
        return plate_no;
    }

    public void setPlate_no(String plate_no) {
        this.plate_no = plate_no;
    }

    public int getSeat_count() {
        return seat_count;
    }

    public void setSeat_count(int seat_count) {
        this.seat_count = seat_count;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public float getPrice_per_km() {
        return price_per_km;
    }

    public void setPrice_per_km(float price_per_km) {
        this.price_per_km = price_per_km;
    }

    public float getLiters_per_km() {
        return liters_per_km;
    }

    public void setLiters_per_km(float liters_per_km) {
        this.liters_per_km = liters_per_km;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAdded_At() {
        return added_At;
    }

    public void setAdded_At(String added_At) {
        this.added_At = added_At;
    }
}
