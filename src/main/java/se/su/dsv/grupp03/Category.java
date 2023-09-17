package se.su.dsv.grupp03;

import se.su.dsv.grupp03.utilities.StringFormatter;

import java.util.Objects;

public class Category {
    private final String name;

    public Category(String name) {
        StringFormatter formatter = new StringFormatter();
        if(formatter.normalizeWhitespace(name).isEmpty())
            throw new IllegalArgumentException("Category name must not be empty.");
        this.name = formatter.normalizeWhitespace(name);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Category category = (Category) o;
        return name.equals(category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
