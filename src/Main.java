import java.util.ArrayList;
import java.util.List;

// Book
public class Book extends LibraryItem implements Borrowable {
    private String isbn;
    private int numberOfPages;
    private String genre;

    public Book(String itemId, String title, String author, String isbn, int numberOfPages, String genre) {
        super(itemId, title, author);
        this.isbn = isbn;
        this.numberOfPages = numberOfPages;
        this.genre = genre;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        if (genre == null || genre.isEmpty()) {
            throw new IllegalArgumentException("Genre cannot be null or empty.");
        }
        this.genre = genre;
    }

    public void setNumberOfPages(int pages) {
        if (pages <= 0) {
            throw new IllegalArgumentException("Number of pages must be positive.");
        }
        this.numberOfPages = pages;
    }

    @Override
    public String getItemType() {
        return "Book";
    }

    @Override
    public double calculateLateFee(int daysLate) {
        return daysLate * 0.50;
    }

    @Override
    public void borrowItem(String borrowerName) {
        checkOut(borrowerName);
    }

    @Override
    public void returnItem() {
        checkIn();
    }

    @Override
    public boolean isAvailable() {
        return !isCheckedOut;
    }

    @Override
    public int getBorrowingPeriod() {
        return 14;
    }
}

//Borrowable

public interface Borrowable {
    void borrowItem(String borrowerName);

    void returnItem();

    boolean isAvailable();

    int getBorrowingPeriod();

    default String getBorrowingStatus() {
        return isAvailable() ? "Available for borrowing" : "Currently borrowed";
    }

    default String getItemInfo() {
        return String.format("%s: %s by %s", getItemType(), title, author);
    }

    default String getItemType() {
        return "DVD";
    }

    default double calculateLateFee(int daysLate) {
        return daysLate * 0.50;
    }
}

//DVD

public class DVD extends LibraryItem implements Borrowable {
    private int duration;
    private String rating;
    private String genre;

    public DVD(String itemId, String title, String author, int duration, String rating, String genre) {
        super(itemId, title, author);
        this.duration = duration;
        this.rating = rating;
        this.genre = genre;
    }

    public int getDuration() {
        return duration;
    }

    public String getRating() {
        return rating;
    }

    public String getGenre() {
        return genre;
    }

    public void setRating(String rating) {
        if (!isValidRating(rating)) {
            throw new IllegalArgumentException("Invalid rating. Must be G, PG, PG-13, R, or NC-17.");
        }
        this.rating = rating;
    }

    public void setDuration(int duration) {
        if (duration <= 0) {
            throw new IllegalArgumentException("Duration must be positive.");
        }
        this.duration = duration;
    }

    private boolean isValidRating(String rating) {
        return rating.equals("G") || rating.equals("PG") || rating.equals("PG-13") || rating.equals("R") || rating.equals("NC-17");
    }

    @Override
    public String getItemType() {
        return "DVD";
    }

    @Override
    public double calculateLateFee(int daysLate) {
        return daysLate * 1.00;
    }

    @Override
    public void borrowItem(String borrowerName) {
        checkOut(borrowerName);
    }

    @Override
    public void returnItem() {
        checkIn();
    }

    @Override
    public boolean isAvailable() {
        return !isCheckedOut;
    }

    @Override
    public int getBorrowingPeriod() {
        return 5;
    }

    @Override
    public String getBorrowingStatus() {
        return "DVD: " + Borrowable.super.getBorrowingStatus();
    }
}

//faculty
public class Faculty extends User {
    private String department;
    private String position;

    public Faculty(String userId, String name, String email, String department, String position) {
        super(userId, name, email);
        this.department = department;
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public String getPosition() {
        return position;
    }

    @Override
    public int getMaxBorrowLimit() {
        return 10;
    }
}

//libraryitem

public abstract class LibraryItem {
    protected String itemId;
    protected String title;
    protected String author;
    protected boolean isCheckedOut;
    protected String borrowerName;

    public LibraryItem(String itemId, String title, String author) {
        this.itemId = itemId;
        this.title = title;
        this.author = author;
    }

    public String getItemInfo() {
        return String.format("%s: %s by %s", getItemType(), title, author);
    }

    public void checkOut(String borrowerName) {
        this.isCheckedOut = true;
        this.borrowerName = borrowerName;
    }

    public void checkIn() {
        this.isCheckedOut = false;
        this.borrowerName = null;
    }

    public abstract String getItemType();
    public abstract double calculateLateFee(int daysLate);
}
//librarymanager

import java.util.ArrayList;
import java.util.List;

public class LibraryManager {
    private List<Borrowable> items;

    public LibraryManager() {
        this.items = new ArrayList<>();
    }

    public void addItem(Borrowable item) {
        items.add(item);
    }

    public void displayAllItems() {
        for (Borrowable item : items) {
            System.out.println(item.getItemInfo() + " - " + item.getBorrowingStatus());
        }
    }

