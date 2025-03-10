package org.megacity.cabservice.dto.vehicle_dto;

public class VehicleInsertDto {

    private int modelId;
    private String color;
    private String plate_no;
    private int seat_count;
    private boolean availability;
    private float price_per_km;
    private float liters_per_km;
    private int driverId;
    private int ownerId;
    private String status;

    public VehicleInsertDto(int modelId, String color, String plate_no, int seat_count, boolean availability, float price_per_km, float liters_per_km, int driverId, int ownerId, String status) {
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
    }

    public int getModelId() {
        return modelId;
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

    public int getDriverId() {
        return driverId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public String getStatus() {
        return status;
    }

    public void setPlate_no(String plate_no) {
        this.plate_no = plate_no;
    }
}
