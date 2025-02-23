package org.megacity.cabservice.dto.driver_dto;

public class DriverDetailDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String contactNumber;
    private String userType;
    private String status;
    private String driverLicense;
    private String nic;
    private String address;
    private String employmentType;
    private String updatedAt;
    private String createdAt;

    public DriverDetailDTO(String firstName, String lastName, String email, String contactNumber, String userType, String status, String driverLicense, String nic, String address, String employmentType, String updatedAt, String createdAt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactNumber = contactNumber;
        this.userType = userType;
        this.status = status;
        this.driverLicense = driverLicense;
        this.nic = nic;
        this.address = address;
        this.employmentType = employmentType;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getUserType() {
        return userType;
    }

    public String getStatus() {
        return status;
    }

    public String getDriverLicense() {
        return driverLicense;
    }

    public String getNic() {
        return nic;
    }

    public String getAddress() {
        return address;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
