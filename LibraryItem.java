import java.time.LocalDate;

public abstract class LibraryItem implements Borrowable {
    protected String itemId, title, borrowerId;
    protected boolean available = true;
    protected LocalDate issueDate;

    public LibraryItem(String itemId, String title) {
        this.itemId = itemId;
        this.title = title;
    }

    public String getItemId() { return itemId; }
    public String getTitle() { return title; }
    public boolean isAvailable() { return available; }
    public String getBorrowerId() { return borrowerId; }
    public LocalDate getIssueDate() { return issueDate; }

    public abstract void displayDetails();

    public void issueItem(LibraryMember member) {
        if (!available) throw new IllegalStateException("Already issued.");
        available = false;
        borrowerId = member.getMemberId();
        issueDate = LocalDate.now();
    }

    public double returnItem(LibraryMember member) {
        if (available || borrowerId == null || !borrowerId.equals(member.getMemberId())) {
            throw new IllegalStateException("Not issued to this member.");
        }

        int overdue = Math.max(0, (int) (LocalDate.now().toEpochDay() - issueDate.toEpochDay()) - member.getLoanPeriodDays());
        double fine = calculateFine(overdue);

        available = true;
        borrowerId = null;
        issueDate = null;

        return fine;
    }

    public double calculateFine(int days) {
        return Math.max(0, days) * 2.0;
    }
}