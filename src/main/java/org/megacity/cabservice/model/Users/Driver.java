package org.megacity.cabservice.model.Users;

import org.megacity.cabservice.model.Notifiers.UserNotificationListner;
import org.megacity.cabservice.model.User;

public class Driver extends User implements UserNotificationListner {
    @Override
    public void update(String message) {
        System.out.println("Notification to Driver : " + getFirstName() + " " + getLastName() + " - " + message);
    }
}
