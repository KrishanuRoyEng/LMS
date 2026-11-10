public class Faculty extends LibraryMember {
    public Faculty(String id, String name) { super(id, name); }
    public int getLoanLimit() { return 5; }
    public int getLoanPeriodDays() { return 30; }
    public double getFineRate() { return 1.0; }
}