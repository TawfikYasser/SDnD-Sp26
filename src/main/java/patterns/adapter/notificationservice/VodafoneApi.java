package patterns.adapter.notificationservice;

// Adaptee
public class VodafoneApi {


    public void sendSms(String to, String message, String apiKey) {
        System.out.println("[VodafoneApi]       Sending SMS to " + to
                + " via Vodafone network | msg: " + message
                + " | apiKey: " + apiKey);
    }
}
