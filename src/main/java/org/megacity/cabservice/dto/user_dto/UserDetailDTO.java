package org.megacity.cabservice.dto.user_dto;

public class UserDetailDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String contactNumber;
    private String userType;
    private String status;
    private String updatedAt;
    private String createdAt;


    public UserDetailDTO(String firstName, String lastName, String email, String contactNumber, String userType, String status, String updatedAt, String createdAt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactNumber = contactNumber;
        this.userType = userType;
        this.status = status;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    public UserDetailDTO(String firstName, String lastName, String email, String contactNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactNumber = contactNumber;
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

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String toJson() {

        StringBuilder json = new StringBuilder();
        json.append("{")
                .append("\"first_name\":\"").append(escapeJson(firstName)).append("\",")
                .append("\"last_name\":\"").append(escapeJson(lastName)).append("\",")
                .append("\"email\":\"").append(escapeJson(email)).append("\",")
                .append("\"contact_number\":\"").append(escapeJson(contactNumber)).append("\"")
                .append("}");

        return json.toString();
    }

    private String escapeJson(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("\\", "\\\\")   // Escape backslashes
                .replace("\"", "\\\"")   // Escape double quotes
                .replace("\n", "\\n")    // Escape new lines
                .replace("\r", "\\r")    // Escape carriage returns
                .replace("\t", "\\t");   // Escape tabs
    }
}
