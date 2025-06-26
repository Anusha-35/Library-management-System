import model.*;
import service.Library;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner sc = new Scanner(System.in);

        // Predefined admin and users (no ID needed from user)
        Admin admin = new Admin(1, "AdminOne");
        LibraryUser user = new LibraryUser(2, "UserOne");  // default sample user

        library.addPerson(admin);
        library.addPerson(user);

        while (true) {
            System.out.println("\n=== Library Management System ===");
            System.out.println("Select your role:");
            System.out.println("1. Admin");
            System.out.println("2. User");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int roleChoice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (roleChoice) {
                case 1:
                    System.out.println("Welcome Admin: " );
                    runAdminMenu(admin, library, sc);
                    break;
                case 2:
                    System.out.println("Welcome User: ");
                    runUserMenu(user, library, sc);
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    // Admin menu
    public static void runAdminMenu(Admin admin, Library library, Scanner sc) {
        while (true) {
            admin.displayMenu();
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Book Title: ");
                    String title = sc.nextLine();
                    System.out.print("Author: ");
                    String author = sc.nextLine();
                    library.addBook(title, author);
                    break;
                case 2:
                    library.listBooks();
                    break;
                case 3:
                    library.listUsers();
                    break;
                case 4:
                    System.out.print("Search keyword: ");
                    String keyword = sc.nextLine();
                    library.search(keyword);
                    break;
                case 5:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    // User menu
    public static void runUserMenu(LibraryUser user, Library library, Scanner sc) {
        while (true) {
            user.displayMenu();
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    library.listBooks();
                    break;
                case 2:
                    List<Book> availableBooks = library.getAvailableBooks();
                    if (availableBooks.isEmpty()) {
                        System.out.println("No books available to borrow.");
                        break;
                    }

                    System.out.println("Available Books:");
                    for (Book book : availableBooks) {
                        System.out.println(book);
                    }

                    System.out.print("Enter Book ID to borrow: ");
                    int bookId = sc.nextInt();
                    sc.nextLine();
                    library.issueBook(user.getId(), bookId);
                    break;
                case 3:
                    System.out.print("Enter Book ID to return: ");
                    int returnId = sc.nextInt();
                    sc.nextLine();
                    library.returnBook(user.getId(), returnId);
                    break;
                case 4:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
