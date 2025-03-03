package org.megacity.cabservice.service;

import org.megacity.cabservice.dto.driver_dto.DriverInsertDTO;
import org.megacity.cabservice.dto.user_dto.UserAuthDTO;
import org.megacity.cabservice.mapper.DriverMapper;
import org.megacity.cabservice.mapper.UserMapper;
import org.megacity.cabservice.model.Wrappers.PasswordWrapper;
import org.megacity.cabservice.model.Wrappers.ResponseWrapper;
import org.megacity.cabservice.model.User;
import org.megacity.cabservice.repository.AccountRepo;
import org.megacity.cabservice.util.PasswordUtill;

public class AccountService {

    private AccountRepo accountRepo = new AccountRepo();

    public ResponseWrapper<User> login(UserAuthDTO userAuthDTO) {
        PasswordWrapper<User> response = accountRepo.getUserByEmail(userAuthDTO.getEmail());
        if(response.getData() != null) {

            if(PasswordUtill.checkPassword(userAuthDTO.getPassword(), response.getPassword())) {
                return new ResponseWrapper<>("User logged in successfully",response.getData());
            }
            else{
                return new ResponseWrapper<>("Invalid Username or Password",null);
            }
        }
        else{
            System.out.println("User not found:" + userAuthDTO.getEmail());
            return new ResponseWrapper<>("User not found",null);
        }
    }

    public ResponseWrapper<User> createAccount(User user, String confirmPassword) {

        ResponseWrapper<User> responseWrapper = null;
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
            return new ResponseWrapper<>("User already exists",null);
        }
        else{
            String hashedPassword = PasswordUtill.hashPassword(user.getPassword());
            user.setPassword(hashedPassword);

            switch (user.getUserType()){
                case "Customer":
                    responseWrapper =  accountRepo.addNewCustomer(UserMapper.getInstance().toUserInsertDTO(user))?
                            new ResponseWrapper<>("Customer created successfully",user):
                            new ResponseWrapper<>("Account already exists",null);
                    break;
                case "Driver":
                    if (accountRepo.isNicExist(user.getNic())) {
                        return new ResponseWrapper<>("Nic No already exists",null);
                    }else if (accountRepo.isDriverLicenseExist(user.getDriverLicense())) {
                        return new ResponseWrapper<>("Driver License already exists",null);
                    }
                    else{
                        DriverInsertDTO dto = DriverMapper.getInstance().toDriverInsertDto(user);
                        dto.setEmploymentType("Freelancer");
                        responseWrapper =  accountRepo.addNewDriver(dto)?
                                new ResponseWrapper<>("Driver created successfully",user):
                                new ResponseWrapper<>("Account already exists",null);
                    }
                    break;
                case "Admin":
                    break;
            }

            return responseWrapper;
        }
    }
}
