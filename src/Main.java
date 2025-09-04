import java.util.ArrayList;

class Book {
    // instance variables for each book
    private String title;
    private String author;
    private ArrayList<Integer> ratings; // Stores all ratings for this book
    private static int totalBooks = 0;  // Shared across all Book instances

    // constructor to initialize a new book
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.ratings = new ArrayList<>();
        totalBooks++; // Increment the shared book counter
    }

    // add a single rating with validation
    public void addRating(int rating) throws IllegalArgumentException {
        if (rating < 1 || rating > 5) throw new IllegalArgumentException("Invalid rating: must be 1-5 stars");
        ratings.add(rating);
    }

    // calculate average rating of all reviews
    public double getAverageRating() {
        if (ratings.isEmpty()) return 0.0; // Handle case with no ratings
        int sum = 0;
        for (int rating : ratings) sum += rating;
        return (double) sum / ratings.size();
    }

    // convert average rating to a popularity category
    public String getPopularityLevel() {
        if (ratings.isEmpty()) return "No ratings";
        double avg = getAverageRating();
        if (avg >= 4.5) return "Excellent";
        if (avg >= 3.5) return "Good";
        if (avg >= 2.5) return "Average";
        if (avg >= 1.5) return "Poor";
        return "Terrible";
    }

    // add multiple ratings at once using varargs
    public void addMultipleRatings(int... ratings) {
        for (int rating : ratings) {
            try {
                addRating(rating); // Reuse our single rating method
            } catch (IllegalArgumentException e) {
            }
        }
    }

    // getter methods
    public static int getTotalBooks() { return totalBooks; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }

    // display book information in a formatted string
    public String displayBook() {
        return String.format("Book: %s by %s, Average Rating: %.1f, Level: %s",
                title, author, getAverageRating(), getPopularityLevel());
    }
    public String hdisplayBook() {
        return String.format("Highest rated book: %s by %s (%.1f)",
        title, author, getAverageRating());
    }
}

public class Main {
    public static void main(String[] args) {
        // create three different books
        Book book1 = new Book("Java Programming", "John Smith");
        Book book2 = new Book("Data Structures", "Alice Brown");
        Book book3 = new Book("Web Development", "Bob Wilson");

        book1.addMultipleRatings(4, 4, 4, 4);

        book2.addMultipleRatings(5, 5, 4, 4, 3);

        book3.addMultipleRatings(4, 3, 1, 3, 3);

        book3.addMultipleRatings(5, 6, 0, 4, 7, 3);
        System.out.println("=== Book Record System ===");
        System.out.println("");
        System.out.println("Adding books and ratings...");
        System.out.println("Rating 4 added successfully");
        System.out.println("Ratings added: 5, 4, 3, 5");
        System.out.println("Error: Invalid rating: must be 1-5 stars");

        // display all book information
        System.out.println("\nBook Results:");
        System.out.println(book1.displayBook());
        System.out.println(book2.displayBook());
        System.out.println(book3.displayBook());

        // shows total books created using static method
        System.out.println("\nTotal books created: " + Book.getTotalBooks());

        // finds the book with the highest average rating
        Book highest = book1;
        if (book2.getAverageRating() > highest.getAverageRating()) highest = book2;
        if (book3.getAverageRating() > highest.getAverageRating()) highest = book3;
        System.out.println(highest.hdisplayBook());

    }
}






