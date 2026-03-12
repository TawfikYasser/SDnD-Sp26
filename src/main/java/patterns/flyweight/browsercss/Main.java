package patterns.flyweight.browsercss;

public class Main {

    public static void main(String[] args) {

        StyleSheet sheet = new StyleSheet();
        sheet.addRule(".text",    "color:#333; font-size:14px; font-family:Arial; line-height:1.5");
        sheet.addRule(".title",   "color:#000; font-size:24px; font-weight:bold; margin-bottom:8px");
        sheet.addRule(".warning", "color:#c00; background:#fff3cd; border:1px solid #ffc107; padding:8px");
        sheet.addRule(".code",    "font-family:monospace; background:#f5f5f5; padding:2px 4px; font-size:13px");

        System.out.println("\nStylesheet has " + sheet.ruleCount() + " rules.\n");

        System.out.println("--- 5 <p class='text'> elements share ONE CssStyleRule ---");
        CssStyleRule textRule = sheet.getRule(".text");
        System.out.println("Rule object identity (hashCode): "
                + Integer.toHexString(System.identityHashCode(textRule)));

        for (int i = 0; i < 5; i++) {
            DomElement el = new DomElement("p", i, textRule);
            el.applyStyle();
        }

        System.out.println("\n--- Verifying same object reference ---");
        CssStyleRule r1 = sheet.getRule(".text");
        CssStyleRule r2 = sheet.getRule(".text");
        CssStyleRule r3 = sheet.getRule(".text");
        System.out.println("r1 == r2: " + (r1 == r2));  
        System.out.println("r2 == r3: " + (r2 == r3));  

        System.out.println("\n--- Simulating 5,000 DOM elements ---");
        String[] selectors = {".text", ".title", ".warning", ".code"};
        int total = 5_000;
        for (int i = 0; i < total; i++) {
            String sel = selectors[i % selectors.length];
            DomElement el = new DomElement("div", i, sheet.getRule(sel));
        }
        System.out.println("Created " + total + " DomElement instances.");
        System.out.println("Number of CssStyleRule objects created: " + sheet.ruleCount()
                + "  (not " + total + ")");
    }
}