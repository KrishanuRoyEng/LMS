public class Magazine extends LibraryItem {
    private String issueNo;

    public Magazine(String id, String title, String issueNo) {
        super(id, title);
        this.issueNo = issueNo;
    }

    public void displayDetails() {
        System.out.println("[MAGAZINE] " + itemId + " | " + title + " | Issue: " + issueNo + " | " + (available ? "Available" : "Issued"));
    }
}