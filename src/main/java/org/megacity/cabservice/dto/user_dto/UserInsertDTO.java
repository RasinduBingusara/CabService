package org.megacity.cabservice.dto.user_dto;

public class UserInsertDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String contactNumber;
    private String status;
    private static final String userType = "Customer";

    public UserInsertDTO(String firstName, String lastName, String email, String password, String contactNumber, String status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.contactNumber = contactNumber;
        this.status = status;
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

    public void setPassword(String password) {
        this.password = password;
    }
}
