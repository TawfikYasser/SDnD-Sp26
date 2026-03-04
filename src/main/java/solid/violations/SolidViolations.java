package solid.violations;

import java.util.ArrayList;
import java.util.List;

public class SolidViolations {

    interface BigProcessor {
        boolean process(double amount);
        void refund(double amount);
    }

    static class VisaLike implements BigProcessor {
        @Override public boolean process(double amount) {
            System.out.println("Visa: processing " + amount);
            return true;
        }
        @Override public void refund(double amount) {
            System.out.println("Visa: refunding " + amount);
        }
    }

    static class MeezaLike extends VisaLike {
        @Override public void refund(double amount) {
            throw new UnsupportedOperationException("Meeza does not support refunds!");
        }
    }

    static class ConsoleLogger {
        public void log(String msg) { System.out.println("[LOG] " + msg); }
    }

    static class PaymentGod {

        private ConsoleLogger logger = new ConsoleLogger();
        private List<String> history = new ArrayList<>();

        public boolean process(String method, double amount, String card) {

            if (card == null || card.isBlank()) {
                System.out.println("Invalid card");
                return false;
            }

            if (method.equals("VISA")) {
                System.out.println("Paying " + amount + " via Visa");
            } else if (method.equals("MEEZA")) {
                System.out.println("Paying " + amount + " via Meeza");
            } else if (method.equals("APPLEPAY")) {
                System.out.println("Paying " + amount + " via Apple Pay");
            } else {
                System.out.println("Unknown method");
                return false;
            }

            logger.log("Payment of " + amount + " processed via " + method);

            history.add(method + ":" + amount);

            return true;
        }

        public void printHistory() {
            System.out.println("History: " + history);
        }
    }

    public static void demo() {
        System.out.println("\n--- SRP, OCP, DIP violations via PaymentGod ---");
        PaymentGod god = new PaymentGod();
        god.process("VISA",    100.0, "4111111111111111");
        god.process("MEEZA",   50.0,  "5078...");
        god.process("BITCOIN", 1.0,   "wallet");
        god.printHistory();

        System.out.println("\n--- LSP violation: MeezaLike breaks refund contract ---");
        VisaLike visa = new VisaLike();
        visa.refund(100.0);

        VisaLike meeza = new MeezaLike();
        try {
            meeza.refund(50.0);
        } catch (UnsupportedOperationException e) {
            System.out.println("LSP broken → " + e.getMessage());
        }
    }
}
