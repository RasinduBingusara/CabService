package org.megacity.cabservice.service;

import org.junit.Test;
import org.megacity.cabservice.dto.driver_dto.DriverDetailDTO;
import org.megacity.cabservice.dto.driver_dto.DriverInsertDTO;
import org.megacity.cabservice.model.User;
import org.megacity.cabservice.model.Wrappers.ResponseWrapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DriverAccServiceTest {
    private DriverAccService driverAccService = new DriverAccService();

    @Test
    public void testGetAllEmployeeDrivers() {
        List<DriverDetailDTO> drivers = driverAccService.getAllEmployeeDrivers();
        assertNotNull(drivers, "Driver list should not be null");
    }

    public @Test
    void testGetPortionOfDrivers_NoStatus() {
        List<DriverDetailDTO> drivers = driverAccService.getPortionOfDrivers(5, 0, "");
        assertNotNull(drivers, "Driver portion should not be null");
    }

    @Test
    public void testGetPortionOfDrivers_WithStatus() {
        List<DriverDetailDTO> drivers = driverAccService.getPortionOfDrivers(5, 0, "Active");
        assertNotNull(drivers, "Filtered driver portion should not be null");
    }

    @Test
    public void testGetDriversBySearch_NoStatus() {
        List<DriverDetailDTO> drivers = driverAccService.getDriversBySearch("John", "");
        assertNotNull(drivers, "Search result should not be null");
    }

    @Test
    public void testGetDriversBySearch_WithStatus() {
        List<DriverDetailDTO> drivers = driverAccService.getDriversBySearch("John", "Active");
        assertNotNull(drivers, "Search result with status should not be null");
    }

    @Test
    public void testGetDriverByIdInJson_ValidId() {
        String json = driverAccService.getDriverByIdInJson(1);
        assertNotNull(json, "JSON result should not be null");
    }

    @Test
    public void testGetDriverByIdInJson_InvalidId() {
        String json = driverAccService.getDriverByIdInJson(-1);
        assertNull(json, "Invalid ID should still return a valid response");
    }

    @Test
    public void testAddEmployeeDriverAcc_Valid() {
        DriverInsertDTO driver = new DriverInsertDTO(
                "test",
                "test",
                "testemployee@gmail.com",
                "Test@1234",
                "0765943251",
                "Active",
                "08080808080",
                "0808080808",
                "test addresss",
                "Employee"
        );
        ResponseWrapper<User> response = driverAccService.addEmployeeDriverAcc(driver, "Test@1234");
        assertEquals("Successfully added driver", response.getMessage());
    }

    @Test
    public void testAddEmployeeDriverAcc_InvalidPassword() {
        DriverInsertDTO driver = new DriverInsertDTO(
                "test",
                "test",
                "testemployee@gmail.com",
                "test@1234",
                "0765943251",
                "Active",
                "08080808080",
                "0808080808",
                "test addresss",
                "Employee"
        );
        ResponseWrapper<User> response = driverAccService.addEmployeeDriverAcc(driver, "test@1234");
        assertNull(response.getData());
        assertTrue(response.getMessage().contains("At least one uppercase letter"));
    }

    @Test
    public void testAddEmployeeDriverAcc_MismatchedPassword() {
        DriverInsertDTO driver = new DriverInsertDTO(
                "test",
                "test",
                "testemployee@gmail.com",
                "Test@1234",
                "0765943251",
                "Active",
                "08080808080",
                "0808080808",
                "test addresss",
                "Employee"
        );
        ResponseWrapper<User> response = driverAccService.addEmployeeDriverAcc(driver, "DifferentPassword!");
        assertNull(response.getData());
        assertEquals("Passwords do not match", response.getMessage());
    }

    @Test
    public void testAddEmployeeDriverAcc_ExistingNIC() {
        DriverInsertDTO driver = new DriverInsertDTO(
                "test",
                "test",
                "testemployee2@gmail.com",
                "Test@1234",
                "0765943251",
                "Active",
                "08080808080",
                "200324411090",
                "test addresss",
                "Employee"
        );
        ResponseWrapper<User> response = driverAccService.addEmployeeDriverAcc(driver, "Test@1234");
        assertNull(response.getData());
        assertEquals("Nic no already exists", response.getMessage());
    }

    @Test
    public void testAddEmployeeDriverAcc_ExistingDriverLicense() {
        DriverInsertDTO driver = new DriverInsertDTO(
                "test",
                "test",
                "testemployee2@gmail.com",
                "Test@1234",
                "0765943251",
                "Active",
                "b13594",
                "08080808085",
                "test addresss",
                "Employee"
        );
        ResponseWrapper<User> response = driverAccService.addEmployeeDriverAcc(driver, "Test@1234");
        assertNull(response.getData());
        assertEquals("Driver License already exists", response.getMessage());
    }

    @Test
    public void testUpdateStatus_ValidId() {
        boolean result = driverAccService.updateStatus(1, "Inactive");
        assertTrue(result, "Status should be updated successfully");
    }

    @Test
    public void testUpdateStatus_InvalidId() {
        boolean result = driverAccService.updateStatus(-1, "Inactive");
        assertFalse(result, "Status update should fail for invalid ID");
    }


}