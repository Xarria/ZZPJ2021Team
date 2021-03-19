package pl.zzpj2021.solid.srp.book.solution;

import java.util.Map;

public class Printer {

    private Book book;
    private int currentPage = 0;

    public Printer() {}

    public Printer(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getCurrentPageContents() {
        if (book != null) {
            return book.getPages().get(currentPage);
        }
        return "No book loaded";
    }

    public void turnPage() {
        currentPage ++;
    }

    public String printAllPages() {
        if (book != null) {
            StringBuilder allPages = new StringBuilder();
            for(Map.Entry<Integer, String> page : book.getPages().entrySet()) {
                allPages.append(page.getKey()).append(" ").append(page.getValue());
            }
            return allPages.toString();
        }
        return "No book loaded";
    }
}
