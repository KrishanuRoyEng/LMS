public class Journal extends LibraryItem {
    private String volume;

    public Journal(String id, String title, String volume) {
        super(id, title);
        this.volume = volume;
    }

    public void displayDetails() {
        System.out.println("[JOURNAL] " + itemId + " | " + title + " | Volume: " + volume + " | " + (available ? "Available" : "Issued"));
    }

    public double calculateFine(int days) {
        return Math.max(0, days) * 3.0;
    }
}