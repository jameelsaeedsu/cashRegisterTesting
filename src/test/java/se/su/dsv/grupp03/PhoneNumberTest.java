package se.su.dsv.grupp03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PhoneNumberTest {

    private final String validCountryCodeFormat = "46";
    private final String validSubscriberFormat = "722508689";
    private final String[] invalidCountryCodeFormat = {"+46", "0046", "46", "+++46", "  +00046 "};
    private final String[] invalidSubscriberFormat = {"0722508689", "072-250 86 89", " 72 250 86 89 "};

    private final String[] invalidCountryCode = {"+", "", "+++", "0", "   "};
    private final String[] invalidSubscriber = {"+", "", "00000000a000000000a00000000", "   "};

    @Test
    void acceptsValidPhoneNumber(){
        PhoneNumber phoneNumber = new PhoneNumber(validCountryCodeFormat, validSubscriberFormat);
        assertEquals(validCountryCodeFormat, phoneNumber.getCountryCode());
        assertEquals(validSubscriberFormat, phoneNumber.getSubscriber());
    }

    @Test
    void correctToStringPrintOut(){
        PhoneNumber phoneNumber = new PhoneNumber(validCountryCodeFormat, validSubscriberFormat);
        assertEquals("+" + validCountryCodeFormat + validSubscriberFormat, phoneNumber.toString());
    }

    @Test
    void normalizeCountryCode(){
        for (String invalidCC : invalidCountryCodeFormat)
            assertEquals("46", new PhoneNumber(invalidCC, validSubscriberFormat).getCountryCode());
    }

    @Test
    void normalizeSubscriber(){
        for (String invalidSub : invalidSubscriberFormat)
            assertEquals("722508689", new PhoneNumber(validCountryCodeFormat, invalidSub).getSubscriber());
    }

    @Test
    void throwsNullPointerExceptionIfGivenNullAsArgument(){
        assertThrows(NullPointerException.class, () -> new PhoneNumber(null, validSubscriberFormat));
        assertThrows(NullPointerException.class, () -> new PhoneNumber(validCountryCodeFormat, null));
    }

    @Test
    void throwsIllegalArgumentExceptionIfGivenInvalidPhoneNumber(){
        for(String invalidCC : invalidCountryCode)
            assertThrows(IllegalArgumentException.class, () -> new PhoneNumber(invalidCC, validSubscriberFormat));
        for(String invalidSub : invalidSubscriber)
            assertThrows(IllegalArgumentException.class, () -> new PhoneNumber(validCountryCodeFormat, invalidSub));
    }

    @Test
    void throwsIllegalArgumentExceptionIfGivenPhoneNumberOfInvalidLength(){
        // Maximum length for international phone numbers is 15, minimum is set to be 4.
        assertThrows(IllegalArgumentException.class, () -> new PhoneNumber(validCountryCodeFormat, "1"));
        assertThrows(IllegalArgumentException.class, () -> new PhoneNumber(validCountryCodeFormat, "11111111111111111111"));
    }

}
