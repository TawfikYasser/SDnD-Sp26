package patterns.abstractfactory.bankloan.after;

public class CarLoan extends Loan {
    @Override public void getInterestRate(double rate) { this.rate = rate; }
}
