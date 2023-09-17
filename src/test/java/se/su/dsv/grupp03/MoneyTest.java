package se.su.dsv.grupp03;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Currency;
import java.util.Locale;

public class MoneyTest {

    @Test
    void createMoneyAmountFiveAndEqualsBigDecimalValueFivePointZeroZero() {
        Money five = new Money(5);
        Money fivePointZeroZero = new Money(5.00);

        assertEquals(fivePointZeroZero, five);
    }

    @Test
    void throwsNumberFormatExceptionDueToNegativeAmount() {
        NumberFormatException exception = assertThrows(NumberFormatException.class, () ->
                new Money(-1));
    }


    @Test
    void createMoneyWithManyDecimalsAndExpectedTwoDecimals() {
        Money valueWithDecimals = new Money(99.994999494949494);
        Money expectedValue = new Money(99.99);

        assertEquals(expectedValue, valueWithDecimals);
    }

    @Test
    void createMoneyWithManyDecimalsAndShouldNotBeEqualedToExpected() {
        Money valueWithDecimals = new Money(99.994999494949494);
        Money expectedValue = new Money(99.98);

        assertNotEquals(expectedValue, valueWithDecimals);
    }

    @Test
    void roundingDownToClosestDecimal() {
        Money valueWithDecimals = new Money(199.9949);
        Money expectedValue = new Money(199.99);

        assertEquals(expectedValue, valueWithDecimals);
    }

    @Test
    void roundingUpToClosestDecimal() {
        Money valueWithDecimals = new Money(199.995);
        Money expectedValue = new Money(200.00);

        assertEquals(expectedValue, valueWithDecimals);
    }

    @Test
    void roundingUpToClosesDecimalManyDecimals() {
        Money valueWithDecimals = new Money(588.505999999);
        Money expectedValue = new Money(588.51);

        assertEquals(expectedValue, valueWithDecimals);
    }

    @Test
    void roundingDownToClosestDecimalManyDecimals() {
        Money valueWithDecimals = new Money(2976.7949999);
        Money expectedValue = new Money(2976.79);

        assertEquals(expectedValue, valueWithDecimals);
    }

    @Test
    void currencySymbolShouldNotBeUSD() {
        Money oneNinetyNine = new Money(199.99);
        String currenSymbolOfSweden = oneNinetyNine.getCurrency().getSymbol();

        assertNotEquals("USD", currenSymbolOfSweden);
    }

    @Test
    void twoMoneyObjectsWithSameValueAreTheSame() {
        Money five = new Money(5);
        Money anotherFive = new Money(5.00);

        assertTrue(five.equals(anotherFive) && anotherFive.equals(five));
    }

    @Test
    void twoDifferentMoneyObjectsAreNotEqualed() {
        Money decimalValueOne = new Money(599.99);
        Money decimalValueTwo = new Money(599.999);

        assertFalse(decimalValueOne.equals(decimalValueTwo) && decimalValueTwo.equals(decimalValueOne));
    }

    @Test
    void testCurrencySymbolIsCorrect() {
        Money five = new Money(5);
        String swedishCurrency = Currency.getInstance(new Locale("en", "SE")).getSymbol();

        assertEquals(swedishCurrency, five.getCurrency().getSymbol());
    }

    @Test
    void toStringShouldPresentAsExpected() {
        Money ninetyNineDecimals = new Money(99.994564549);
        String expectedPresentation = ninetyNineDecimals.getAmount() + " " + ninetyNineDecimals.getCurrency().getSymbol();

        assertEquals(expectedPresentation, ninetyNineDecimals.toString());
    }

    @Test
    void multiplyMoneyWithQuantityIntAndReturnNewMoney() {
        int quantityOfProduct = 25;
        Money oneNinetyNine = new Money(199.99);

        Money totalSum = oneNinetyNine.multiplyWithInteger(quantityOfProduct);
        Money expectedMoneyValue = new Money(4999.75);

        assertEquals(expectedMoneyValue, totalSum);
    }

    @Test
    void checkIfQuantityIsLessThanOneInMultiplyMethodShouldeThrowIllegalArgumentException(){
        int quantityOfProduct = 0;
        Money fiveFiftyFive = new Money(555.99);

        assertThrows(IllegalArgumentException.class, () -> {
            Money totalSum = fiveFiftyFive.multiplyWithInteger(quantityOfProduct);
        });
    }

    @Test // Test-ID: ADD1
    void addEmptyArgumentToAnotherMoneyObjectsAmount() {
        Money m1 = new Money(0);
        Money expected = new Money(0);
        m1.add();

        assertEquals(expected, m1);
    }

    @Test // Test-ID: ADD2
    void addTheValueOfZeroMoneyObjectToAnotherMoneyObjectsAmount() {
        Money m1 = new Money(0);
        Money zero = new Money(0);
        Money expected = new Money(0);
        m1.add(zero);

        assertEquals(expected, m1);
    }

