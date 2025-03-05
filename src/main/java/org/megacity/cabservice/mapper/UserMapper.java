package org.megacity.cabservice.mapper;


import org.megacity.cabservice.dto.user_dto.*;
import org.megacity.cabservice.model.User;

public class UserMapper {

    private static UserMapper INSTANCE;

    public static UserMapper getInstance() {
        if(INSTANCE == null){
            INSTANCE = new UserMapper();
        }
        return INSTANCE;
    }

    public User toEntity(UserInsertDTO dto){
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setContactNumber(dto.getContactNumber());
        user.setStatus(dto.getStatus());
        return user;
    }
    public UserInsertDTO toUserInsertDTO(User entity){
        UserInsertDTO dto = new UserInsertDTO(
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getContactNumber(),
                entity.getStatus()
        );
        return dto;
    }

    public User toEntity(UserResponseDTO dto){
        User user = new User();
        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setContactNumber(dto.getContactNumber());
        user.setStatus(dto.getStatus());
        user.setUserType(dto.getUserTpye());
        return user;
    }

    public UserResponseDTO toUserResponseDTO(User entity){
        UserResponseDTO dto = new UserResponseDTO(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getContactNumber(),
                entity.getStatus()
        );
        return dto;
    }

    public User toEntity(UserEditDTO dto){
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setContactNumber(dto.getContactNumber());
        user.setStatus(dto.getStatus());
        return user;
    }
    public UserEditDTO toUserEditDTO(User entity){
        UserEditDTO dto = new UserEditDTO(
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getContactNumber(),
                entity.getStatus()
        );
        return dto;
    }

    public User toEntity(UserDetailDTO dto){
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setContactNumber(dto.getContactNumber());
        user.setStatus(dto.getStatus());
        user.setStatus(dto.getStatus());
        user.setUpdatedAt(dto.getUpdatedAt());
        user.setCreatedAt(dto.getCreatedAt());
        return user;
    }

    public UserDetailDTO toUserDetailDTO(User entity){
        UserDetailDTO dto = new UserDetailDTO(
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getContactNumber(),
                entity.getUserType(),
                entity.getStatus(),
                entity.getUpdatedAt(),
                entity.getCreatedAt()
        );
        return dto;
    }

    public User toEntity(UserAuthDTO dto){
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }

    public UserAuthDTO toUserAuthDTO(User entity){
        UserAuthDTO dto = new UserAuthDTO(
                entity.getEmail(),
                entity.getPassword()
        );
        return dto;
    }

}
