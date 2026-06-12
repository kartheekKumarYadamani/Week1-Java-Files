package WEEK1.task6;

public class Notification {

    private String notificationId;
    private String userName;
    private String email;
    private String mobile;
    private String message;
    private String notificationType;
    private String priority;

    public Notification(String notificationId,String userName,String email,String mobile,String message,String notificationType,String priority){
        this.notificationId=notificationId;
        this.userName=userName;
        this.email=email;
        this.mobile=mobile;
        this.message=message;
        this.notificationType=notificationType;
        this.priority=priority;
    }

    public String getNotificationId() { return notificationId; }
    public String getUserName() { return userName; }
    public String getEmail() { return email; }
    public String getMobile() { return mobile; }
    public String getMessage() { return message; }
    public String getNotificationType() { return notificationType; }
    public String getPriority() { return priority; }
}
