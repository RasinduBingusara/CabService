package org.megacity.cabservice.model.Users;

public interface Notifier {

    void registerListener(UserNotificationListner listener);
    void removeListener(UserNotificationListner listener);
    void notifyListeners();
}
