package org.megacity.cabservice.dto.admin_dto;

public class AdminResponseDTO {

    private String firstName;
    private String lastName;
    private String email;
    private static final String userType = "Admin";

    public AdminResponseDTO(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
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

    public String getUserType() {return userType;}


}
