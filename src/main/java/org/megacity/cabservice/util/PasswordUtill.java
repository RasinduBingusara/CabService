package org.megacity.cabservice.util;

import org.mindrot.jbcrypt.BCrypt;

import java.util.regex.Pattern;

public class PasswordUtill {

    private static PasswordUtill INSTANCE;
    public static PasswordUtill getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PasswordUtill();
        }
        return INSTANCE;
    }
    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

    public boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return Pattern.matches(passwordRegex, password);
    }
}
