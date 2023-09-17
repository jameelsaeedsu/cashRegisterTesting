package se.su.dsv.grupp03;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

public class Receipt {
    private static int globalReceiptNumbers = 1000;
    private final int receiptNumber;
    private Money totalSum;
    private Money totalVat;
    private final Order order;
    private final LocalDate localDate;
    private final LocalTime localTime;

    /*Det går att skapa ett kvitto från en tom order.
    * Det vill säga, en order utan orderLines*/

    public Receipt(Order order) {
        if (order == null)
            throw new IllegalArgumentException();
        this.order = order;
        receiptNumber = globalReceiptNumbers++;
        localDate = LocalDate.now();
        localTime = LocalTime.now();
        totalSum = order.getOrderTotal();
        totalVat = calcVat();

    }

    public void update(){
        this.totalSum = order.getOrderTotal();
        this.totalVat = calcVat();
    }

    public Money getTotalVat() {
        return totalVat;
    }

    public Money getTotalSum() {
        return totalSum;
    }

    public int getReceiptNumber() {
        return receiptNumber;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public Money calcVat() {
        Money total = new Money(0);
        Set<OrderLine> orderLines = order.getLines();
        for (OrderLine line : orderLines)
            total = total.add(line.getProduct().getVat().multiplyWithInteger(line.getQuantity()));
        return total;
    }
}