    public void borrowItem(String itemId, String borrowerName) {
        Borrowable item = findItem(itemId);
        if (item != null && item.isAvailable()) {
            item.borrowItem(borrowerName);
            System.out.println("Borrowed: " + item.getItemInfo());
        } else {
            System.out.println("Item is not available for borrowing.");
        }
    }

    public void returnItem(String itemId) {
        Borrowable item = findItem(itemId);
        if (item != null && !item.isAvailable()) {
            item.returnItem();
            System.out.println("Returned: " + item.getItemInfo());
        } else {
            System.out.println("Item is not currently borrowed.");
        }
    }

    public void displayAvailableItems() {
        for (Borrowable item : items) {
            if (item.isAvailable()) {
                System.out.println(item.getItemInfo() + " - " + item.getBorrowingStatus());
            }
        }
    }

    public double calculateTotalLateFees(int daysLate) {
        double totalFees = 0.0;
        for (Borrowable item : items) {
            if (!item.isAvailable()) {
                totalFees += item.calculateLateFee(daysLate);
            }
        }
        return totalFees;
    }

    private Borrowable findItem(String itemId) {
        for (Borrowable item : items) {
            if (item.getItemInfo().startsWith(item.getItemType() + ": " + itemId)) {
                return item;
            }
        }
        return null;
    }
}

//magazine
public class Magazine extends LibraryItem implements Borrowable {
    private int issueNumber;
    private String publicationMonth;
    private boolean isLatestIssue;

    public Magazine(String itemId, String title, String author, int issueNumber, String publicationMonth, boolean isLatestIssue) {
        super(itemId, title, author);
        this.issueNumber = issueNumber;
        this.publicationMonth = publicationMonth;
        this.isLatestIssue = isLatestIssue;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public String getPublicationMonth() {
        return publicationMonth;
    }

    public boolean isLatestIssue() {
        return isLatestIssue;
    }

    public void setLatestIssue(boolean latest) {
        isLatestIssue = latest;
    }

    public void setIssueNumber(int issue) {
        if (issue <= 0) {
            throw new IllegalArgumentException("Issue number must be positive.");
        }
        this.issueNumber = issue;
    }

    @Override
    public String getItemType() {
        return "Magazine";
    }

    @Override
    public double calculateLateFee(int daysLate) {
        return daysLate * 0.25;
    }

    @Override
    public void borrowItem(String borrowerName) {
        checkOut(borrowerName);
    }

    @Override
    public void returnItem() {
        checkIn();
    }

    @Override
    public boolean isAvailable() {
        return !isCheckedOut;
    }

    @Override
    public int getBorrowingPeriod() {
        return 7;
    }
}

//main

public class Main {
    public static void main(String[] args) {
        Book book1 = new Book("B001", "Java Programming", "James Gosling", "978-0-13-815405-2", 624, "Computer Science");
        Book book2 = new Book("B002", "The C Programming Language", "Brian Kernighan", "978-0-13-110362-8", 272, "Computer Science");
        Magazine magazine1 = new Magazine("M001", "Tech Today", "Editor Smith", 12, "June", true);
        Magazine magazine2 = new Magazine("M002", "Science Monthly", "Dr. Jane Doe", 7, "March", false);
        DVD dvd1 = new DVD("D001", "The Matrix", "Wachowski Sisters", 136, "R", "Sci-Fi");
        DVD dvd2 = new DVD("D002", "Jurassic Park", "Steven Spielberg", 127, "PG-13", "Adventure");

        Student student = new Student("S001", "John Smith", "john@university.edu", "CS123", "Computer Science");
        Faculty faculty = new Faculty("F001", "Dr. Jane Doe", "jane@university.edu", "Engineering", "Professor");

        LibraryManager libraryManager = new LibraryManager();
        libraryManager.addItem(book1);
        libraryManager.addItem(book2);
        libraryManager.addItem(magazine1);
        libraryManager.addItem(magazine2);
        libraryManager.addItem(dvd1);
        libraryManager.addItem(dvd2);

        System.out.println("Displaying All Items:");
        libraryManager.displayAllItems();

        libraryManager.borrowItem("B001", student.getUserId());
        libraryManager.borrowItem("D001", faculty.getUserId());

        System.out.println("\nDisplaying Available Items:");
        libraryManager.displayAvailableItems();

        System.out.printf("\nTotal Late Fees: $%.2f\n", libraryManager.calculateTotalLateFees(5));

        student.displayBorrowedItems();
        faculty.displayBorrowedItems();
    }
}

//student
public class Student extends User {
    private String studentId;
    private String major;

    public Student(String userId, String name, String email, String studentId, String major) {
        super(userId, name, email);
        this.studentId = studentId;
        this.major = major;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getMajor() {
        return major;
    }

    @Override
    public int getMaxBorrowLimit() {
        return 5;
    }
}

//user

public class Student extends User {
    private String studentId;
    private String major;

    public Student(String userId, String name, String email, String studentId, String major) {
        super(userId, name, email);
        this.studentId = studentId;
        this.major = major;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getMajor() {
        return major;
    }

    @Override
    public int getMaxBorrowLimit() {
        return 5;
    }
}
