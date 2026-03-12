package patterns.flyweight.browsercss;

public class DomElement {

    private final String tag;
    private final int    id;
    private final CssStyleRule rule;  // shared flyweight

    public DomElement(String tag, int id, CssStyleRule rule) {
        this.tag  = tag;
        this.id   = id;
        this.rule = rule;
    }

    public void applyStyle() {
        rule.applyTo("<" + tag + " id='" + id + "'>");
    }
}
