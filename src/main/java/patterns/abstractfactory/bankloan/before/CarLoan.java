package patterns.abstractfactory.bankloan.before;
public class CarLoan extends Loan {
    @Override public void getInterestRate(double rate) { this.rate = rate; }
}
