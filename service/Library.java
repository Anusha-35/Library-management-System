package service;

import model.*;
import java.util.*;

public class Library {
    private Map<Integer, Book> books = new HashMap<>();
    private Map<Integer, Person> people = new HashMap<>();
    private int bookIdCounter = 1;

    // Add a new person (Admin or User)
    public void addPerson(Person person) {
        people.put(person.getId(), person);
    }

    // Retrieve person by ID
    public Person getPersonById(int id) {
        return people.get(id);
    }

    // Add a new book
    public void addBook(String title, String author) {
        Book book = new Book(bookIdCounter++, title, author);
        books.put(book.getId(), book);
        System.out.println("Book added: " + book);
    }

    // Issue a book to a LibraryUser
    public void issueBook(int userId, int bookId) {
        Person person = people.get(userId);
        Book book = books.get(bookId);

        if (!(person instanceof LibraryUser)) {
            System.out.println("Invalid user.");
            return;
        }

        LibraryUser user = (LibraryUser) person;

        if (book == null) {
            System.out.println("Book not found.");
        } else if (book.isIssued()) {
            System.out.println("Book already issued.");
        } else if (!user.canBorrow()) {
            System.out.println("Borrow limit reached.");
        } else {
            book.issue();
            user.borrowBook(book);
            System.out.println("Book issued to " + user.getName());
        }
    }

    // Return a book from a LibraryUser
    public void returnBook(int userId, int bookId) {
        Person person = people.get(userId);
        Book book = books.get(bookId);

        if (!(person instanceof LibraryUser)) {
            System.out.println("Invalid user.");
            return;
        }

        LibraryUser user = (LibraryUser) person;

        if (book != null && book.isIssued()) {
            book.returnBook();
            user.returnBook(book);
            System.out.println("Book returned by " + user.getName());
        } else {
            System.out.println("Invalid book or not issued.");
        }
    }

    // List all books
    public void listBooks() {
        books.values().forEach(System.out::println);
    }

    // List only LibraryUsers
    public void listUsers() {
        for (Person person : people.values()) {
            if (person instanceof LibraryUser) {
                System.out.println(person);
            }
        }
    }

    // Search books by keyword
    public void search(String keyword) {
        for (Book book : books.values()) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase())
                    || book.getAuthor().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(book);
            }
        }
    }

    public List<Book> getAvailableBooks() {
    List<Book> available = new ArrayList<>();
    for (Book book : books.values()) {
        if (!book.isIssued()) {
            available.add(book);
        }
    }
    return available;
}

}
