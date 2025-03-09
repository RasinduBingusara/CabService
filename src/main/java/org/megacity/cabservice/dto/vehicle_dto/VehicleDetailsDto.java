package org.megacity.cabservice.dto.vehicle_dto;

import org.megacity.cabservice.model.User;
import org.megacity.cabservice.model.VehicleModel;

public class VehicleDetailsDto {

    private int id;
    private VehicleModel model;
    private String color;
    private String plate_no;
    private int seat_count;
    private boolean availability;
    private float price_per_km;
    private float liters_per_km;
    private User driver;
    private User owner;
    private String status;
    private String updatedAt;
    private String added_At;


    public VehicleDetailsDto(int id, VehicleModel model, String color, String plate_no, int seat_count, boolean availability, float price_per_km, float liters_per_km, User driver, User owner, String status, String updatedAt, String added_At) {
        this.id = id;
        this.model = model;
        this.color = color;
        this.plate_no = plate_no;
        this.seat_count = seat_count;
        this.availability = availability;
        this.price_per_km = price_per_km;
        this.liters_per_km = liters_per_km;
        this.driver = driver;
        this.owner = owner;
        this.status = status;
        this.updatedAt = updatedAt;
        this.added_At = added_At;
    }

    public int getId() {
        return id;
    }

    public VehicleModel getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public String getPlate_no() {
        return plate_no;
    }

    public int getSeat_count() {
        return seat_count;
    }

    public boolean isAvailability() {
        return availability;
    }

    public float getPrice_per_km() {
        return price_per_km;
    }

    public float getLiters_per_km() {
        return liters_per_km;
    }

    public User getDriver() {
        return driver;
    }

    public User getOwner() {
        return owner;
    }

    public String getStatus() {
        return status;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getAdded_At() {
        return added_At;
    }

    public String toJson() {

        StringBuilder json = new StringBuilder();

        json.append("{")
                .append("\"vehicle_id\":\"").append(escapeJson(String.valueOf(id))).append("\",")
                .append("\"model\":{")
                .append("\"model_id\":\"").append(escapeJson(String.valueOf(model.getModelId()))).append("\",")
                .append("\"model_name\":\"").append(escapeJson(model.getModelName())).append("\"},")
                .append("\"color\":\"").append(escapeJson(color)).append("\",")
                .append("\"plate_no\":\"").append(escapeJson(plate_no)).append("\",")
                .append("\"seat_count\":\"").append(escapeJson(String.valueOf(seat_count))).append("\",")
                .append("\"availability\":\"").append(escapeJson(String.valueOf(availability))).append("\",")
                .append("\"price_per_km\":\"").append(escapeJson(String.valueOf(price_per_km))).append("\",")
                .append("\"liters_per_km\":\"").append(escapeJson(String.valueOf(liters_per_km))).append("\",")
                .append("\"driver\":{")
                .append("\"driver_id\":\"").append(escapeJson(String.valueOf(driver.getId()))).append("\",")
                .append("\"first_name\":\"").append(escapeJson(driver.getFirstName())).append("\",")
                .append("\"last_name\":\"").append(escapeJson(driver.getLastName())).append("\"},")
                .append("\"owner\":{")
                .append("\"owner_id\":\"").append(escapeJson(String.valueOf(owner.getId()))).append("\",")
                .append("\"first_name\":\"").append(escapeJson(owner.getFirstName())).append("\",")
                .append("\"last_name\":\"").append(escapeJson(owner.getLastName())).append("\"},")
                .append("\"status\":\"").append(escapeJson(status)).append("\",")
                .append("\"updated_at\":\"").append(escapeJson(updatedAt)).append("\",")
                .append("\"added_at\":\"").append(escapeJson(added_At)).append("\"")
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
