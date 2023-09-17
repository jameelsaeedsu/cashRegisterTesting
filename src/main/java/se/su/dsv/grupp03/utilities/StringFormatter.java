package se.su.dsv.grupp03.utilities;

public class StringFormatter {

    public String normalizeWhitespace(String input) {
        // Removes whitespace at beginning and end of String, and excessive whitespace.
        return input.replaceAll("\\s{2,}", " ").trim();
    }

    public String removeWhitespace(String input) {
        // Removes all white space in the string (e.g. " 1 23  " would become "123").
        return input.replaceAll("\\s+", "").trim();
    }

}
