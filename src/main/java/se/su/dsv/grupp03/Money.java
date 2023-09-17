package se.su.dsv.grupp03;
//Fowler's Money pattern

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Locale;
import java.util.Objects;

public class Money implements Comparable<Money> {

    // Accessing the class Currency to get the currency of Sweden
    private static final Currency swedishCurrency = Currency.getInstance(new Locale("en", "SE"));
    //A default RoundingMode class, that's rounds all the Money objects that are creating to half even
    private static final RoundingMode default_rounding = RoundingMode.HALF_EVEN;

    //Using Big Decimal to handle precision
    private final BigDecimal amount;
    private final Currency currency;

    //Constructor is private and cannot be accessed until swedishCrowns is used. In this way money can't be manipulated
    public Money(double amountInDouble) {
        if(amountInDouble < 0)
            throw new NumberFormatException("Money cannot be negative");
        BigDecimal amountInBigDecimal = new BigDecimal(amountInDouble);
        this.currency = swedishCurrency;
        this.amount = amountInBigDecimal.setScale(currency.getDefaultFractionDigits(), default_rounding);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    /*
    Works differently as to equals() for BigDecimals and the reason is that the amount is rounded to two decimal points
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equals(amount, money.amount);
    }
    /*
    Takes in a quantity and then converts to BigDecimal and multiples with the price of the product
    and returns the amount ' quantity as a Money object
    */
    public Money multiplyWithInteger(int quantityOfProduct) {
        if(quantityOfProduct < 1)
            throw new IllegalArgumentException("Quantity should never be lower than 1");
        BigDecimal quantityOfProductInBigDecimal = new BigDecimal(quantityOfProduct);
        BigDecimal calculation = amount.multiply(quantityOfProductInBigDecimal);
        return new Money(Double.parseDouble(calculation.toString()));
    }
    /*
    A BigDecimal sum that has the products price in the toString() from BigDecimal
    A for-loop that looks through every money object that is added and returns their amount and
    adds it to tht totalSum and finally returns ir as a Money object.
     */

    public Money add(Money... moneyObjects) {
        if(moneyObjects == null)
            throw new NullPointerException("Null as an argument is not accepted.");

        BigDecimal totalSum = new BigDecimal(String.valueOf(amount));
        for(Money money : moneyObjects)
            totalSum = totalSum.add(money.getAmount());
        return new Money(Double.parseDouble(totalSum.toString()));
    }

    public Money subtract(Money... moneyObjects) {
        BigDecimal sumAfterCalculation = new BigDecimal(String.valueOf(amount));
        for(Money money : moneyObjects)
            sumAfterCalculation = sumAfterCalculation.subtract(money.getAmount());
        return new Money(Double.parseDouble(sumAfterCalculation.toString()));
    }

    public Money divideWithInteger(int number) {
        if(number < 1)
            throw new IllegalArgumentException("Input number cannot be lower than 1");
        BigDecimal numberInBigDecimal = new BigDecimal(number);
        BigDecimal calculation = amount.divide(numberInBigDecimal, default_rounding);
        return new Money(Double.parseDouble(calculation.toString()));
    }


    @Override
    public String toString() {
        return getAmount() + " " + getCurrency().getSymbol();
    }

    @Override
    public int compareTo(Money moneyObject) {
        return Integer.compare(this.amount.compareTo(moneyObject.getAmount()), 0);

    }

}
