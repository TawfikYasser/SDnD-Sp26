package solid.payment.after;

public interface TransactionLogger {
    void log(Transaction transaction);
}
