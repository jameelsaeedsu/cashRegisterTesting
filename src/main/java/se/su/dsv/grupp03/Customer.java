package se.su.dsv.grupp03;

import se.su.dsv.grupp03.utilities.StringFormatter;

public class Customer {

    private final int id;
    private final String name;
    private static int nextId = 1;

    public Customer(String name) {
        StringFormatter formatter = new StringFormatter();
        if(formatter.normalizeWhitespace(name).isEmpty())
            throw new IllegalArgumentException("Customer name cannot be empty");
        this.name = formatter.normalizeWhitespace(name);
        this.id = nextId++;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

}