    @Test // Test-ID: ADD3
    void addTheValueOfOneMoneyObjectToAnotherMoneyObjectsAmount() {
        Money m1 = new Money(0);
        Money m2 = new Money(100);
        Money expectedValue = new Money(100);
        assertEquals(expectedValue, m1.add(m2));
    }

    @Test // Test-ID: ADD4
    void addTheValueOfManyMoneyObjectToAnotherMoneyObjectsAmount() {
        Money sum = new Money(0);
        Money m1 = new Money(100);
        Money m2 = new Money(200);
        Money m3 = new Money(300);
        Money m4 = new Money(399.99);
        Money expectedValue = new Money(999.99);
        assertEquals(expectedValue, sum.add(m1, m2, m3, m4));
    }

    @Test
    void addTheValueOfNegativeMoneyObjectToAnotherMoneyObjectsAmount() {
        assertThrows(NumberFormatException.class, () -> {
            Money m1 = new Money(0);
            Money negative = new Money(-100);
            m1.add(negative);
        });
    }

    @Test
    void addNullToAMoneyObjectsAmount() {
        Money m1 = new Money(0);
        assertThrows(NullPointerException.class, () -> {
            m1.add(null);
        });
    }

    @Test
    void checkThatWhenAddingAMoneyObjectThatIsOfNegativeValueShouldThrowNumberFormatException(){
        assertThrows(NumberFormatException.class, () -> {
            Money priceOne = new Money(1.99);
            Money priceTwo = new Money(-1.99);
            Money priceThree = new Money(599.99);
            Money priceFour = new Money(299.99);
            Money priceFive = new Money(20);

            priceOne.add(priceTwo, priceThree, priceFour, priceFive);
        });
    }

    @Test
    void shouldThrowExceptionForDivisionInputIsLowerThanOne() {
        assertThrows(IllegalArgumentException.class, () -> {
            Money value = new Money(5);
            value.divideWithInteger(0);
        });
    }

    @Test
    void divideMoneyWithIntegerShouldBeEquals() {
        Money value = new Money(199.99);
        Money afterCalcValue = value.divideWithInteger(25);
        Money expectedValue = new Money(8);

        assertEquals(expectedValue, afterCalcValue);
    }

    @Test
    void compareTwoIdenticalMoneyObjects() {
        Money valueOne = new Money(199.99);
        Money valueOneSame = new Money(199.99);

        assertEquals(0, valueOne.compareTo(valueOneSame));
    }

    @Test
    void comparingTwoDifferentMoneyObjects() {
        Money valueOne = new Money(199.90);
        Money valueTwo = new Money(199.91);

        assertThat(valueOne.compareTo(valueTwo), lessThan(0));
    }

    @Test
    void compareSmallerMoneyValueWithBiggerShouldBeEqualsToOne() {
        Money valueOne = new Money(1.00);
        Money valueTwo = new Money(1.01);

        assertThat(valueTwo.compareTo(valueOne), greaterThan(0));
    }

    @Test
    public void checkThatEqualsWorkDueToRoundingSolutionToNumbers() {
        //Money klass kommer att använda roundingMode för att göra om 10 --> 10.00 Därav kommer de ha samma värde
        Money valueOne = new Money(10);
        Money sameValueAsOne = new Money(10.00);

        assertThat(valueOne, is(equalTo(sameValueAsOne)));

    }

    @Test
    public void createMoneyDoubleValueWithManyDecimalShouldBeRounded() {
        Money decimalValue = new Money(0.126468185154);
        Money expectedValueAfterRounding = new Money(0.13);

        assertEquals(decimalValue, expectedValueAfterRounding);
    }

    @Test
    public void createMoneyDoubleValueWithDecimalButShouldNotBeEquals() {
        Money decimalValue = new Money(0.126468185154);
        Money notExpectingValue = new Money(0.12);

        assertNotEquals(decimalValue, notExpectingValue);
    }

    @Test
    public void subtractMoneyObjectsShouldThrowExceptionDueToNegativeResult() {
        assertThrows(NumberFormatException.class,() -> {
            Money two = new Money(2);
            Money one = new Money(1);

            Money difference = one.subtract(two);
        });
    }

    @Test
    public void subtractMoneyObjectAndResultShouldBeEquals() {
        Money priceOne = new Money(1181961);
        Money priceTwo = new Money(199.99);
        Money priceThree = new Money(599.99);
        Money priceFour = new Money(299.99);
        Money priceFive = new Money(20);

        Money difference = priceOne.subtract(priceTwo, priceThree, priceFour, priceFive);
        Money expectedValue = new Money(1180841.03);

        assertEquals(expectedValue, difference);
    }

    @Test
    public void insertIncorrectExpectingValueInSubtract() {
        Money priceOne = new Money(1181961);
        Money priceTwo = new Money(199.99);
        Money priceThree = new Money(599.99);
        Money priceFour = new Money(299.99);
        Money priceFive = new Money(20);

        Money difference = priceOne.subtract(priceTwo, priceThree, priceFour, priceFive);
        Money expectedValue = new Money(1180841.02);

        assertNotEquals(expectedValue, difference);
    }


}
