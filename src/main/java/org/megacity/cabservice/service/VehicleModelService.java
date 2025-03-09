package org.megacity.cabservice.service;

import org.megacity.cabservice.dto.model_dto.ModelDetailsDto;
import org.megacity.cabservice.dto.model_dto.ModelInsertDto;
import org.megacity.cabservice.model.Wrappers.ResponseWrapper;
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

    public List<ModelDetailsDto> getAllVehicleModels() {
        return vehicleModelRepo.getAllVehicleModels();
    }

    public String getAllVehicleModelsInJason() {
        List<ModelDetailsDto> allVehicleModels = vehicleModelRepo.getAllVehicleModels();
        return JsonBuilder.getInstance().modelDetailDtoToJson(allVehicleModels);
    }

    public boolean updateStatus(int id, String status) {
        return vehicleModelRepo.updateStatus(id, status);
    }
    public VehicleModel getVehicleModelById(int id) {
        return null;
    }
}
