package org.megacity.cabservice.model.Wrappers;

public class BooleanWrapper {
    private String message;
    private boolean value;
    public BooleanWrapper(String message, boolean value) {
        this.message = message;
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}
