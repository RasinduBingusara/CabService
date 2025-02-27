package org.megacity.cabservice.dto.model_dto;

public class ModelInsertDto {

    private String modelName;
    private String vehicleType;
    private String manufacturer;
    private String year;
    private String fuelType;
    private String transmission;
    private String status;

    public ModelInsertDto(String modelName, String vehicleType, String manufacturer, String year, String fuelType, String transmission, String status) {
        this.modelName = modelName;
        this.vehicleType = vehicleType;
        this.manufacturer = manufacturer;
        this.year = year;
        this.fuelType = fuelType;
        this.transmission = transmission;
        this.status = status;
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

}
