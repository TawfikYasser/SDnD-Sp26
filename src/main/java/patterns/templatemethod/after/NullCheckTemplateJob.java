package patterns.templatemethod.after;

import patterns.iterator.after.DataRowIterator;

public class NullCheckTemplateJob extends DataQualityJob{
    private final int fieldIndex;

    public NullCheckTemplateJob(DataRowIterator iterator, int fieldIndex) {
        super("NULL CHECK", iterator);
        this.fieldIndex = fieldIndex;
    }

    @Override
    protected String validate(String[] row, int rowNum) {
        if (row[fieldIndex] == null || row[fieldIndex].isBlank()) {
            return "Row " + rowNum + ": field[" + fieldIndex + "] is null/empty";
        }
        return null; // no issue
    }
}
