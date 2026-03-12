package patterns.adapter.notificationservice;

// Target
public interface INotifier {

    void send(String to, String message);
}
