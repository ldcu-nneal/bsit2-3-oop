public class LabActivity3 {
    public static void main(String[] args) {
        Book book1 = new Book("Harry Potter and the Philosopher's Stone", "J.K. Rowling", 223);
        Book book2 = new Book("Harry Potter and the Chamber of Secrets", "J.K. Rowling", 251);
        Book book3 = new Book("Harry Potter and the Prisoner of Azkaban", "J.K. Rowling", 317);

        System.out.println("\nBook 1:");
        book1.displayInfo();
        book1.borrowBook();
        book1.returnBook();

        System.out.println("\nBook 2:");
        book2.displayInfo();
        book2.borrowBook();
        book2.returnBook();

        System.out.println("\nBook 3:");
        book3.displayInfo();
        book3.borrowBook();
        book3.returnBook();
    }
}

class Book {
    private String title;
    private String author;
    private int pages;
    private boolean isAvailable;

    public Book(String title, String author, int pages) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.isAvailable = true;
        System.out.println("A new book '" + title + "' by " + author + " has been added to the library!");
    }

    public void displayInfo() {
        System.out.println("\nBook Information:");
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Pages: " + pages);
        System.out.println("Available: " + (isAvailable ? "Yes" : "No"));
    }

    public void borrowBook() {
        isAvailable = false;
        System.out.println("Book Borrowed");
    }

    public void returnBook() {
        isAvailable = true;
        System.out.println("Book Returned");
    }
}
