package patterns.abstractfactory.bankloan.after;

public class HomeLoan extends Loan {
    @Override public void getInterestRate(double rate) { this.rate = rate; }
}
