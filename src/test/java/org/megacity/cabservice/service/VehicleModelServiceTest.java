package org.megacity.cabservice.service;

import org.junit.Test;
import org.megacity.cabservice.dto.model_dto.ModelDetailsDto;
import org.megacity.cabservice.dto.model_dto.ModelInsertDto;
import org.megacity.cabservice.model.Wrappers.ResponseWrapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleModelServiceTest {

    private VehicleModelService vehicleModelService = new VehicleModelService();

    ModelInsertDto existingModel = new ModelInsertDto(
            "Corolla",
            "Sedan",
            "Toyota",
            "2022",
            "Petrol",
            "Automatic",
            "Active"
    );

    @Test
    public void testAddExistingVehicleModel() {

        ResponseWrapper<ModelInsertDto> response = vehicleModelService.addVehicleModel(existingModel);
        assertEquals("Vehicle Model Name Already Exists", response.getMessage());
        assertEquals(existingModel, response.getData());
    }

    @Test
    public void testAddNewVehicleModel_Success() {
        ModelInsertDto model = new ModelInsertDto(
                "new model",
                "Sedan",
                "Toyota",
                "2022",
                "Petrol",
                "Automatic",
                "Active"
        );

        ResponseWrapper<ModelInsertDto> response = vehicleModelService.addVehicleModel(model);
        assertEquals("Vehicle Model Added Successfully", response.getMessage());
        assertNull(response.getData());
    }

    @Test
    public void testGetAllVehicleModels_Empty() {
        // Simulating no records in the repository
        List<ModelDetailsDto> models = vehicleModelService.getAllVehicleModels();
        assertFalse(models.isEmpty());
    }


    @Test
    public void testGetAllVehicleModelsAsJson() {
        List<ModelDetailsDto> models = new ArrayList<>();

        assertNotNull(vehicleModelService.getAllVehicleModelsInJason());
    }

    @Test
    public void testUpdateVehicleModelStatus_Success() {
        int modelId = 1;
        String newStatus = "Active";

        assertTrue(vehicleModelService.updateStatus(modelId, newStatus));
    }

    @Test
    public void testUpdateVehicleModelStatus_Failure() {
        int modelId = 99;
        String newStatus = "Inactive";

        assertFalse(vehicleModelService.updateStatus(modelId, newStatus));
    }
}