package patterns.abstractfactory.bankloan.after;

public class FactoryCreator {

    public static AbstractFactory getFactory(String choice) {
        if (choice.equalsIgnoreCase("Bank")) return new BankFactory();
        if (choice.equalsIgnoreCase("Loan")) return new LoanFactory();
        return null;
    }
}
