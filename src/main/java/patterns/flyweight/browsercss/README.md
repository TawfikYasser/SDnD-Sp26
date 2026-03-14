# Flyweight: Browser CSS Style Rules

## The Concept

When a browser renders a page with five thousand `<p>` elements that all share the same CSS class, those elements all have the same style properties: the same font size, the same color, the same line height. If you created a separate style rule object for each DOM element, you would have five thousand objects holding identical data. The cost is entirely in the duplication.

The Flyweight pattern says: create the style rule once and share the reference. Every `DomElement` that uses `.text` points to the same `CssStyleRule` object. Whether you have five elements or five thousand, there is still exactly one `CssStyleRule` for that selector.

## The Code

```java
// CssStyleRule.java — the flyweight (shared, immutable)
public final class CssStyleRule {
    private final String selector;
    private final String properties;

    CssStyleRule(String selector, String properties) {
        this.selector   = selector;
        this.properties = properties;
    }

    public void applyTo(String elementRef) {
        System.out.println("[CssStyleRule] Applying { " + properties + " } --> " + elementRef);
    }
}

// StyleSheet.java — the factory / cache
public class StyleSheet {
    private final Map<String, CssStyleRule> rules = new LinkedHashMap<>();

    public void addRule(String selector, String properties) {
        rules.put(selector, new CssStyleRule(selector, properties));
    }

    public CssStyleRule getRule(String selector) {
        return rules.get(selector);
    }
}

// DomElement.java — holds a reference to the shared flyweight
public class DomElement {
    private final String tag;
    private final int    id;
    private final CssStyleRule rule;  // shared — not owned

    public DomElement(String tag, int id, CssStyleRule rule) {
        this.tag  = tag;
        this.id   = id;
        this.rule = rule;
    }

    public void applyStyle() {
        rule.applyTo("<" + tag + " id='" + id + "'>");
    }
}
```

The `StyleSheet` creates exactly four `CssStyleRule` objects for four selectors, and five thousand `DomElement` instances all share those four objects. The number of `CssStyleRule` objects stays at four regardless of how many DOM elements exist. This is the flyweight cache working as designed: the object count is bounded by the number of distinct values, not the number of usages.
