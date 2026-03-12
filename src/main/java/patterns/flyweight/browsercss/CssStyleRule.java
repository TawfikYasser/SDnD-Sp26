package patterns.flyweight.browsercss;

// Fly weight
public final class CssStyleRule {

    private final String selector;
    private final String properties;  

    CssStyleRule(String selector, String properties) {
        this.selector   = selector;
        this.properties = properties;
        System.out.println("[CssStyleRule] Created rule: "
                + selector + " { " + properties + " }");
    }

    public void applyTo(String elementRef) {
        System.out.println("[CssStyleRule] Applying { " + properties
                + " } --> " + elementRef);
    }

    public String getSelector()   { return selector; }
    public String getProperties() { return properties; }
}
