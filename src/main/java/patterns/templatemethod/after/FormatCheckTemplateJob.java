package patterns.templatemethod.after;

import patterns.iterator.after.DataRowIterator;

public class FormatCheckTemplateJob extends DataQualityJob {
    private final int    fieldIndex;
    private final String pattern;

    public FormatCheckTemplateJob(DataRowIterator iterator, int fieldIndex, String pattern) {
        super("FORMAT CHECK", iterator);
        this.fieldIndex = fieldIndex;
        this.pattern    = pattern;
    }

    @Override
    protected String validate(String[] row, int rowNum) {
        if (!row[fieldIndex].matches(pattern)) {
            return "Row " + rowNum + ": field[" + fieldIndex + "] = '"
                    + row[fieldIndex] + "' does not match pattern: " + pattern;
        }
        return null;
    }

}
