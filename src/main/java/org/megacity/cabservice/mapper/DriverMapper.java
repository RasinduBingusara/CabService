package org.megacity.cabservice.mapper;


import org.megacity.cabservice.dto.driver_dto.*;
import org.megacity.cabservice.model.User;

public class DriverMapper {

    private static DriverMapper INSTANCE;
    public static DriverMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DriverMapper();
        }
        return INSTANCE;
    }

    public User toEntity(DriverInsertDTO dto){
        User driver = new User();
        driver.setFirstName(dto.getFirstName());
        driver.setLastName(dto.getLastName());
        driver.setEmail(dto.getEmail());
        driver.setPassword(dto.getPassword());
        driver.setContactNumber(dto.getContactNumber());
        driver.setStatus(dto.getStatus());
        driver.setDriverLicense(dto.getDriverLicense());
        driver.setNic(dto.getNic());
        driver.setAddress(dto.getAddress());
        driver.setEmploymentType(dto.getEmploymentType());
        return driver;
    }

    public DriverInsertDTO toDriverInsertDto(User entity){
        DriverInsertDTO dto = new DriverInsertDTO(
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getContactNumber(),
                entity.getStatus(),
                entity.getDriverLicense(),
                entity.getNic(),
                entity.getAddress(),
                entity.getEmploymentType()
        );
        return dto;
    }

    public User toEntity(DriverEditDTO dto){
        User driver = new User();
        driver.setFirstName(dto.getFirstName());
        driver.setLastName(dto.getLastName());
        driver.setEmail(dto.getEmail());
        driver.setPassword(dto.getPassword());
        driver.setContactNumber(dto.getContactNumber());
        driver.setStatus(dto.getStatus());
        driver.setDriverLicense(dto.getDriverLicense());
        driver.setNic(dto.getNic());
        driver.setAddress(dto.getAddress());
        return driver;
    }

    public DriverEditDTO toDriverEditDto(User entity){
        DriverEditDTO dto = new DriverEditDTO(
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getContactNumber(),
                entity.getStatus(),
                entity.getDriverLicense(),
                entity.getNic(),
                entity.getAddress()
        );
        return dto;
    }

    public User toEntity(DriverDetailDTO dto){
        User driver = new User();
        driver.setId(dto.getId());
        driver.setFirstName(dto.getFirstName());
        driver.setLastName(dto.getLastName());
        driver.setEmail(dto.getEmail());
        driver.setContactNumber(dto.getContactNumber());
        driver.setUserType(dto.getUserType());
        driver.setStatus(dto.getStatus());
        driver.setDriverLicense(dto.getDriverLicense());
        driver.setNic(dto.getNic());
        driver.setAddress(dto.getAddress());
        driver.setEmploymentType(dto.getEmploymentType());
        driver.setUpdatedAt(dto.getUpdatedAt());
        driver.setCreatedAt(dto.getCreatedAt());
        return driver;
    }

    public DriverDetailDTO toDriverDetailDto(User entity){
        DriverDetailDTO dto = new DriverDetailDTO(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getContactNumber(),
                entity.getUserType(),
                entity.getStatus(),
                entity.getDriverLicense(),
                entity.getNic(),
                entity.getAddress(),
                entity.getEmploymentType(),
                entity.getUpdatedAt(),
                entity.getCreatedAt()
        );
        return dto;
    }

    public User toEntity(DriverResponseDTO dto){
        User driver = new User();
        driver.setFirstName(dto.getFirstName());
        driver.setLastName(dto.getLastName());
        driver.setEmail(dto.getEmail());
        driver.setContactNumber(dto.getContactNumber());
        driver.setStatus(dto.getStatus());
        driver.setDriverLicense(dto.getDriverLicense());
        driver.setNic(dto.getNic());
        driver.setAddress(dto.getAddress());
        driver.setUserType(dto.getUserType());
        return driver;
    }

    public DriverResponseDTO toDriverResponseDto(User entity){
        DriverResponseDTO dto = new DriverResponseDTO(
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getContactNumber(),
                entity.getStatus(),
                entity.getDriverLicense(),
                entity.getNic(),
                entity.getAddress()
        );
        return dto;
    }
}
