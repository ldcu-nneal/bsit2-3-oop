import java.util.ArrayList;
import java.util.Scanner;

public class LibraryManager {
    private ArrayList<String> books;
    private Scanner scanner;
    
    public LibraryManager() {
        books = new ArrayList<>();
        scanner = new Scanner(System.in);
        
        books.add("Java Programming");
        books.add("Web Development");
        books.add("Database Design");
    }
    
    public void showBooks() {
        try {
            System.out.println("\n--- Current Books ---");
            
            if (books == null) {
                throw new IllegalStateException("Book list is null");
            }
            
            if (books.isEmpty()) {
                System.out.println("The library has no books available.");
            } else {
                for (int i = 0; i < books.size(); i++) {
                    System.out.println((i + 1) + ". " + books.get(i));
                }
            }
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Display operation completed.");
        }
    }
    
    public void addBook() {
        try {
            System.out.print("\nEnter book title to add: ");
            String title = scanner.nextLine().trim();
            
            if (title == null || title.isEmpty()) {
                throw new IllegalArgumentException("Book title cannot be empty!");
            }
            
            if (title.length() < 3) {
                throw new IllegalArgumentException("Book title must be at least 3 characters long!");
            }
            
            books.add(title);
            System.out.println("Book '" + title + "' added successfully!");
            
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Add book operation completed.");
            showBooks();
        }
    }
    
    public void removeBook() {
        try {
            if (books.isEmpty()) {
                System.out.println("\nCannot remove books - library is empty!");
                return;
            }
            
            System.out.print("\nEnter book number to remove (1-" + books.size() + "): ");
            String input = scanner.nextLine();
            
            int index = Integer.parseInt(input) - 1;
            
            if (index < 0) {
                throw new IllegalArgumentException("Index cannot be negative!");
            }
            
            if (index >= books.size()) {
                throw new ArrayIndexOutOfBoundsException("Invalid book number! Please enter between 1 and " + books.size() + ".");
            }
            
            String removedBook = books.remove(index);
            System.out.println("Book '" + removedBook + "' removed successfully!");
            
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid number!");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Remove book operation completed.");
            showBooks();
        }
    }
    
    public static void main(String[] args) {
        LibraryManager library = new LibraryManager();
        
        System.out.println("Library Management System");
        
        library.showBooks();
        
        library.addBook();
        library.addBook();
        library.addBook();
        
        library.removeBook();
        library.removeBook();
        library.removeBook();
        
        System.out.println("\nProgram completed successfully.");
    }
}
