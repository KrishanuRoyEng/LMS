import java.util.*;

public class Library {
    private Map<String, LibraryItem> items = new LinkedHashMap<>();
    private Map<String, LibraryMember> members = new LinkedHashMap<>();

    public boolean addItem(LibraryItem x) {
        if (items.containsKey(x.getItemId())) return false;
        items.put(x.getItemId(), x);
        return true;
    }

    public boolean addMember(LibraryMember x) {
        if (members.containsKey(x.getMemberId())) return false;
        members.put(x.getMemberId(), x);
        return true;
    }

    public void displayItems() {
        if (items.isEmpty()) { System.out.println("No items."); return; }
        for (LibraryItem x : items.values()) x.displayDetails();
    }

    public void search(String q) {
        boolean found = false;
        for (LibraryItem x : items.values()) {
            if (x.getItemId().equalsIgnoreCase(q) || x.getTitle().toLowerCase().contains(q.toLowerCase())) {
                x.displayDetails();
                found = true;
            }
        }
        if (!found) System.out.println("No matching item.");
    }

    public String issue(String mid, String iid) {
        LibraryMember m = members.get(mid);
        LibraryItem x = items.get(iid);

        if (m == null) return "Member ID not found.";
        if (x == null) return "Item ID not found.";
        if (!x.isAvailable()) return "Item already issued.";
        if (!m.canBorrow()) return "Borrowing limit reached.";

        x.issueItem(m);
        m.addLoan(iid);
        return "Item issued successfully.";
    }

    public String returnItem(String mid, String iid) {
        LibraryMember m = members.get(mid);
        LibraryItem x = items.get(iid);

        if (m == null) return "Member ID not found.";
        if (x == null) return "Item ID not found.";
        if (!m.hasLoan(iid)) return "Member did not borrow this item.";

        double fine = x.returnItem(m);
        m.removeLoan(iid);
        return String.format("Returned. Fine: Rs. %.2f", fine);
    }

    public void displayMembers() {
        if (members.isEmpty()) { System.out.println("No members."); return; }
        for (LibraryMember m : members.values()) m.displayMember();
    }
}