package patterns.adapter.notificationservice;


public class Main {

    public static void main(String[] args) {
        System.out.println("Vodafone SMS ===");
        NotificationService vodafoneService =
                new NotificationService(new VodafoneAdapter());
        vodafoneService.notify("01012345678", "Your order has been shipped.");

        System.out.println("\nApple Push Notification ===");
        NotificationService appleService =
                new NotificationService(new ApplePushAdapter());
        appleService.notify("device_token_f47ac10b58cc", "Your ride arrives in 3 minutes.");

        System.out.println("\nRuntime swap ===");
        INotifier[] adapters = {
            new VodafoneAdapter(),
            new ApplePushAdapter()
        };
        for (INotifier adapter : adapters) {
            NotificationService svc = new NotificationService(adapter);
            svc.notify("01011223344", "Test via " + adapter.getClass().getSimpleName());
        }
    }
}
