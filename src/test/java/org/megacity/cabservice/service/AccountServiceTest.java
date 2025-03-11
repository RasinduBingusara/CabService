package org.megacity.cabservice.service;
import org.junit.Test;
import org.megacity.cabservice.dto.user_dto.UserAuthDTO;
import org.megacity.cabservice.model.User;
import org.megacity.cabservice.model.Wrappers.BooleanWrapper;
import org.megacity.cabservice.model.Wrappers.ResponseWrapper;

import static org.junit.jupiter.api.Assertions.*;
public class AccountServiceTest {

    private AccountService accountService = new AccountService();

    @Test
    public void testLoginWithValidCredentials() {
        UserAuthDTO userAuthDTO = new UserAuthDTO("rasindubingusara@gmail.com", "Admin@1234");
        ResponseWrapper<User> response = accountService.login(userAuthDTO);
        assertNotNull(response.getData());
        assertEquals("User logged in successfully", response.getMessage());
    }

    @Test
    public void testLoginWithInvalidPassword() {
        UserAuthDTO userAuthDTO = new UserAuthDTO("rasindubingusara@gmail.com", "WrongPassword");
        ResponseWrapper<User> response = accountService.login(userAuthDTO);
        assertNull(response.getData());
        assertEquals("Invalid Username or Password", response.getMessage());
    }

    @Test
    public void testLoginWithNonExistentEmail() {
        UserAuthDTO userAuthDTO = new UserAuthDTO("nonexistent@example.com", "Password@123");
        ResponseWrapper<User> response = accountService.login(userAuthDTO);
        assertNull(response.getData());
        assertEquals("Invalid Username or Password", response.getMessage());
    }

    @Test
    public void testCreateCustomerAccountWithValidData() {
        User user = new User();
        user.setEmail("rasindubingusaranew@gmail.com");
        user.setPassword("R@$1ndu1234");
        user.setFirstName("Rasindu");
        user.setLastName("Savan");
        user.setContactNumber("0765943251");
        user.setUserType("Customer");
        ResponseWrapper<User> response = accountService.createAccount(user, "R@$1ndu1234");
        assertNotNull(response.getData());
        assertEquals("Customer created successfully", response.getMessage());
    }

    @Test
    public void testCreateCustomerAccountWithWeakPassword() {
        User user = new User();
        user.setEmail("rasindubingusaranew2@gmail.com");
        user.setPassword("weak");
        user.setFirstName("Rasindu");
        user.setLastName("Savan");
        user.setContactNumber("0765943251");
        user.setUserType("Driver");
        ResponseWrapper<User> response = accountService.createAccount(user, "weak");
        assertNull(response.getData());
        assertTrue(response.getMessage().contains("At least one uppercase letter"));
    }

    @Test
    public void testCreateCustomerAccountWithPasswordMismatch() {
        User user = new User();
        user.setEmail("rasindubingusaranew@gmail.com");
        user.setPassword("R@$1ndu1234");
        user.setFirstName("Rasindu");
        user.setLastName("Savan");
        user.setContactNumber("0765943251");
        user.setUserType("Driver");
        ResponseWrapper<User> response = accountService.createAccount(user, "R@$1ndu1243");
        assertNull(response.getData());
        assertEquals("Passwords do not match",response.getMessage());
    }

    @Test
    public void testCreateCustomerAccountWithExistingEmail() {
        User user = new User();
        user.setEmail("rasindubingusara@gmail.com");
        user.setPassword("R@$1ndu1234");
        user.setFirstName("Rasindu");
        user.setLastName("Savan");
        user.setContactNumber("0765943251");
        user.setUserType("Driver");
        ResponseWrapper<User> response = accountService.createAccount(user, "R@$1ndu1234");
        assertNull(response.getData());
        assertEquals("Email already exists",response.getMessage());
    }

    @Test
    public void testCreateCustomerAccountWithInvalidContactNumber() {
        User user = new User();
        user.setEmail("rasindubingusaranew2@gmail.com");
        user.setPassword("R@$1ndu1234");
        user.setFirstName("Rasindu");
        user.setLastName("Savan");
        user.setContactNumber("076594325");
        user.setUserType("Driver");
        ResponseWrapper<User> response = accountService.createAccount(user, "R@$1ndu1234");
        assertNull(response.getData());
        assertEquals("Invalid Contact Number",response.getMessage());
    }

