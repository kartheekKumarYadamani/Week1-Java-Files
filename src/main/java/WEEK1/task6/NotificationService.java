package WEEK1.task6;

import java.util.HashMap;
import java.util.Map;

public class NotificationService {

    NotificationSender emailSender=n->{
        if(n.getEmail()!=null&&!n.getEmail().isEmpty())
            System.out.println("Email Sent to "+n.getEmail());
    };

    NotificationSender smsSender=n->{
        if(n.getMobile()!=null&&!n.getMobile().isEmpty())
            System.out.println("SMS Sent to "+n.getMobile());
    };

    NotificationSender whatsappSender=n->{
        if(n.getMobile()!=null&&!n.getMobile().isEmpty())
            System.out.println("WhatsApp Sent to "+n.getMobile());
    };

    NotificationSender pushSender=n->System.out.println("Push Notification Sent");

    Map<String,NotificationSender> senders=new HashMap<>();

    public NotificationService(){

        senders.put("EMAIL",emailSender);
        senders.put("SMS",smsSender);
        senders.put("WHATSAPP",whatsappSender);
        senders.put("PUSH",pushSender);
    }

    public void sendNotification(Notification notification){

        if(notification.getPriority().equalsIgnoreCase("HIGH")){

            System.out.println("Sending HIGH priority notification...");

            emailSender.send(notification);
            whatsappSender.send(notification);

            System.out.println("Message : "+notification.getMessage());
        }

        else{

            NotificationSender sender=senders.get(notification.getNotificationType().toUpperCase());

            if(sender!=null){
                sender.send(notification);
                System.out.println("Message : "+notification.getMessage());
            }
        }
    }
}