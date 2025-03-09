package org.megacity.cabservice.dto.driver_dto;

public class DriverDetailDTO {

    private int id;
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


    public DriverDetailDTO(int id, String firstName, String lastName, String email, String contactNumber, String userType, String status, String driverLicense, String nic, String address, String employmentType, String updatedAt, String createdAt) {
        this.id = id;
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

    public int getId() {
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

    public String toJson() {

        StringBuilder json = new StringBuilder();
        json.append("{")
                .append("\"id\":\"").append(escapeJson(String.valueOf(id))).append("\",")
                .append("\"first_name\":\"").append(escapeJson(firstName)).append("\",")
                .append("\"last_name\":\"").append(escapeJson(lastName)).append("\",")
                .append("\"email\":\"").append(escapeJson(email)).append("\",")
                .append("\"contact_number\":\"").append(escapeJson(contactNumber)).append("\",")
                .append("\"user_type\":\"").append(escapeJson(userType)).append("\",")
                .append("\"status\":\"").append(escapeJson(status)).append("\",")
                .append("\"driver_license\":\"").append(escapeJson(driverLicense)).append("\",")
                .append("\"nic\":\"").append(escapeJson(nic)).append("\",")
                .append("\"address\":\"").append(escapeJson(address)).append("\",")
                .append("\"employment_type\":\"").append(escapeJson(employmentType)).append("\",")
                .append("\"updated_at\":\"").append(escapeJson(updatedAt)).append("\",")
                .append("\"created_at\":\"").append(escapeJson(createdAt)).append("\"")
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
