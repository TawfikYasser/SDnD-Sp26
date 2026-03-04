package solid.payment.after;

public class VisaProcessor implements PaymentProcessor, Refundable {

    @Override
    public boolean process(double amount) {
        System.out.println("Pay " + amount + " using Visa!");
        return true;
    }

    @Override
    public void refund(double amount) {
        System.out.println("Refunding " + amount + " via Visa.");
    }
}
