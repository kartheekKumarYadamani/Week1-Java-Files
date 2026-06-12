package WEEK1.task6;

import WEEK1.task6.Entity.Notification;
import WEEK1.task6.Service.NotificationService;

public class Test {

    public static void main(String[] args) {

        Notification notification=new Notification("N101", "Kartheek", "Kartheek@gmail.com", "9014875415", "Your class starts at 8:30 PM",
                "SMS", "HIGH");

        NotificationService service=new NotificationService();
        service.sendNotification(notification);
    }
}