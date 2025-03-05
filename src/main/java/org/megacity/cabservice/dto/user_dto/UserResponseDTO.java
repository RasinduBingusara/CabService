package org.megacity.cabservice.dto.user_dto;

public class UserResponseDTO {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String contactNumber;
    private String status;
    public static final String userTpye = "Customer";

    public UserResponseDTO(String id, String firstName, String lastName, String email, String contactNumber, String status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactNumber = contactNumber;
        this.status = status;
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

    public String getUserTpye() {return userTpye;}
}
