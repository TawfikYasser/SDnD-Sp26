package patterns.adapter.notificationservice;

// Concrete Adapter
class VodafoneAdapter implements INotifier {

    private final VodafoneApi api;
    private static final String API_KEY = "VF-SECRET-2024";

    public VodafoneAdapter() {
        this.api = new VodafoneApi();
    }

    public VodafoneAdapter(VodafoneApi api) {
        this.api = api;
    }

    @Override
    public void send(String to, String message) {
        System.out.println("[VodafoneAdapter]   Translating INotifier.send -> sendSms");
        // Extra argument (apiKey) is hidden from the main system
        api.sendSms(to, message, API_KEY);
    }
}


// Concrete Adapter

class ApplePushAdapter implements INotifier {

    private final ApplePushService apns;

    public ApplePushAdapter() {
        this.apns = new ApplePushService();
    }

    public ApplePushAdapter(ApplePushService apns) {
        this.apns = apns;
    }

    @Override
    public void send(String to, String message) {
        System.out.println("[ApplePushAdapter]  Translating INotifier.send -> pushPayload");
        // "to" carries the device token in this adapter's usage contract
        apns.pushPayload(to, message);
    }
}
