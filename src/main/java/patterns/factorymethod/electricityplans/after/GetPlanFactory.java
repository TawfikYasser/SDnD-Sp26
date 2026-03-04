package patterns.factorymethod.electricityplans.after;

public class GetPlanFactory {

    public Plan getPlan(String type) {
        if (type == null) return null;
        return switch (type.toUpperCase()) {
            case "DOMESTIC"     -> new DomesticPlan();
            case "COMMERCIAL"   -> new CommercialPlan();
            case "INSTITUTIONAL" -> new InstitutionalPlan();
            default              -> null;
        };
    }
}
