# 📚 Library Management System (LMS)

A console-based **Java Library Management System** that demonstrates core OOP concepts — inheritance, abstraction, interfaces, and polymorphism — through a fully functional library simulation.

> Built as a mini-project; source code recovered and corrected from an OCR-scanned PDF. See [`corrections.md`](corrections.md) for the full error log.

---

## ✨ Features

- 📖 Manage **Books**, **Journals**, and **Magazines**
- 👤 Support for **Student** and **Faculty** member types with different loan limits and periods
- 🔄 **Issue** and **Return** items with automatic fine calculation
- 🔍 **Search** by item ID or title keyword
- ✅ Borrowing-limit and duplicate-ID enforcement
- 💾 Pre-loaded sample data for instant testing

---

## 🗂 Project Structure

```
LMS/
├── Borrowable.java       # Interface — issue / return / fine contract
├── LibraryItem.java      # Abstract base class (implements Borrowable)
├── Book.java             # Concrete item  — fine: ₹2 / overdue day
├── Journal.java          # Concrete item  — fine: ₹3 / overdue day
├── Magazine.java         # Concrete item  — fine: ₹2 / overdue day (inherited)
├── LibraryMember.java    # Abstract member base class
├── Student.java          # 3-item limit, 14-day loan period
├── Faculty.java          # 5-item limit, 30-day loan period
├── Library.java          # Central store — issue / return / search logic
├── Main.java             # Entry point — interactive console menu
├── corrections.md        # OCR fix log (PDF → compilable Java)
└── README.md             # This file
```

---

## 🛠 Prerequisites

| Requirement | Minimum version |
|-------------|----------------|
| JDK | 11 or higher |
| OS | Linux / macOS / Windows |

Check your Java version:

```bash
java -version
javac -version
```

---

## 🚀 Local Compilation & Execution

### 1 — Clone the repository

```bash
git clone https://github.com/KrishanuRoyEng/LMS.git
cd LMS
```

### 2 — Compile all source files

```bash
javac *.java
```

You should see **no output** (no errors). A `.class` file is generated for every `.java` file.

### 3 — Run the program

```bash
java Main
```

---

## 🖥 Using the Application

When launched, the program displays an interactive menu:

```
=== LIBRARY MANAGEMENT SYSTEM ===
1 Display items | 2 Search | 3 Register student
4 Register faculty | 5 Add book | 6 Issue item
7 Return item    | 8 Display members | 0 Exit
Choice:
```

### Menu Reference

| Option | Action |
|--------|--------|
| `1` | List all library items with current availability |
| `2` | Search by exact item ID **or** title keyword (case-insensitive) |
| `3` | Register a new student member |
| `4` | Register a new faculty member |
| `5` | Add a new book to the catalogue |
| `6` | Issue an available item to a registered member |
| `7` | Return a borrowed item (calculates fine automatically) |
| `8` | Display all registered members and their loan counts |
| `0` | Exit the program |

---

## 🧪 Quick Test Walkthrough

The program pre-loads the following sample data on startup:

**Items**

| ID | Type | Title |
|----|------|-------|
| `BK001` | Book | *Clean Code* — Robert C. Martin |
| `BK002` | Book | *Effective Java* — Joshua Bloch |
| `JR001` | Journal | *Computing Research* — Vol. 5 |
| `MG001` | Magazine | *Tech Monthly* — Issue 12 |

**Members**

| ID | Type | Name |
|----|------|------|
| `ST101` | Student | Aarav Sharma |
| `FC101` | Faculty | Dr. Meera Sen |

### Example session

```
Choice: 1
[BOOK]    BK001 | Clean Code          | Author: Robert C. Martin | Available
[BOOK]    BK002 | Effective Java      | Author: Joshua Bloch     | Available
[JOURNAL] JR001 | Computing Research  | Volume: Vol. 5           | Available
[MAGAZINE] MG001 | Tech Monthly       | Issue: Issue 12          | Available

Choice: 6
Member ID: ST101
Item ID:   BK001
→ Item issued successfully.

Choice: 7
Member ID: ST101
Item ID:   BK001
→ Returned. Fine: Rs. 0.00

Choice: 0
→ Goodbye.
```

---

## 📐 Fine Calculation Rules

| Member type | Loan period | Fine rate |
|-------------|------------|-----------|
| Student | 14 days | ₹2.00 / overdue day |
| Faculty | 30 days | ₹1.00 / overdue day |

**Journal override:** Journals always charge **₹3.00 / overdue day**, regardless of member type.

Formula: `fine = max(0, daysHeld - loanPeriod) × ratePerDay`

---

## 🏗 Class Hierarchy

```
Borrowable (interface)
└── LibraryItem (abstract)
    ├── Book
    ├── Journal          ← overrides calculateFine()
    └── Magazine

LibraryMember (abstract)
├── Student
└── Faculty

Library                  ← aggregates items + members
Main                     ← entry point
```

---

## 📝 OCR Corrections

The original source code was extracted from a scanned PDF and contained numerous OCR artifacts. All fixes are documented in [`corrections.md`](corrections.md), including:

- Missing `=` assignment operators
- Colons (`:`) misread as semicolons (`;`)
- Garbled variable names (`$=id;$` → `= id;`)
- Missing `*` and `-` math operators in fine calculations
- Incomplete generic type brackets (`Map<String, LibraryMember>`)

---

## 📄 License

This project is provided for educational purposes.
