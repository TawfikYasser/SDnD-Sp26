package patterns.iterator.after;

public class ApiPageIterator implements DataRowIterator {

    private final String[][] pages;
    private int pageIndex = 0;

    public ApiPageIterator(String[][] pages) { this.pages = pages; }

    @Override
    public boolean hasNext() {
        return pageIndex < pages.length;
    }

    @Override
    public String[] next() {
        return pages[pageIndex++];
    }
}
