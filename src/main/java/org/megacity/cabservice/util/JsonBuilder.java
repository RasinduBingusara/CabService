package org.megacity.cabservice.util;

import org.megacity.cabservice.dto.driver_dto.DriverDetailDTO;
import org.megacity.cabservice.dto.model_dto.ModelDetailsDto;
import org.megacity.cabservice.dto.vehicle_dto.VehicleDetailsDto;

import java.util.List;

public class JsonBuilder{

    private static JsonBuilder INSTANCE;

    public static JsonBuilder getInstance(){
        if(INSTANCE == null){
            INSTANCE = new JsonBuilder();
        }
        return INSTANCE;
    }


    public String driverDetailDtoToJson(List<DriverDetailDTO> list){
        StringBuilder jsonList = new StringBuilder("[");
        for (int i = 0; i < list.size(); i++) {
            jsonList.append(list.get(i).toJson());
            if (i < list.size() - 1) jsonList.append(",");
        }
        jsonList.append("]");
        return jsonList.toString();
    }

    public String modelDetailDtoToJson(List<ModelDetailsDto> list){
        StringBuilder jsonList = new StringBuilder("[");
        for (int i = 0; i < list.size(); i++) {
            jsonList.append(list.get(i).toJson());
            if (i < list.size() - 1) jsonList.append(",");
        }
        jsonList.append("]");
        return jsonList.toString();
    }

    public String vehicleDetailDtoToJson(List<VehicleDetailsDto> list){
        StringBuilder jsonList = new StringBuilder("[");
        for (int i = 0; i < list.size(); i++) {
            jsonList.append(list.get(i).toJson());
            if (i < list.size() - 1) jsonList.append(",");
        }
        jsonList.append("]");
        return jsonList.toString();
    }
}
