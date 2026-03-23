package patterns.iterator.before;

public class ApiPagedResponse {
    private final String[][] pages;
    private int pageIndex = 0;

    public ApiPagedResponse(String[][] pages) { this.pages = pages; }

    public boolean  hasNextPage() { return pageIndex < pages.length; }
    public String[] fetchPage()   { return pages[pageIndex++]; }
}
