package se.su.dsv.grupp03;

import se.su.dsv.grupp03.utilities.StringFormatter;

public class Supplier {
    private final String name;
    private final String contactPerson;

    public Supplier(String name, String contactPerson) {
        StringFormatter formatter = new StringFormatter();
        if(formatter.normalizeWhitespace(name).isEmpty() || formatter.normalizeWhitespace(contactPerson).isEmpty())
            throw new IllegalArgumentException("Inputs to supplier should not be empty");
        this.name = formatter.normalizeWhitespace(name);
        this.contactPerson = formatter.normalizeWhitespace(contactPerson);
    }

    public String getName() {
        return name;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    @Override
    public String toString() {
        return name;
    }


}
