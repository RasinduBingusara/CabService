package org.megacity.cabservice.dto.driver_dto;

public class DriverEditDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String contactNumber;
    private String status;
    private String driverLicense;
    private String nic;
    private String address;

    public DriverEditDTO(String firstName, String lastName, String email, String password, String contactNumber, String status, String driverLicense, String nic, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.contactNumber = contactNumber;
        this.status = status;
        this.driverLicense = driverLicense;
        this.nic = nic;
        this.address = address;
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

    public String getPassword() {
        return password;
    }

    public String getContactNumber() {
        return contactNumber;
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
}
