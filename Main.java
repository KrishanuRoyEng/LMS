import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static Library lib = new Library();

    static String input(String s) {
        System.out.print(s);
        return sc.nextLine().trim();
    }

    static void sampleData() {
        lib.addItem(new Book("BK001", "Clean Code", "Robert C. Martin"));
        lib.addItem(new Book("BK002", "Effective Java", "Joshua Bloch"));
        lib.addItem(new Journal("JR001", "Computing Research", "Vol. 5"));
        lib.addItem(new Magazine("MG001", "Tech Monthly", "Issue 12"));
        lib.addMember(new Student("ST101", "Aarav Sharma"));
        lib.addMember(new Faculty("FC101", "Dr. Meera Sen"));
    }

    static void menu() {
        System.out.println("\n=== LIBRARY MANAGEMENT SYSTEM ===");
        System.out.println("1 Display items | 2 Search | 3 Register student");
        System.out.println("4 Register faculty | 5 Add book | 6 Issue item");
        System.out.println("7 Return item | 8 Display members | 0 Exit");
    }

    public static void main(String[] args) {
        sampleData();
        boolean run = true;

        while (run) {
            menu();
            String choice = input("Choice: ");
            try {
                switch (choice) {
                    case "1": lib.displayItems(); break;
                    case "2": lib.search(input("ID/title: ")); break;
                    case "3":
                        System.out.println(lib.addMember(new Student(input("Student ID: "), input("Name: "))) ? "Registered." : "Duplicate ID.");
                        break;
                    case "4":
                        System.out.println(lib.addMember(new Faculty(input("Faculty ID: "), input("Name: "))) ? "Registered." : "Duplicate ID.");
                        break;
                    case "5":
                        System.out.println(lib.addItem(new Book(input("Book ID: "), input("Title: "), input("Author: "))) ? "Book added." : "Duplicate ID.");
                        break;
                    case "6":
                        System.out.println(lib.issue(input("Member ID: "), input("Item ID: ")));
                        break;
                    case "7":
                        System.out.println(lib.returnItem(input("Member ID: "), input("Item ID: ")));
                        break;
                    case "8": lib.displayMembers(); break;
                    case "0":
                        run = false;
                        System.out.println("Goodbye.");
                        break;
                    default: System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("Operation error: " + e.getMessage());
            }
        }
        sc.close();
    }
}