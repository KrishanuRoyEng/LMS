import java.time.LocalDate;

public interface Borrowable {
    void issueItem(LibraryMember member);
    double returnItem(LibraryMember member);
    double calculateFine(int overdueDays);
    LocalDate getIssueDate();
}