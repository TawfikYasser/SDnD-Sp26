package patterns.abstractfactory.bankloan;

import patterns.abstractfactory.bankloan.after.*;

public class Main {

    public static void main(String[] args) {
        runBefore();
        runAfter();
    }

    static void runBefore() {

        patterns.abstractfactory.bankloan.before.AbstractFactory bankFactory =
                new patterns.abstractfactory.bankloan.before.BankFactory();
        patterns.abstractfactory.bankloan.before.Bank bank = bankFactory.getBank("HDFC");
        if (bank != null) System.out.println("bank: " + bank.getBankName());

        patterns.abstractfactory.bankloan.before.AbstractFactory loanFactory =
                new patterns.abstractfactory.bankloan.before.LoanFactory();
        patterns.abstractfactory.bankloan.before.Loan loan = loanFactory.getLoan("Home");
        if (loan != null) {
            loan.getInterestRate(8.5);
            System.out.print("home loan 500,000 over 20 years: ");
            loan.calculateLoanPayment(500000, 20);
        }

    }

    static void runAfter() {

        AbstractFactory bankFactory = FactoryCreator.getFactory("Bank");
        if (bankFactory != null) {
            Bank hdfc  = bankFactory.getBank("HDFC");
            Bank icici = bankFactory.getBank("ICICI");
            Bank sbi   = bankFactory.getBank("SBI");
            if (hdfc  != null) System.out.println("bank: " + hdfc.getBankName());
            if (icici != null) System.out.println("bank: " + icici.getBankName());
            if (sbi   != null) System.out.println("bank: " + sbi.getBankName());
        }

        AbstractFactory loanFactory = FactoryCreator.getFactory("Loan");
        if (loanFactory != null) {
            Loan home = loanFactory.getLoan("Home");
            if (home != null) {
                home.getInterestRate(8.5);
                System.out.print("home loan 500,000 over 20 years: ");
                home.calculateLoanPayment(500000, 20);
            }
            Loan car = loanFactory.getLoan("Car");
            if (car != null) {
                car.getInterestRate(10.0);
                System.out.print("car loan 200,000 over 5 years: ");
                car.calculateLoanPayment(200000, 5);
            }
        }

    }
}
