package org.megacity.cabservice.dto.model_dto;

public class ModelDetailsDto {

    private int modelId;
    private String modelName;
    private String vehicleType;
    private String manufacturer;
    private String year;
    private String fuelType;
    private String transmission;
    private String status;
    private String addedAt;

    public ModelDetailsDto(int modelId, String modelName, String vehicleType, String manufacturer, String year, String fuelType, String transmission, String status, String addedAt) {
        this.modelId = modelId;
        this.modelName = modelName;
        this.vehicleType = vehicleType;
        this.manufacturer = manufacturer;
        this.year = year;
        this.fuelType = fuelType;
        this.transmission = transmission;
        this.status = status;
        this.addedAt = addedAt;
    }

    public int getModelId() {
        return modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getYear() {
        return year;
    }

    public String getFuelType() {
        return fuelType;
    }

    public String getTransmission() {
        return transmission;
    }

    public String getStatus() {
        return status;
    }

    public String getAddedAt() {
        return addedAt;
    }

    public String toJson() {

        StringBuilder json = new StringBuilder();
        json.append("{")
                .append("\"model_id\":\"").append(escapeJson(String.valueOf(modelId))).append("\",")
                .append("\"model_name\":\"").append(escapeJson(modelName)).append("\",")
                .append("\"vehicle_type\":\"").append(escapeJson(vehicleType)).append("\",")
                .append("\"manufacturer\":\"").append(escapeJson(manufacturer)).append("\",")
                .append("\"year\":\"").append(escapeJson(year)).append("\",")
                .append("\"fuel_type\":\"").append(escapeJson(fuelType)).append("\",")
                .append("\"transmission\":\"").append(escapeJson(transmission)).append("\",")
                .append("\"status\":\"").append(escapeJson(status)).append("\",")
                .append("\"added_at\":\"").append(escapeJson(addedAt)).append("\"")
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
