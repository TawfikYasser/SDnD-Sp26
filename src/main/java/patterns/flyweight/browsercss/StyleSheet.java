package patterns.flyweight.browsercss;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;


public class StyleSheet {

    private final Map<String, CssStyleRule> rules = new LinkedHashMap<>();


    public void addRule(String selector, String properties) {
        rules.put(selector, new CssStyleRule(selector, properties));
    }

    public CssStyleRule getRule(String selector) {
        return rules.get(selector);
    }

    public int ruleCount() { return rules.size(); }

    public Map<String, CssStyleRule> getRules() {
        return Collections.unmodifiableMap(rules);
    }
}
