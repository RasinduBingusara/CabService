package org.megacity.cabservice.service;

import org.megacity.cabservice.dto.driver_dto.DriverInsertDTO;
import org.megacity.cabservice.dto.user_dto.UserAuthDTO;
import org.megacity.cabservice.dto.user_dto.UserDetailDTO;
import org.megacity.cabservice.mapper.DriverMapper;
import org.megacity.cabservice.mapper.UserMapper;
import org.megacity.cabservice.model.Wrappers.BooleanWrapper;
import org.megacity.cabservice.model.Wrappers.PasswordWrapper;
import org.megacity.cabservice.model.Wrappers.ResponseWrapper;
import org.megacity.cabservice.model.User;
import org.megacity.cabservice.repository.AccountRepo;
import org.megacity.cabservice.util.PasswordUtill;

import java.util.regex.Pattern;

public class AccountService {

    private AccountRepo accountRepo;

    public AccountService() {
        this.accountRepo = new AccountRepo();
    }

    public ResponseWrapper<User> login(UserAuthDTO userAuthDTO) {
        PasswordWrapper<User> response = accountRepo.getUserByEmail(userAuthDTO.getEmail());
        if(response.getData() != null) {

            if(response.getData().getStatus()!= null && response.getData().getStatus().equals("Banned")){
                return new ResponseWrapper<>("🔴 Your Account has been Banned!",null);
            }
            else if(PasswordUtill.getInstance().checkPassword(userAuthDTO.getPassword(), response.getPassword())) {
                return new ResponseWrapper<>("User logged in successfully",response.getData());
            }
            else{
                return new ResponseWrapper<>("🔴 Invalid Username or Password",null);
            }
        }
        else{
            System.out.println("User not found:" + userAuthDTO.getEmail());
            return new ResponseWrapper<>("🔴 Invalid Username or Password",null);
        }
    }

    public ResponseWrapper<User> createAccount(User user, String confirmPassword) {

        ResponseWrapper<User> responseWrapper = null;
        if(!PasswordUtill.getInstance().isValidPassword(user.getPassword())) {
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
            return new ResponseWrapper<>("Email already exists",null);
        } else if (!isValidContactNumber(user.getContactNumber())) {
            return new ResponseWrapper<>("Invalid Contact Number",null);
        } else{
            String hashedPassword = PasswordUtill.getInstance().hashPassword(user.getPassword());
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

    public BooleanWrapper updateAccountPassword(String email, String newPassword, String confirmPassword) {

        if(!accountRepo.isEmailExist(email)) {
            return new BooleanWrapper("Email not exists",false);
        }
        else if(!newPassword.equals(confirmPassword)) {
            return new BooleanWrapper("Password and Confirm Password must be same",false);
        }
        else if (!PasswordUtill.getInstance().isValidPassword(newPassword)) {
            System.out.println("new password: " + newPassword);
            String error = "At least one uppercase letter (A-Z) </br>" +
                    "At least one lowercase letter (a-z) </br>" +
                    "At least one digit (0-9) </br>" +
                    "At least one special character </br>" +
                    "Minimum 8 characters in length";
            return new BooleanWrapper(error,false);
        }
        else{
            PasswordWrapper<User> response = accountRepo.getUserByEmail(email);
            if(response.getData() != null) {
                String hashedPassword = PasswordUtill.getInstance().hashPassword(newPassword);
                return accountRepo.updatePassword(response.getData().getEmail(),hashedPassword)? new BooleanWrapper("Password updated successfully",true)
                        : new BooleanWrapper("Password update failed",false);
            }
        }
        return new BooleanWrapper("Password update failed",false);
    }

    public String getProfileInfoInJson(String email) {
        UserDetailDTO userDetailDTO = accountRepo.getUserDetails(email);
        if(userDetailDTO != null) {
            return userDetailDTO.toJson();
        }
        return "{}";
    }


    public static boolean isValidContactNumber(String mobileNumber) {
        String mobileRegex = "^07[1-9]\\d{7}$";
        return Pattern.matches(mobileRegex, mobileNumber);
    }

}
