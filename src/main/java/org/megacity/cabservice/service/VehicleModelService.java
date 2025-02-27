package org.megacity.cabservice.service;

import org.megacity.cabservice.dto.model_dto.ModelDetailsDto;
import org.megacity.cabservice.dto.model_dto.ModelInsertDto;
import org.megacity.cabservice.model.ResponseWrapper;
import org.megacity.cabservice.model.VehicleModel;
import org.megacity.cabservice.repository.VehicleModelRepo;
import org.megacity.cabservice.util.JsonBuilder;

import java.util.List;

public class VehicleModelService {

    private VehicleModelRepo vehicleModelRepo = new VehicleModelRepo();
    public ResponseWrapper<ModelInsertDto> addVehicleModel(ModelInsertDto vehicleModel) {

        if(vehicleModelRepo.isModelNameExist(vehicleModel.getModelName())) {
            return new ResponseWrapper<>("Vehicle Model Name Already Exists", vehicleModel);
        }
        else{
            if(vehicleModelRepo.addVehicleType(vehicleModel)){
                return new ResponseWrapper<>("Vehicle Model Added Successfully", null);
            }
            else{
                return new ResponseWrapper<>("Vehicle Model Not Added Successfully", vehicleModel);
            }
        }

    }
    public boolean removeVehicleModel(VehicleModel vehicleModel) {
        return false;
    }
    public boolean updateVehicleModel(VehicleModel vehicleModel) {
        return false;
    }
    public List<ModelDetailsDto> getAllVehicleModels() {
        return vehicleModelRepo.getAllVehicleModels();
    }

    public String getAllVehicleModelsInJason() {
        List<ModelDetailsDto> allVehicleModels = vehicleModelRepo.getAllVehicleModels();
        return JsonBuilder.modelDetailDtoToJson(allVehicleModels);
    }
    public VehicleModel getVehicleModelById(int id) {
        return null;
    }
}
