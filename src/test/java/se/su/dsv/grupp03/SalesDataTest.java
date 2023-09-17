package se.su.dsv.grupp03;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

public class SalesDataTest {

     private final Money[] salesDataArr = {new Money(199.99), new Money(299.99),
     new Money(99.99), new Money(55), new Money(285.70), new Money(552),
     new Money(685.90), new Money(1999.99), new Money(458.65)};

     @Test
     public void controlThatArrayMoneyItems() {
         Money[] expectedArr = {new Money(458.65), new Money(299.99), new Money(1999.99),
                 new Money(99.99), new Money(55), new Money(285.70), new Money(552),
                 new Money(685.90), new Money(199.99) };

         assertThat(salesDataArr, arrayWithSize(9));
         assertThat(salesDataArr, hasItemInArray(greaterThan(new Money(1999))));
         assertThat(salesDataArr, hasItemInArray(lessThanOrEqualTo(new Money(1999.99))));
         assertThat(salesDataArr, arrayContainingInAnyOrder(expectedArr));
         assertThat(salesDataArr, hasItemInArray(new Money(1999.99)));
     }

    @Test
    public void checkGetTotalValueExVatIsEquals() {
        SalesData data = new SalesData(salesDataArr);
        Money totalRevenueExcludingVat = data.getTotalRevenueVatExcluded();
        Money expectedTotalSales = new Money(4637.21);

        assertEquals(expectedTotalSales, totalRevenueExcludingVat);
    }

    @Test
    public void checkThatTotalRevenueIncVatIsCorrect() {
        SalesData data = new SalesData(salesDataArr);
        Money totalRevenueIncludedVat = data.getTotalRevenueVatIncluded();
        Money manuallyCalculatedVat = new Money(1159.3025);

        Money expectedTotalRevenueVatIncluded = data.getTotalRevenueVatExcluded().add(manuallyCalculatedVat);

        assertEquals(expectedTotalRevenueVatIncluded, totalRevenueIncludedVat);
    }

    @Test
    public void insertWrongExpectedValueShouldBeNotEquals() {
        SalesData data = new SalesData(salesDataArr);
        Money totalRevenueIncludedVat = data.getTotalRevenueVatIncluded();
        Money manuallyCalculatedVat = new Money(1159.306);

        Money expectedTotalRevenueVatIncluded = data.getTotalRevenueVatExcluded().add(manuallyCalculatedVat);

        assertNotEquals(expectedTotalRevenueVatIncluded, totalRevenueIncludedVat);
    }

    @Test
    public void checkThatTotalVatIsEquals() {
        SalesData data = new SalesData(salesDataArr);
        Money actualVatValue = data.getTotalVat();
        Money expectingValue = new Money(1159.3025);

        assertEquals(actualVatValue, expectingValue);
    }


    @Test
    public void shouldThrowExceptionDueToArrayEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new SalesData(new Money[0]));
    }

    @Test
    public void shouldThrowExceptionDueToArrayNull() {
        assertThrows(IllegalArgumentException.class, () -> new SalesData(new Money[Integer.parseInt(null)]));
    }

    @Test
    public void checkHighestValueIsCorrect() {
        SalesData data = new SalesData(salesDataArr);
        Money expectedHighestAmount = new Money(1999.99);
        Money actualHighestAmount = data.getHighestAmount();

        assertEquals(expectedHighestAmount, actualHighestAmount);
    }

    @Test
    public void insertIncorrectExpectedValueInGetHighest() {
        SalesData data = new SalesData(salesDataArr);
        Money expectedHighestAmount = new Money(1999.98);
        Money actualHighestAmount = data.getHighestAmount();

        assertNotEquals(expectedHighestAmount, actualHighestAmount);
    }

    @Test
    public void checkLowestValueIsCorrect() {
        SalesData data = new SalesData(salesDataArr);
        Money expectedLowestAmount = new Money(55);
        Money actualLowestAmount = data.getLowestAmount();

        assertEquals(expectedLowestAmount, actualLowestAmount);
    }

    @Test
    public void insertIncorrectInputAndControlAgainstLowestValue() {
        SalesData data = new SalesData(salesDataArr);
        Money notExceptingValue = new Money(0);
        Money actualLowestValue = data.getLowestAmount();

        assertNotEquals(notExceptingValue, actualLowestValue);
    }

    @Test
    public void checkThatAverageSumIsCorrect() {
        SalesData data = new SalesData(salesDataArr);
        Money expectedAverageValue = new Money(515.25);
        Money actualAverageValue = data.getAverage();

        assertEquals(expectedAverageValue, actualAverageValue);
    }

    @Test
    public void checkThatInputIsNotEqualToAverage() {
        SalesData data = new SalesData(salesDataArr);
        Money expectedAverageValue = new Money(515.24);
        Money actualAverageValue = data.getAverage();

        assertNotEquals(expectedAverageValue, actualAverageValue);
    }

    @Test
    public void checkThatMedianValueIsCorrectWhenLengthIsOdd() {
        SalesData data = new SalesData(salesDataArr);
        Money expectedMedianValue = new Money(299.99);
        Money actualMedianValue = data.getMedian();

        assertEquals(expectedMedianValue, actualMedianValue);
    }

    @Test
    public void checkThatExpectedMedianValueIsIncorrect() {
        SalesData data = new SalesData(salesDataArr);
        Money notExpectingValue = new Money(99.99);
        Money actualMedianValue = data.getMedian();

        assertNotEquals(notExpectingValue, actualMedianValue);
    }

    @Test
    public void checkMedianValueInArrayWithEvenLength() {
        Money[] temp = new Money[salesDataArr.length + 1];
        for(int i = 0; i < salesDataArr.length; i++)
            temp[i] = salesDataArr[i];
        temp[salesDataArr.length] = new Money(2500);

        SalesData salesData = new SalesData(temp);

        Money expectedMedianValue = new Money(379.32);
        Money actualMedianValue = salesData.getMedian();

        assertEquals(expectedMedianValue, actualMedianValue);

    }

    @Test
    public void checkMedianValueInArrayWithEvenLengthIsNotEqualToIncorrectValue() {
        Money[] temp = new Money[salesDataArr.length + 1];
        for(int i = 0; i < salesDataArr.length; i++)
            temp[i] = salesDataArr[i];
        temp[salesDataArr.length] = new Money(2500);

        SalesData data = new SalesData(temp);

        Money notExpectingValue = new Money(379.33);
        Money actualMedianValue = data.getMedian();

        assertNotEquals(notExpectingValue, actualMedianValue);
    }

}
