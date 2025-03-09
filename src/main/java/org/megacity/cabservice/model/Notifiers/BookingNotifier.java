package org.megacity.cabservice.model.Notifiers;

import java.util.ArrayList;
import java.util.List;

public class BookingNotifier implements Notifier {

    private List<UserNotificationListner> notifiers = new ArrayList<UserNotificationListner>();
    private String message;
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
            notifier.update(message);
        }
    }

    @Override
    public void removeAllListeners() {
        notifiers.clear();
    }

    public void setMessage(String message) {
        this.message = message;
        notifyListeners();
    }
}
