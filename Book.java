public class Book extends LibraryItem {
    private String author;

    public Book(String id, String title, String author) {
        super(id, title);
        this.author = author;
    }

    public void displayDetails() {
        System.out.println("[BOOK] " + itemId + " | " + title + " | Author: " + author + " | " + (available ? "Available" : "Issued"));
    }
}