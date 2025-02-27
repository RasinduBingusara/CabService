package org.megacity.cabservice.util;

import org.megacity.cabservice.dto.driver_dto.DriverDetailDTO;
import org.megacity.cabservice.dto.model_dto.ModelDetailsDto;

import java.util.List;

public class JsonBuilder{

    public static String driverDetailDtoToJson(List<DriverDetailDTO> list){
        StringBuilder jsonList = new StringBuilder("[");
        for (int i = 0; i < list.size(); i++) {
            jsonList.append(list.get(i).toJson());
            if (i < list.size() - 1) jsonList.append(",");
        }
        jsonList.append("]");
        return jsonList.toString();
    }

    public static String modelDetailDtoToJson(List<ModelDetailsDto> list){
        StringBuilder jsonList = new StringBuilder("[");
        for (int i = 0; i < list.size(); i++) {
            jsonList.append(list.get(i).toJson());
            if (i < list.size() - 1) jsonList.append(",");
        }
        jsonList.append("]");
        return jsonList.toString();
    }
}
