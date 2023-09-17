package se.su.dsv.grupp03;

import se.su.dsv.grupp03.utilities.StringFormatter;

public class Manufacturer {
    private final String contactPerson;
    private final String name;

    public Manufacturer(String name, String contactPerson) {
        StringFormatter formatter = new StringFormatter();
        if(formatter.normalizeWhitespace(name).isEmpty() || formatter.normalizeWhitespace(contactPerson).isEmpty())
            throw new IllegalArgumentException("Manufacturer name or contact person's name must not be empty.");
        this.contactPerson = formatter.normalizeWhitespace(contactPerson);
        this.name = formatter.normalizeWhitespace(name);
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
