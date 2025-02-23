package org.megacity.cabservice.model;

public class PasswordWrapper <T>{

    private String password;
    private T data;

    public PasswordWrapper(String password, T data) {
        this.password = password;
        this.data = data;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
