package org.megacity.cabservice.service;

import org.junit.Test;
import org.megacity.cabservice.dto.vehicle_dto.VehicleDetailsDto;
import org.megacity.cabservice.dto.vehicle_dto.VehicleInsertDto;
import org.megacity.cabservice.model.Wrappers.ResponseWrapper;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleServiceTest {

    private VehicleService vehicleService = new VehicleService();

    @Test
    public void testAddVehicle_Success() {
        VehicleInsertDto vehicle = new VehicleInsertDto(
                1,
                "Blue",
                "GFT-5685",
                4,
                true,
                50,
                0.1f,
                1,
                1,
                "Active"

        );
        ResponseWrapper<VehicleInsertDto> response = vehicleService.addVehicle(vehicle);
        assertEquals("Vehicle added successfully", response.getMessage());
    }

    @Test
    public void testAddVehicle_Fail_ExistingPlateNo() {
        VehicleInsertDto vehicle = new VehicleInsertDto(
                1,
                "Blue",
                "XYZ-5678",
                4,
                true,
                50,
                0.1f,
                1,
                1,
                "Active"

        );
        ResponseWrapper<VehicleInsertDto> response = vehicleService.addVehicle(vehicle);
        assertEquals("Vehicle Plate no already exist", response.getMessage());
    }

    @Test
    public void testGetAllVehiclesInJson() {
        String jsonResult = vehicleService.getAllVehiclesInJson();
        assertNotNull(jsonResult);
        assertTrue(jsonResult.startsWith("[") && jsonResult.endsWith("]"));
    }

    @Test
    public void testGetVehiclesByStatusInJson() {
        String jsonResult = vehicleService.getVehiclesByStatusInJson("Active");
        assertNotNull(jsonResult);
        assertTrue(jsonResult.contains("\"status\":\"Active\""));
    }

    @Test
    public void testGetVehiclesBySearch() {
        String jsonResult = vehicleService.getVehiclesBySearch("XYZ-5678", "");
        assertNotNull(jsonResult);
    }

    @Test
    public void testUpdateVehicle_Success() {
        VehicleInsertDto vehicle = new VehicleInsertDto(
                1,
                "Blue",
                "GFT-5688",
                4,
                true,
                50,
                0.1f,
                1,
                1,
                "Active"

        );
        ResponseWrapper<VehicleInsertDto> response = vehicleService.updateVehicle(vehicle, 19);
        assertEquals("Vehicle renew successfully", response.getMessage());
    }

    @Test
    public void testUpdateVehicle_Fail() {
        VehicleInsertDto vehicle = new VehicleInsertDto(
                1,
                "Blue",
                "GFT-5688",
                4,
                true,
                50,
                0.1f,
                1,
                1,
                "Active"

        );
        ResponseWrapper<VehicleInsertDto> response = vehicleService.updateVehicle(vehicle, -1);
        assertEquals("Vehicle renew failed", response.getMessage());
    }

    @Test
    public void testUpdateStatus_Success() {
        boolean result = vehicleService.updateStatus(3, "Inactive");
        assertTrue(result);
    }

    @Test
    public void testGetVehicleByDriverId() {
        VehicleDetailsDto vehicle = vehicleService.getVehcileByDriverId(20);
        assertNotNull(vehicle);
        assertEquals(20, vehicle.getDriver().getId());
    }

}