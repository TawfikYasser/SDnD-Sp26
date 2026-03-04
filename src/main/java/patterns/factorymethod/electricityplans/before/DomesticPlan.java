package patterns.factorymethod.electricityplans.before;

public class DomesticPlan extends Plan {
    @Override
    public void getRate() { rate = 3.50; }
}
