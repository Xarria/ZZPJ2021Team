package pl.zzpj2021.solid.srp.book.solution;

import java.util.HashMap;
import java.util.Map;

public class Book {
    private Map<Integer, String> pages = new HashMap<>();

    private String libraryRoomName;
    private String rowLocator;
    private int indexOnShelf;

    public Book() {}

    public Book(Map<Integer, String> pages, String libraryRoomName, String rowLocator, int indexOnShelf) {
        this.pages = pages;
        this.libraryRoomName = libraryRoomName;
        this.rowLocator = rowLocator;
        this.indexOnShelf = indexOnShelf;
    }

    public String getTitle() {
        return "A Great Book";
    }

    public String getAuthor() {
        return "John Doe";
    }

    public Map<Integer, String> getPages() {
        return pages;
    }

    public String libraryRoomName() {
        return libraryRoomName;
    }

    public String getLocationRowLocator() {
        return rowLocator;
    }

    public int getIndexOnShelf() {
        return indexOnShelf;
    }
}
