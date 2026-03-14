# Adapter: Notification Service

## The Problem

Third-party notification providers have their own APIs and their own method signatures. `VodafoneApi` has a `sendSms` method that requires three arguments including an API key. `ApplePushService` has a `pushPayload` method that takes a device token and an alert string. Neither of them looks anything like each other, and neither matches whatever clean interface your application would want to work with.

If you call these APIs directly from your `NotificationService`, the service has to know the exact method names, parameter orders, and quirks of every provider it supports. Adding a new provider means modifying the service. Switching providers means rewriting call sites. Testing the service in isolation requires that the real third-party APIs are available.

## What Changed

The solution is a common `INotifier` interface with a single `send(String to, String message)` method. `VodafoneAdapter` implements `INotifier` and translates that call into `VodafoneApi.sendSms`, hiding the API key internally. `ApplePushAdapter` implements `INotifier` and translates into `ApplePushService.pushPayload`, treating the `to` parameter as a device token.

`NotificationService` takes an `INotifier` in its constructor and calls `notifier.send(recipient, message)`. It has no idea which provider is behind the interface. You can swap Vodafone for Apple Push, add a new provider, or inject a mock in a test by simply passing a different `INotifier` implementation.

## The Code

```java
// INotifier.java — the target interface your system knows about
public interface INotifier {
    void send(String to, String message);
}

// VodafoneAdapter.java — translates INotifier into VodafoneApi
class VodafoneAdapter implements INotifier {
    private final VodafoneApi api;
    private static final String API_KEY = "VF-SECRET-2024";

    @Override
    public void send(String to, String message) {
        api.sendSms(to, message, API_KEY);  // API key is hidden from the caller
    }
}

// ApplePushAdapter.java — translates INotifier into ApplePushService
class ApplePushAdapter implements INotifier {
    private final ApplePushService apns;

    @Override
    public void send(String to, String message) {
        apns.pushPayload(to, message);  // "to" is a device token in this context
    }
}

// NotificationService.java — only knows INotifier
public class NotificationService {
    private final INotifier notifier;

    public NotificationService(INotifier notifier) {
        this.notifier = notifier;
    }

    public void notify(String recipient, String message) {
        notifier.send(recipient, message);
    }
}
```

At the call site, switching providers is just swapping which adapter you pass in:

```java
NotificationService sms = new NotificationService(new VodafoneAdapter());
sms.notify("01012345678", "Your order has been shipped.");

NotificationService push = new NotificationService(new ApplePushAdapter());
push.notify("device_token_f47ac10b58cc", "Your ride arrives in 3 minutes.");
```

`NotificationService` never changes regardless of how many providers you add or swap.
