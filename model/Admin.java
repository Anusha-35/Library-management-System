package model;

public class Admin extends Person {
    public Admin(int id, String name) {
        super(id, name, "Admin");
    }

    @Override
    public void displayMenu() {
        System.out.println("Admin Menu:");
        System.out.println("1. Add Book");
        System.out.println("2. View All Books");
        System.out.println("3. View All Users");
        System.out.println("4. Search Books");
        System.out.println("5. Exit");
    }
}
