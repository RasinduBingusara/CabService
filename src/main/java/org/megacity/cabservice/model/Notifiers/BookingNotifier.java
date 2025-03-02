package org.megacity.cabservice.model.Notifiers;

import org.megacity.cabservice.model.Users.Notifier;
import org.megacity.cabservice.model.Users.UserNotificationListner;

import java.util.ArrayList;
import java.util.List;

public class BookingNotifier implements Notifier {

    private List<UserNotificationListner> notifiers = new ArrayList<UserNotificationListner>();
    private String status;
    @Override
    public void registerListener(UserNotificationListner listener) {
        notifiers.add(listener);
    }

    @Override
    public void removeListener(UserNotificationListner listener) {
        notifiers.remove(listener);
    }

    @Override
    public void notifyListeners() {
        for (UserNotificationListner notifier : notifiers) {
            notifier.update("Booking Status Updated to: "+ status);
        }
    }

    public void updateStatus(String status) {
        this.status = status;
        notifyListeners();
    }
}
