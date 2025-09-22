public class Main {
    public static void main(String[] args) {
        System.out.println("=== Library Management System ===\n");

        System.out.println("Adding books to library...");
        Library library = new Library();

        Book b1 = new Book("Java Programming", "John Smith", "1234567890", 2020);
        Book b2 = new Book("Data Structures", "Jane Doe", "9876543210", 2019);
        Book b3 = new Book("Web Development", "Mike Johnson", "5555666677", 2021);

        library.addBook(b1);
        library.addBook(b2);
        library.addBook(b3);

        library.displayAllBooks();

        System.out.println("\nBorrowing Java Programming...");
        library.borrowBook("1234567890");

        System.out.println("\nTrying to borrow Java Programming again...");
        library.borrowBook("1234567890");

        library.displayAvailableBooks();

        System.out.println("\nReturning Java Programming...");
        library.returnBook("1234567890");

        System.out.println("\nTesting validation...");
        Book invalidBook = new Book("", "", "123", 1400);
    }
}
public class Library {
    private Book[] books;
    private int bookCount;

    public Library() {
        books = new Book[10];
        bookCount = 0;
    }

    public boolean addBook(Book book) {
        if (bookCount < books.length) {
            books[bookCount++] = book;
            System.out.println("Book added: " + book.getTitle() + " by " + book.getAuthor());
            return true;
        }
        return false;
    }

    public Book findBook(String isbn) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getIsbn().equals(isbn)) {
                return books[i];
            }
        }
        return null;
    }

    public boolean borrowBook(String isbn) {
        Book book = findBook(isbn);
        if (book != null && book.borrowBook()) {
            System.out.println("Book borrowed successfully!");
            return true;
        }
        System.out.println("Book is not available for borrowing.");
        return false;
    }

    public boolean returnBook(String isbn) {
        Book book = findBook(isbn);
        if (book != null && book.returnBook()) {
            System.out.println("Book returned successfully!");
            return true;
        }
        return false;
    }

    public void displayAvailableBooks() {
        System.out.println("\nAvailable books:");
        for (int i = 0; i < bookCount; i++) {
            if (books[i].isAvailable()) {
                books[i].displayBookInfo();
            }
        }
    }

    public void displayAllBooks() {
        System.out.println("\nAll books in library:");
        for (int i = 0; i < bookCount; i++) {
            books[i].displayBookInfo();
        }
    }

    public int getBookCount() {
        return bookCount;
    }
}
public class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;
    private int yearPublished;

    public Book(String title, String author, String isbn, int yearPublished) {
        setTitle(title);
        setAuthor(author);
        setYearPublished(yearPublished);
        setIsbn(isbn);
        this.isAvailable = true;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public boolean isAvailable() { return isAvailable; }
    public int getYearPublished() { return yearPublished; }

    public void setTitle(String title) {
            this.title = title;
    }

    public void setAuthor(String author) {
           this.author = author;
    }

    public void setYearPublished(int year) {
        if (year < 1450 || year > 2025) {
            System.out.println("Invalid year provided. Year must be between 1450 and 2025");
        } else {
            this.yearPublished = year;
        }
    }

    public void setIsbn(String isbn) {
        if (isbn == null || !(isbn.length() == 10 || isbn.length() == 13)) {
            System.out.println("Invalid ISBN. ISBN must be 10 or 13 characters long");
        } else {
            this.isbn = isbn;
        }
    }


    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    public boolean borrowBook() {
        if (isAvailable) {
            isAvailable = false;
            return true;
        }
        return false;
    }

    public boolean returnBook() {
        if (!isAvailable) {
            isAvailable = true;
            return true;
        }
        return false;
    }

    public void displayBookInfo() {
        System.out.println("Title: " + title + " | Author: " + author + " | ISBN: " + isbn +
                " | Year: " + yearPublished + " |");
        System.out.println("Status: " + (isAvailable ? "Available" : "Not Available"));
    }
}
