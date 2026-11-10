public class Student extends LibraryMember {
    public Student(String id, String name) { super(id, name); }
    public int getLoanLimit() { return 3; }
    public int getLoanPeriodDays() { return 14; }
    public double getFineRate() { return 2.0; }
}