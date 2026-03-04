package solid.payment.after;

public class ApplePayProcessor implements PaymentProcessor, Refundable {

    @Override
    public boolean process(double amount) {
        System.out.println("Pay " + amount + " using Apple Pay!");
        return true;
    }

    @Override
    public void refund(double amount) {
        System.out.println("Apple Pay refund procedure...");
    }
}
