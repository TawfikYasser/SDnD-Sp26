package patterns.abstractfactory.bankloan.after;

public class EducationalLoan extends Loan {
    @Override public void getInterestRate(double rate) { this.rate = rate; }
}
