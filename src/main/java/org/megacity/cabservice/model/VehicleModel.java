package org.megacity.cabservice.model;

public class VehicleModel {

    private int modelId;
    private String modelName;
    private String vehicleType;
    private String manufacturer;
    private String year;
    private String fuelType;
    private String transmission;
    private String status;
    private String addedAt;

    public VehicleModel(int modelId, String modelName, String vehicleType, String manufacturer, String year, String fuelType, String transmission, String status, String addedAt) {
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

    public VehicleModel(int modelId, String modelName) {
        this.modelId = modelId;
        this.modelName = modelName;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(String addedAt) {
        this.addedAt = addedAt;
    }

}
