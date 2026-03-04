package patterns.abstractfactory.bankloan.before;
public class EducationalLoan extends Loan {
    @Override public void getInterestRate(double rate) { this.rate = rate; }
}
