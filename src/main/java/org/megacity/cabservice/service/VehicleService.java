package org.megacity.cabservice.service;


import org.megacity.cabservice.dto.vehicle_dto.VehicleDetailsDto;
import org.megacity.cabservice.dto.vehicle_dto.VehicleInsertDto;
import org.megacity.cabservice.model.ResponseWrapper;
import org.megacity.cabservice.model.Vehicle;
import org.megacity.cabservice.repository.VehicleRepo;
import org.megacity.cabservice.util.JsonBuilder;

import java.util.List;

public class VehicleService {

    private VehicleRepo vehicleRepo = new VehicleRepo();

    public ResponseWrapper<VehicleInsertDto> addVehicle(VehicleInsertDto vehicle) {

        if(vehicleRepo.isPlateNoExist(vehicle.getPlate_no())){
            vehicle.setPlate_no("");
            return new ResponseWrapper<>("Vehicle Plate no already exist",vehicle);
        }
        if(vehicleRepo.addVehicle(vehicle)){
            return new ResponseWrapper<>("Vehicle added successfully",null);
        }
        else{
            return new ResponseWrapper<>("Vehicle added failed",vehicle);
        }
    }

    public String getPortionOfVehicles(String limit,String offset, String status){
        List<VehicleDetailsDto> vehicles = status.isEmpty()? vehicleRepo.getPortionOfVehicles(limit, offset):
                vehicleRepo.getPortionOfVehiclesWithStatus(limit, offset,status);
        return JsonBuilder.getInstance().vehicleDetailDtoToJson(vehicles);
    }

    public String getAllVehiclesInJson() {
        List<VehicleDetailsDto> vehicles = vehicleRepo.getAllVehicles();
        return JsonBuilder.getInstance().vehicleDetailDtoToJson(vehicles);
    }

    public String getVehiclesBySearch(String keyword, String status){
        System.out.println("Keyword: " + keyword);
        List<VehicleDetailsDto> vehicles = status.isEmpty()? vehicleRepo.getVehiclesBySearch(keyword):
                vehicleRepo.getVehiclesBySearchWithStatus(keyword, status);
        System.out.println("Convert to Json");
        return JsonBuilder.getInstance().vehicleDetailDtoToJson(vehicles);
    }


}
