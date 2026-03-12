package patterns.adapter.notificationservice;

// Adaptee 
public class ApplePushService {

    public void pushPayload(String deviceToken, String alert) {
        System.out.println("[ApplePushService]  APNS push to device " + deviceToken
                + " | alert: " + alert);
    }
}
