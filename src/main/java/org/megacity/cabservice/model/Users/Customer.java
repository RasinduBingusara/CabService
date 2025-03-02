package org.megacity.cabservice.model.Users;

import org.megacity.cabservice.model.User;

public class Customer extends User implements UserNotificationListner{
    @Override
    public void update(String message) {
        System.out.println("Notification: " + getFirstName() + " " + getLastName() + "-> " + message );
    }
}
