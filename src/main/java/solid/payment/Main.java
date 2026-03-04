package solid.payment;

import solid.payment.after.*;
import solid.payment.before.FawryPaymentService;
import solid.payment.before.FawryProcessor;

public class Main {

    public static void main(String[] args) {
        runBefore();
        runAfter();
    }

    static void runBefore() {

        new FawryPaymentService().processPayment(100.0);

        FawryProcessor fp = new FawryProcessor();
        fp.payVisa(100.0);
        fp.payApplePay(100.0);

        solid.payment.before.MeezaProcessor meeza = new solid.payment.before.MeezaProcessor();
        meeza.process(50.0);
        try {
            meeza.refund(50.0);
        } catch (UnsupportedOperationException e) {
            System.out.println("runtime crash: " + e.getMessage());
        }

    }

    static void runAfter() {

        new CardValidatorService().isValid(new Card());

        PaymentProcessor visa = new VisaProcessor();
        visa.process(100.0);
        ((Refundable) visa).refund(100.0);

        PaymentProcessor meeza = new MeezaProcessor();
        meeza.process(50.0);

        PaymentProcessor apple = new ApplePayProcessor();
        apple.process(200.0);
        ((Refundable) apple).refund(200.0);

        issueRefund(new VisaProcessor());
        issueRefund(new ApplePayProcessor());
    }

    static void issueRefund(Refundable r) {
        r.refund(75.0);
    }
}
