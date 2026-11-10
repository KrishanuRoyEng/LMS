import java.util.ArrayList;
import java.util.List;

public abstract class LibraryMember {
    protected String memberId, name;
    protected List<String> loans = new ArrayList<>();

    public LibraryMember(String id, String name) {
        this.memberId = id;
        this.name = name;
    }

    public String getMemberId() { return memberId; }
    public String getName() { return name; }

    public abstract int getLoanLimit();
    public abstract int getLoanPeriodDays();
    public abstract double getFineRate();

    public boolean canBorrow() { return loans.size() < getLoanLimit(); }
    public void addLoan(String id) { loans.add(id); }
    public void removeLoan(String id) { loans.remove(id); }
    public boolean hasLoan(String id) { return loans.contains(id); }

    public void displayMember() {
        System.out.println(memberId + " | " + name + " | Loans: " + loans.size() + "/" + getLoanLimit());
    }
}