package org.megacity.cabservice.dto.user_dto;

public class UserAuthDTO {

    private String email;
    private String password;

    public UserAuthDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
