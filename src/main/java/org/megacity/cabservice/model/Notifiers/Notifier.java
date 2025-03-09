package org.megacity.cabservice.model.Notifiers;

public interface Notifier {

    void registerListener(UserNotificationListner listener);
    void removeListener(UserNotificationListner listener);
    void notifyListeners();
    void removeAllListeners();
}
