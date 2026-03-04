package patterns.abstractfactory.bankloan.before;

public class LoanFactory extends AbstractFactory {
    @Override public Bank getBank(String bank) { return null; }
    @Override
    public Loan getLoan(String loan) {
        if (loan == null) return null;
        if (loan.equalsIgnoreCase("Home"))        return new HomeLoan();
        if (loan.equalsIgnoreCase("Car"))         return new CarLoan();
        if (loan.equalsIgnoreCase("Educational")) return new EducationalLoan();
        return null;
    }
}
