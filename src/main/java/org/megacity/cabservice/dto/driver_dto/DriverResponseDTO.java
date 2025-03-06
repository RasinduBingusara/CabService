package org.megacity.cabservice.dto.driver_dto;

public class DriverResponseDTO {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String contactNumber;
    private String status;
    private String driverLicense;
    private String nic;
    private String address;
    private static final String userType = "Driver";


    public DriverResponseDTO(String id, String firstName, String lastName, String email, String contactNumber, String status, String driverLicense, String nic, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactNumber = contactNumber;
        this.status = status;
        this.driverLicense = driverLicense;
        this.nic = nic;
        this.address = address;
    }

    public String getId() {
        return id;
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

    public String getUserType() {return userType;}
}
