package se.su.dsv.grupp03.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {

    public EmailValidator() {}

    public boolean isValidLocalPart(String localPart){
        if(localPart.length() > 64 || localPart.length() < 1)
            return false;
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(localPart);
        return matcher.matches();
    }

    public boolean isValidDomainPart(String domainPart){
        if(domainPart.length() > 255 || domainPart.length() < 4)
            return false;
        String regex = "^([a-zA-Z0-9][a-zA-Z0-9-]{0,62}(?<!-)\\.)+[a-zA-Z]{2,6}$";
        // LEGACY, KEEP FOR NOW: String regex = "^(?:[a-zA-Z0-9-]{1,63}\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(domainPart);
        return matcher.matches();
    }

    public boolean validate(String email) {
        // Return false if string is empty.
        if(email.isEmpty())
            return false;
        // Return false if length is not between 6-320 characters.
        if(email.length() > 320 || email.length() < 6)
            return false;
        // Return false if the address does not contain exactly one "@".
        if(!email.contains("@") || email.indexOf("@") != email.lastIndexOf("@"))
            return false;
        // Split string into two parts, validate each and return boolean sum of both.
        String[] parts = email.split("@");
        return isValidLocalPart(parts[0]) && isValidDomainPart(parts[1]);
    }
}

