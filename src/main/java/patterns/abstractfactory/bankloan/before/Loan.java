package patterns.abstractfactory.bankloan.before;

public abstract class Loan {
    protected double rate;
    public abstract void getInterestRate(double rate);
    public void calculateLoanPayment(double loanAmount, int years) {
        double monthlyRate = rate / 1200;
        int months = years * 12;
        double emi = (loanAmount * monthlyRate * Math.pow(1 + monthlyRate, months))
                / (Math.pow(1 + monthlyRate, months) - 1);
        System.out.printf(" Monthly EMI: %.2f%n", emi);
    }
}
