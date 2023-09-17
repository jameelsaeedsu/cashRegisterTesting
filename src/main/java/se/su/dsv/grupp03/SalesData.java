package se.su.dsv.grupp03;

import java.math.BigDecimal;
import java.util.Arrays;

public class SalesData {

    private Money[] salesData;

    public SalesData(Money[] sales) {
        if(sales == null || sales.length == 0)
            throw new IllegalArgumentException();
        this.salesData = new Money[sales.length];

        for(int i = 0; i < sales.length; i++)
            salesData[i] = sales[i];
    }

    public Money getTotalRevenueVatExcluded() {
        Money totalRevenueVatExcluded = new Money(0);

        for (Money money : salesData)
            totalRevenueVatExcluded = totalRevenueVatExcluded.add(money);

        return totalRevenueVatExcluded;
    }

    public Money getTotalRevenueVatIncluded() {
        Money totalRevenueVatIncluded = new Money(0);
        BigDecimal vatInBigDecimal = new BigDecimal(0);
        BigDecimal vatPercentage = EnumVat.TWENTY_FIVE_PERCENT.getInBigDecimal();

        for(Money money : salesData)
            vatInBigDecimal = vatInBigDecimal.add(money.getAmount().multiply(vatPercentage));
        Money totalSumOfVat = new Money(Double.parseDouble(vatInBigDecimal.toString()));

        totalRevenueVatIncluded = totalRevenueVatIncluded.add(getTotalRevenueVatExcluded().add(totalSumOfVat));

        return totalRevenueVatIncluded;
    }

    public Money getTotalVat() {
        return getTotalRevenueVatIncluded().subtract(getTotalRevenueVatExcluded());
    }

    public Money getHighestAmount() {
        Money highestAmount = salesData[0];

        for(int i = 1; i < salesData.length; i++){
            if(salesData[i].getAmount().doubleValue() > highestAmount.getAmount().doubleValue())
                highestAmount = salesData[i];
        }
        return highestAmount;
    }

    public Money getLowestAmount() {
        Money lowestAmount = salesData[0];

        for(int i = 1; i < salesData.length; i++){
            if(salesData[i].getAmount().doubleValue() < lowestAmount.getAmount().doubleValue())
                lowestAmount = salesData[i];
        }
        return lowestAmount;
    }

    public Money getAverage() {
        return getTotalRevenueVatExcluded().divideWithInteger(salesData.length);
    }

    public Money getMedian() {
        Money medianValue = new Money(0);
        Arrays.sort(salesData);
        int arrayLength = salesData.length;
        if(arrayLength % 2 == 1)
            medianValue = medianValue.add(salesData[((arrayLength + 1) / 2) - 1]);
        else {
            medianValue = salesData[arrayLength/2].add(salesData[arrayLength/2 - 1]).divideWithInteger(2);
        }
        return medianValue;
    }
}
