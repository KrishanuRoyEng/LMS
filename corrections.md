# OCR Corrections Log — Java Library Management System

This document records every fix applied to the OCR-extracted source code from the PDF
(**Section 8 – Java Library Management Mini Project**) to make it compile and run correctly.

---

## Summary of Issues

| # | Category | Files Affected |
|---|----------|---------------|
| 1 | Missing assignment operators (`=`) | `LibraryItem.java`, `LibraryMember.java`, `Library.java` |
| 2 | Incorrect punctuation (`:` → `;`) | `Borrowable.java`, `Book.java` |
| 3 | Malformed conditionals & garbled variables | `LibraryMember.java`, `Library.java` |
| 4 | Missing mathematical operators (`*`, `-`) | `LibraryItem.java`, `Journal.java` |
| 5 | Missing generic closing bracket (`>`) | `Library.java` |

---

## Detailed Fixes

### 1 — Missing Assignment Operators

Several variable initializations and constructor assignments were missing the `=` sign,
likely because the PDF's OCR dropped it.

| File | Original (broken) | Fixed |
|------|-------------------|-------|
| `LibraryItem.java` | `boolean available true;` | `boolean available = true;` |
| `LibraryItem.java` | `this.itemId itemId;` | `this.itemId = itemId;` |
| `LibraryItem.java` | `this.title title;` | `this.title = title;` |
| `LibraryMember.java` | `this.name name;` | `this.name = name;` |

---

### 2 — Incorrect Punctuation (`:` instead of `;`)

The OCR misread semicolons as colons at the end of interface method signatures and
some statement lines.

| File | Original (broken) | Fixed |
|------|-------------------|-------|
| `Borrowable.java` | `void issueItem(LibraryMember member):` | `void issueItem(LibraryMember member);` |
| `Borrowable.java` | `double returnItem(LibraryMember member):` | `double returnItem(LibraryMember member);` |
| `Borrowable.java` | `double calculateFine(int overdueDays):` | `double calculateFine(int overdueDays);` |
| `Borrowable.java` | `LocalDate getIssueDate():` | `LocalDate getIssueDate();` |
| `Book.java` | `this.author author:` | `this.author = author;` |

---

### 3 — Malformed Conditionals & Garbled Variables

The worst OCR artifacts appeared as LaTeX-style dollar-sign fragments and broken
equality operators.

| File | Original (broken) | Fixed |
|------|-------------------|-------|
| `LibraryMember.java` | `this.memberId $=id;$` | `this.memberId = id;` |
| `Library.java` | `if ( $(x=$ null)` | `if (x == null)` |
| `Library.java` | `LibraryItem x = items.get(iid) if (x= null)` | `LibraryItem x = items.get(iid); if (x == null)` |

---

### 4 — Missing Mathematical Operators

The fine-calculation expressions were missing `*` (multiplication) and in one case `-`
(subtraction), making the expressions invalid.

| File | Original (broken) | Fixed |
|------|-------------------|-------|
| `LibraryItem.java` | `Math.max(0, days) 2.0;` | `Math.max(0, days) * 2.0;` |
| `LibraryItem.java` | `issueDate.toEpochDay()) member.getLoanPeriodDays()` | `issueDate.toEpochDay()) - member.getLoanPeriodDays()` |
| `Journal.java` | `Math.max(0, days) 3.0;` | `Math.max(0, days) * 3.0;` |

---

### 5 — Missing Generic Closing Bracket

The `Map` type declaration for the members collection was missing its closing `>`,
which would cause a compile-time syntax error.

| File | Original (broken) | Fixed |
|------|-------------------|-------|
| `Library.java` | `Map<String, LibraryMember members = new LinkedHashMap<>();` | `Map<String, LibraryMember> members = new LinkedHashMap<>();` |

---

## Project File Structure

```
LMS/
├── Borrowable.java       ← interface: issue/return/fine contract
├── LibraryItem.java      ← abstract base class (implements Borrowable)
├── Book.java             ← concrete item (fine rate: ₹2/day)
├── Journal.java          ← concrete item (fine rate: ₹3/day)
├── Magazine.java         ← concrete item (fine rate: ₹2/day, inherited)
├── LibraryMember.java    ← abstract member base class
├── Student.java          ← 3-book limit, 14-day loan period
├── Faculty.java          ← 5-book limit, 30-day loan period
├── Library.java          ← central store; issue/return/search logic
└── Main.java             ← interactive console menu (entry point)
```

---

## How to Compile and Run

### Prerequisites
- **Java 11+** installed (`java -version` to check)
- All 10 `.java` files in the **same directory**

### Step 1 — Compile

```bash
cd /home/krish/Documents/java/LMS
javac *.java
```

You should see **no errors**. This generates a `.class` file for each source file.

### Step 2 — Run

```bash
java Main
```

### Step 3 — Use the Menu

```
=== LIBRARY MANAGEMENT SYSTEM ===
1 Display items | 2 Search | 3 Register student
4 Register faculty | 5 Add book | 6 Issue item
7 Return item    | 8 Display members | 0 Exit
Choice:
```

| Option | What it does |
|--------|-------------|
| `1` | List all library items with availability |
| `2` | Search by item ID or title keyword |
| `3` | Register a new student member |
| `4` | Register a new faculty member |
| `5` | Add a new book to the catalogue |
| `6` | Issue an item to a member |
| `7` | Return an item and calculate any fine |
| `8` | Display all registered members |
| `0` | Exit the program |

### Sample Session

```
Choice: 6
Member ID: ST101
Item ID:   BK001
→ Item issued successfully.

Choice: 7
Member ID: ST101
Item ID:   BK001
→ Returned. Fine: Rs. 0.00
```

> [!TIP]
> The fine is calculated as **overdue days × rate per day**.
> Students are charged ₹2/day; Journals charge ₹3/day regardless of member type.
> No fine is charged if the item is returned within the allowed loan period.

> [!NOTE]
> Pre-loaded sample data includes two books (`BK001`, `BK002`), one journal (`JR001`),
> one magazine (`MG001`), one student (`ST101 — Aarav Sharma`), and one faculty
> member (`FC101 — Dr. Meera Sen`). Use these IDs to test immediately after launch.
