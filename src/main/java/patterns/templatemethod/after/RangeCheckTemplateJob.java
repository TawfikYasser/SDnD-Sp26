package patterns.templatemethod.after;

import patterns.iterator.after.DataRowIterator;

public class RangeCheckTemplateJob extends DataQualityJob {
    private final int    fieldIndex;
    private final double min, max;

    public RangeCheckTemplateJob(DataRowIterator iterator, int fieldIndex, double min, double max) {
        super("RANGE CHECK", iterator);
        this.fieldIndex = fieldIndex;
        this.min = min;
        this.max = max;
    }

    @Override
    protected String validate(String[] row, int rowNum) {
        double value = Double.parseDouble(row[fieldIndex]);
        if (value < min || value > max) {
            return "Row " + rowNum + ": value " + value
                    + " out of range [" + min + ", " + max + "]";
        }
        return null;
    }
}
