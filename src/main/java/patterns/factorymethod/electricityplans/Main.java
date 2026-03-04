package patterns.factorymethod.electricityplans;

import patterns.factorymethod.electricityplans.after.GetPlanFactory;
import patterns.factorymethod.electricityplans.after.Plan;

public class Main {

    public static void main(String[] args) {
        runBefore();
        runAfter();
    }

    static void runBefore() {

        patterns.factorymethod.electricityplans.before.Plan plan =
                new patterns.factorymethod.electricityplans.before.DomesticPlan();
        plan.getRate();
        System.out.print("domestic, 100 units: ");
        plan.calculateBill(100);

    }

    static void runAfter() {

        GetPlanFactory factory = new GetPlanFactory();

        Plan domestic = factory.getPlan("DOMESTIC");
        if (domestic != null) { domestic.getRate(); System.out.print("domestic, 100 units: "); domestic.calculateBill(100); }

        Plan commercial = factory.getPlan("COMMERCIAL");
        if (commercial != null) { commercial.getRate(); System.out.print("commercial, 100 units: "); commercial.calculateBill(100); }

        Plan institutional = factory.getPlan("INSTITUTIONAL");
        if (institutional != null) { institutional.getRate(); System.out.print("institutional, 100 units: "); institutional.calculateBill(100); }

    }
}
