package org.megacity.cabservice.util;

import org.megacity.cabservice.dto.driver_dto.DriverDetailDTO;

import java.util.List;

public class JsonBuilder{

    public static String buildJsonList(List<DriverDetailDTO> list){
        StringBuilder jsonList = new StringBuilder("[");
        for (int i = 0; i < list.size(); i++) {
            jsonList.append(list.get(i).toJson());
            if (i < list.size() - 1) jsonList.append(",");
        }
        jsonList.append("]");
        return jsonList.toString();
    }
}
