package org.megacity.cabservice.mapper;


import org.megacity.cabservice.dto.admin_dto.*;
import org.megacity.cabservice.model.User;

public class AdminMapper {

    private static AdminMapper INSTANCE;

    public static AdminMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AdminMapper();
        }
        return INSTANCE;
    }
    public User toEntity(AdminInsertDTO dto){
        User admin = new User();
        admin.setFirstName(dto.getFirstName());
        admin.setLastName(dto.getLastName());
        admin.setEmail(dto.getEmail());
        admin.setPassword(dto.getPassword());
        return admin;
    }
    public AdminInsertDTO toAdminInsertDto(User entity){
        AdminInsertDTO dto = new AdminInsertDTO(
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPassword()
        );
        return dto;
    }

    public User toEntity(AdminResponseDTO dto){
        User entity = new User();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setUserType(dto.getUserType());
        return entity;
    }
    public AdminResponseDTO toAdminResponseDto(User entity){
        AdminResponseDTO dto = new AdminResponseDTO(
                entity.getFirstName(), entity.getLastName(), entity.getEmail()
        );
        return dto;
    }

    public User toEntity(AdminEditDTO dto){
        User entity = new User();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        return entity;
    }
    public AdminEditDTO toAdminEditDto(User entity){
        AdminEditDTO dto = new AdminEditDTO(
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPassword()
        );
        return dto;
    }
}
