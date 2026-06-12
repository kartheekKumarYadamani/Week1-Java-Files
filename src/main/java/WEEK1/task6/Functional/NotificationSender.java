package WEEK1.task6.Functional;

import WEEK1.task6.Entity.Notification;

@FunctionalInterface
public interface NotificationSender {
    void send(Notification notification);
}