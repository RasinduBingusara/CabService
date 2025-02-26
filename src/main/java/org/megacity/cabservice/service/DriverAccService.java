package org.megacity.cabservice.service;

import org.megacity.cabservice.dto.driver_dto.DriverDetailDTO;
import org.megacity.cabservice.dto.driver_dto.DriverInsertDTO;
import org.megacity.cabservice.mapper.DriverMapper;
import org.megacity.cabservice.model.ResponseWrapper;
import org.megacity.cabservice.model.User;
import org.megacity.cabservice.repository.AccountRepo;
import org.megacity.cabservice.util.PasswordUtill;

import java.util.List;

public class DriverAccService {

    private AccountRepo accountRepo = new AccountRepo();
    public List<DriverDetailDTO> getAllEmployeeDrivers() {
        return accountRepo.getAllDrivers();
    }
    public List<DriverDetailDTO> getPortionOfDrivers(String limit, String offset, String status) {
        return status.isEmpty()? accountRepo.getPortionOfDriver(limit, offset):
                accountRepo.getPortionOfDriverWithStatus(limit, offset,status);
    }
    public List<DriverDetailDTO> getDriversBySearch(String keyword, String status) {
        return status.isEmpty()? accountRepo.getDriversBySearch(keyword):
                accountRepo.getDriversBySearchWithStatus(keyword,status);
    }
    public ResponseWrapper<User> addEmployeeDriverAcc(DriverInsertDTO user, String confirmPassword) {
        if(!PasswordUtill.isValidPassword(user.getPassword())) {
            String error = "At least one uppercase letter (A-Z) </br>" +
                    "At least one lowercase letter (a-z) </br>" +
                    "At least one digit (0-9) </br>" +
                    "At least one special character </br>" +
                    "Minimum 8 characters in length";
            return new ResponseWrapper<>(error,null);
        }
        else if (!user.getPassword().equals(confirmPassword)) {
            String error = "Passwords do not match";
            return new ResponseWrapper<>(error,null);
        }
        else if(accountRepo.isEmailExist(user.getEmail())) {
            System.out.println("email already exist: " + user.getEmail());
            return new ResponseWrapper<>("Email already exists",null);
        } else if (accountRepo.isNicExist(user.getNic())) {
            return new ResponseWrapper<>("Nic no already exists",null);
        }else if (accountRepo.isDriverLicenseExist(user.getDriverLicense())) {
            return new ResponseWrapper<>("Driver License already exists",null);
        }
        else{
            String hashedPassword = PasswordUtill.hashPassword(user.getPassword());
            user.setPassword(hashedPassword);
            System.out.println("User creating for: " + user.getEmail());
            if(accountRepo.addNewDriver(user)){
                return new ResponseWrapper<>("Successfully added driver",
                        DriverMapper.getInstance().toEntity(user));
            }
            else {
                return new ResponseWrapper<>("Failed to add driver",null);
            }
        }
    }
}
