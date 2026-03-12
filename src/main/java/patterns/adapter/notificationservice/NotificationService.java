package patterns.adapter.notificationservice;

// Knows only INotifier
public class NotificationService {
    
    private final INotifier notifier;

    public NotificationService(INotifier notifier) {
        this.notifier = notifier;
    }

    public void notify(String recipient, String message) {
        System.out.println("\n[NotificationService] Dispatching to: " + recipient);
        notifier.send(recipient, message);
        System.out.println("[NotificationService] Done.");
    }
}
