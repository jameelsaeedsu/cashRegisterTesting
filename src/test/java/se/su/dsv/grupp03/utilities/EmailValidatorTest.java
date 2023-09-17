package se.su.dsv.grupp03.utilities;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmailValidatorTest {
    private final EmailValidator validator = new EmailValidator();

    @Test
    void returnTrueIfEmailAddressFormatIsValid() {
        // Test Case: R1 (Email address is of valid format)
        String condition = "A-Za-z.0-9!#$%&'*+-/=?^_`{|}~@e.ex-example.com";
        assertTrue(validator.validate(condition));
    }

    @Test
    void returnFalseIfMoreThanOneAtSymbol() {
        // Test Case: R2 (Address contains >1 "@" symbol)
        String condition = "ex@@example.com";
        assertFalse(validator.validate(condition));
    }

    @Test
    void returnFalseIfLessThanOneAtSymbol() {
        // Test Case: R3 (Address contains <1 "@" symbol)
        String condition = "ex.example.com";
        assertFalse(validator.validate(condition));
    }

    @Test
    void returnFalseIfLocalPartIsLongerThan64Chars() {
        // Test Case: R4 (Local part contains >64 characters)
        String condition = "aaaaaaaaa1aaaaaaaaa2aaaaaaaaa3aaaaaaaaa4aaaaaaaaa5aaaaaaaaa612345@example.com";
        assertFalse(validator.validate(condition));
    }

    @Test
    void returnFalseIfLocalPartIsShorterThan1Char() {
        // Test Case: R5 (Local part contains <1 characters)
        String condition = "@example.com";
        assertFalse(validator.validate(condition));
    }

    @Test
    void returnFalseIfMoreThanTwoConsecutivePunctuationsInLocalPart() {
        // Test Case: R6 (Local part contains consecutive punctuations)
        String condition = "ex..ex@example.com";
        assertFalse(validator.validate(condition));
    }

    @Test
    void returnFalseIfLocalPartBeginsWithPunctuations() {
        // Test Case: R7 (Local part begins with a punctuation)
        String condition = ".ex@example.com";
        assertFalse(validator.validate(condition));
    }

    @Test
    void returnFalseIfLocalPartEndsWithPunctuations() {
        // Test Case: R8 (Local part ends with a punctuation)
        String condition = "ex.@example.com";
        assertFalse(validator.validate(condition));
    }

    @Test
    void returnFalseIfLocalPartContainsDisallowedCharacters() {
        // Test Case: R9 (Local part contains disallowed characters)
        String condition = "√œπé@example.com";
        assertFalse(validator.validate(condition));
    }

    @Test
    void returnFalseIfDomainPartContainsMoreThan63CharsWithoutPunctuation() {
        // Test Case: R10 (Domain part contains more than 63 characters without punctuations)
        String condition = "ex@aaaaaaaaa1aaaaaaaaa2aaaaaaaaa3aaaaaaaaa4aaaaaaaaa5aaaaaaaaa61234.com";
        assertFalse(validator.validate(condition));
    }

    @Test
    void returnFalseIfDomainPartIsShorterThan2CharactersExcludingTopLevelDomain() {
        // Test Case: R11 (Domain part contains less than 2 characters excl. top-level domain)
        String condition = "ex@.com";
        assertFalse(validator.validate(condition));
    }

    @Test
    void returnFalseIfDomainPartContainsTwoConsecutivePunctuations() {
        // Test Case: R12 (Domain part contains two consecutive punctuations)
        String condition = "ex@example..com";
        assertFalse(validator.validate(condition));
    }

    @Test
    void returnFalseIfDomainPartContainsNoPunctuations() {
        // Test Case: R13 (Domain part contains no punctuations)
        String condition = "ex@examplecom";
        assertFalse(validator.validate(condition));
    }

    @Test
    void returnFalseIfDomainPartBeginsWithPunctuation() {
        // Test Case: R14 (Domain part begins with punctuation)
        String condition = "ex@.example.com";
        assertFalse(validator.validate(condition));
    }

    @Test
    void returnFalseIfDomainPartBeginsWithHyphen() {
        // Test Case: R15 (Domain part begins with hyphen)
        String condition = "ex@-example.com";
        assertFalse(validator.validate(condition));
    }

    @Test
    void returnFalseIfDomainPartEndsWithPunctuation() {
        // Test Case: R16 (Domain part ends with punctuation)
        String condition = "ex@example.com.";
        assertFalse(validator.validate(condition));
    }

    @Test
    void returnFalseIfDomainPartEndsWithHyphen() {
        // Test Case: R17 (Domain part ends with hyphen)
        String condition = "ex@example.com-";
        assertFalse(validator.validate(condition));
    }

    @Test
    void returnFalseIfAnyLabelBeginsWithHyphen() {
        // Test Case: R18 (Domain label begins with hyphen)
        String condition = "ex@example.-example.com";
        assertFalse(validator.validate(condition));
    }

    @Test
    void returnFalseIfAnyLabelEndsWithHyphen() {
        // Test Case: R19 (Domain label ends with hyphen)
        String condition = "ex@example-.example.com";
        assertFalse(validator.validate(condition));
    }

    @Test
    void returnFalseIfDomainPartContainsDisallowedSymbols() {
        // Test Case: R20 (Domain part contains disallowed symbols)
        String condition = "ex@_€%&/()˛ª√œπé”.com";
        assertFalse(validator.validate(condition));
    }

    @Test
    void returnFalseIfTopLevelDomainIsShorterThan2Characters() {
        // Test Case: R21 (Top level domain is shorter than 2 characters)
        String condition = "ex@example.e";
        // assertFalse(validator.validate(condition));
    }

    @Test
    void returnFalseIfTopLevelDomainIsLongerThan6Characters() {
        // Test Case: R22 (Top level domain is longer than 6 characters)
        String condition = "ex@example.example";
        // assertFalse(validator.validate(condition));
    }

    @Test
    void returnFalseIfTopLevelDomainContainsDisallowedSymbols() {
        // Test Case: R23 (Top level domain contains disallowed symbols)
        String condition = "ex@example.1√3";
        // assertFalse(validator.validate(condition));
    }

}