    @Test
    public void testCreateDriverAccountWithExistingNIC() {
        User user = new User();
        user.setEmail("rasindubingusaradriver@gmail.com");
        user.setPassword("R@$1ndu1234");
        user.setFirstName("Rasindu");
        user.setLastName("Savan");
        user.setContactNumber("0765943251");
        user.setNic("200324411090");
        user.setAddress("Galduwa,Kahawa.");
        user.setDriverLicense("g5486412");
        user.setUserType("Driver");
        ResponseWrapper<User> response = accountService.createAccount(user, "R@$1ndu1234");
        assertNull(response.getData());
        assertEquals("Nic No already exists",response.getMessage());
    }

    @Test
    public void testCreateDriverAccountWithExistingDriverLicense() {
        User user = new User();
        user.setEmail("rasindubingusaradriver@gmail.com");
        user.setPassword("R@$1ndu1234");
        user.setFirstName("Rasindu");
        user.setLastName("Savan");
        user.setContactNumber("0765943251");
        user.setNic("200324411053");
        user.setAddress("Galduwa,Kahawa.");
        user.setDriverLicense("B1234567");
        user.setUserType("Driver");
        ResponseWrapper<User> response = accountService.createAccount(user, "R@$1ndu1234");
        assertNull(response.getData());
        assertEquals("Driver License already exists",response.getMessage());
    }

    @Test
    public void testCreateDriverAccountWithValidData() {
        User user = new User();
        user.setEmail("rasindubingusaradriver@gmail.com");
        user.setPassword("R@$1ndu1234");
        user.setFirstName("Rasindu");
        user.setLastName("Savan");
        user.setContactNumber("0765943251");
        user.setNic("200324411053");
        user.setAddress("Galduwa,Kahawa.");
        user.setDriverLicense("B1234565");
        user.setUserType("Driver");
        ResponseWrapper<User> response = accountService.createAccount(user, "R@$1ndu1234");
        assertNotNull(response.getData());
        assertEquals("Driver created successfully",response.getMessage());
    }

    @Test
    public void testUpdatePasswordWithValidData() {
        BooleanWrapper response = accountService.updateAccountPassword("rasindubingusaranew@gmail.com", "Driver@1234", "Driver@1234");
        assertTrue(response.isValue());
        assertEquals("Password updated successfully", response.getMessage());
    }

    @Test
    public void testUpdatePasswordWithInvalidEmail() {
        BooleanWrapper response = accountService.updateAccountPassword("rasindubingusaranew2@gmail.com", "Driver@1234", "Driver@1234");
        assertFalse(response.isValue());
        assertEquals("Email not exists", response.getMessage());
    }

    @Test
    public void testUpdatePasswordWithMismatchedConfirmPassword() {
        BooleanWrapper response = accountService.updateAccountPassword("rasindubingusara@gmail.com", "Driver@1234", "WrongConfirm");
        assertFalse(response.isValue());
        assertEquals("Password and Confirm Password must be same", response.getMessage());
    }

    @Test
    public void testUpdatePasswordWithWeakNewPassword() {
        BooleanWrapper response = accountService.updateAccountPassword("rasindubingusaranew@gmail.com", "weak", "weak");
        assertFalse(response.isValue());
        assertTrue(response.getMessage().contains("At least one uppercase letter"));
    }

    @Test
    public void testGetProfileInfoWithValidEmail() {
        String profileJson = accountService.getProfileInfoInJson("rasindubingusaranew@gmail.com");
        assertFalse(profileJson.isEmpty());
        assertNotEquals("{}", profileJson);
    }

    @Test
    public void testGetProfileInfoWithInvalidEmail() {
        String profileJson = accountService.getProfileInfoInJson("invalid@example.com");
        assertEquals("{}", profileJson);
    }

    @Test
    public void testIsValidContactNumber() {
        assertTrue(AccountService.isValidContactNumber("0712345678"));
        assertFalse(AccountService.isValidContactNumber("0812345678")); // Invalid prefix
        assertFalse(AccountService.isValidContactNumber("071234567"));  // Too short
    }
}