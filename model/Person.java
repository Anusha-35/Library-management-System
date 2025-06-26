package model;

public abstract class Person {
    protected int id;
    protected String name;
    protected String role;

    public Person(int id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getRole() { return role; }

    public abstract void displayMenu();
}

