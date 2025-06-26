package model;

import java.util.*;

public class LibraryUser extends Person {
    private List<Book> borrowedBooks = new ArrayList<>();

    public LibraryUser(int id, String name) {
        super(id, name, "User");
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public boolean canBorrow() {
        return borrowedBooks.size() < 3;
    }

    public void borrowBook(Book book) {
        if (canBorrow()) borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    @Override
    public void displayMenu() {
        System.out.println("User Menu:");
        System.out.println("1. View All Books");
        System.out.println("2. Borrow Book");
        System.out.println("3. Return Book");
        System.out.println("4. Exit");
    }
}
