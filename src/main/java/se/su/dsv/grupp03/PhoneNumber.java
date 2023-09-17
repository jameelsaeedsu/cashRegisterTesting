package se.su.dsv.grupp03;

public class PhoneNumber {

    private final String countryCode;
    private final String subscriber;

    public PhoneNumber(String countryCode, String subscriber) {
        if(countryCode == null || subscriber == null)
            throw new NullPointerException("You cannot pass null as an argument.");

        String normalizedCountryCode = normalizeString(countryCode);
        String normalizedSubscriber = normalizeString(subscriber);

        if(normalizedCountryCode.isEmpty() || normalizedSubscriber.isEmpty())
            throw new IllegalArgumentException("Empty country code or subscriber.");
        if(!validatePhoneNumberLength(normalizedCountryCode + normalizedSubscriber))
            throw new IllegalArgumentException("Invalid phone number length.");

        this.countryCode = normalizedCountryCode;
        this.subscriber = normalizedSubscriber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getSubscriber() {
        return subscriber;
    }

    private String normalizeString(String str){
        // Removes everything but digits from String, then removes leading 0's.
        return str.replaceAll("[^0-9]","").replaceAll("^0+","");
    }

    private boolean validatePhoneNumberLength(String phoneNumber){
        // Maximum length for international phone numbers is 15, minimum is set to be 4.
        return phoneNumber.length() > 3 && phoneNumber.length() < 16;
    }

    @Override
    public String toString() {
        return "+" + countryCode + subscriber;
    }

}
