package se.su.dsv.grupp03;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class EnumVatTest {

    private final BigDecimal sixPercent = new BigDecimal("0.06");
    private final BigDecimal twentyFivePercent = new BigDecimal("0.25");


    @ParameterizedTest
    @EnumSource(EnumVat.class) //Passing all the values
    void checkingAllTheValuesInTheEnumShouldBeTrue (EnumVat enumVat) {
        BigDecimal vat = enumVat.getInBigDecimal();
        double convertedEnumValues = vat.doubleValue();

        assertTrue(convertedEnumValues >= 0.06 && convertedEnumValues <= 0.25);
    }

    @ParameterizedTest
    @EnumSource(EnumVat.class) //Passing all the values
    void insertingIncorrectValueToCheckAgainstEnumValues (EnumVat enumVat) {
        BigDecimal vat = enumVat.getInBigDecimal();
        double convertedEnumValues = vat.doubleValue();

        assertFalse(convertedEnumValues > 0.25);
    }

    @ParameterizedTest
    @EnumSource(
            value = EnumVat.class,
            names = {"SIX_PERCENT"}
    )
    void checkingThatEnumValuesSixEqualsSixPercent(EnumVat enumVat) {
        BigDecimal vat = enumVat.getInBigDecimal();
        assertEquals(sixPercent, vat);
    }

    @ParameterizedTest
    @EnumSource(
            value = EnumVat.class,
            names = {"TWENTY_FIVE_PERCENT"}
    )
    void checkingTheEnumValueTwentyFiveEqualsTwentyFivePercent(EnumVat enumVat) {
        BigDecimal vat = enumVat.getInBigDecimal();
        assertEquals(twentyFivePercent, vat);
    }

    @ParameterizedTest
    @EnumSource(
            value = EnumVat.class,
            names = {"TWELVE_PERCENT"}
    )
    void checkingThatElevenPercentIsNotEqualedToEnumValueTwelve(EnumVat enumVat) {
        BigDecimal elevenPercent = new BigDecimal("0.11");
        BigDecimal vat = enumVat.getInBigDecimal();
        assertNotEquals(elevenPercent, vat);
    }

}
