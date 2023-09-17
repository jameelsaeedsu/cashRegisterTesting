package se.su.dsv.grupp03;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ReceiptTest {
    private static final int [] receiptId = {1000, 1001, 1002};


    @BeforeAll
    @Test
    static void testId(){
        ArrayList<Receipt> testIdList = new ArrayList<>();
        Order tempOrder = new Order();

        testIdList.add(new Receipt(tempOrder));
        testIdList.add(new Receipt(tempOrder));
        testIdList.add(new Receipt(tempOrder));
        assertEquals(receiptId[0], testIdList.get(0).getReceiptNumber());
        assertEquals(receiptId[1], testIdList.get(1).getReceiptNumber());
        assertEquals(receiptId[2], testIdList.get(2).getReceiptNumber());
    }


    @Test
    void testCreate(){
        Receipt receipt = new Receipt(new Order());
        assertNotNull(receipt);
    }

    @Test
    void testCreateWithNull(){
        assertThrows(IllegalArgumentException.class, () -> new Receipt(null));
    }

    @Test
    void testDate(){
        Receipt receipt = new Receipt(new Order());
        LocalDate localDate = LocalDate.now();
        assertEquals(localDate, receipt.getLocalDate());
    }

    @Test
    void testTime(){
        Receipt receipt = new Receipt(new Order());
        LocalTime localTime = LocalTime.now();
        assertEquals(localTime.getHour(), receipt.getLocalTime().getHour());
        assertEquals(localTime.getMinute(), receipt.getLocalTime().getMinute());
        assertEquals(localTime.getSecond(), receipt.getLocalTime().getSecond());
    }

    @Test
    void testTotalWithNoProducts(){
        Order order = new Order();
        Receipt receipt = new Receipt(order);
        assertEquals(new Money(0), receipt.getTotalSum());
    }

    @Test
    void testVatWithNoProducts(){
        Order order = new Order();
        Receipt receipt = new Receipt(order);
        assertEquals(new Money(0), receipt.getTotalVat());
    }

    @Test
    void testTotalSumWithOneProduct(){
        Product product = new Product("Temp", new Money(100), new Manufacturer("Dell", "Stellan"), new Supplier("Tech Data", "Adam"), new Category("Laptop"));
        Order order = new Order();
        OrderLine orderLine = new OrderLine(order, product, 1);
        order.add(orderLine);
        Receipt receipt = new Receipt(order);
        assertEquals(receipt.getTotalSum(), order.getOrderTotal());
    }

    @Test
    void testTotalVatWIthOneProduct(){
        Product product = new Product("SB300-X2", new Money(100), new Manufacturer("Dell", "Stellan"), new Supplier("Tech Data", "Adam"), new Category("Laptop"));
        Order order = new Order();
        OrderLine orderLine = new OrderLine(order, product, 1);
        order.add(orderLine);
        Receipt receipt = new Receipt(order);
        assertEquals(new Money(25), receipt.getTotalVat());
    }

    @Test
    void testTotalVatWIthFourProduct(){
        Product product = new Product("SB300-X2", new Money(100), new Manufacturer("Dell", "Stellan"), new Supplier("Tech Data", "Adam"), new Category("Laptop"));
        Product product2 = new Product("SB300-X3", new Money(100), new Manufacturer("Sony", "Stellan"), new Supplier("Tech Data", "Adam"), new Category("HeadPhone"));
        Product product3 = new Product("SB300-X4", new Money(100), new Manufacturer("Dell", "Stellan"), new Supplier("Tech Data", "Adam"), new Category("Laptop"));

        Order order = new Order();
        OrderLine orderLine = new OrderLine(order, product, 1);
        OrderLine orderLine2 = new OrderLine(order, product2, 2);
        OrderLine orderLine3 = new OrderLine(order, product3, 1);
        order.add(orderLine);
        order.add(orderLine2);
        order.add(orderLine3);
        Receipt receipt = new Receipt(order);
        assertEquals(new Money(100), receipt.getTotalVat());
    }

    @Test
    void testTotalSumAfterUpdate(){
        Product product = new Product("SB300-X2", new Money(100), new Manufacturer("Dell", "Stellan"), new Supplier("Tech Data", "Adam"), new Category("Laptop"));
        Product product2 = new Product("SB300-X3", new Money(100), new Manufacturer("Sony", "Stellan"), new Supplier("Tech Data", "Adam"), new Category("HeadPhone"));
        Order order = new Order();
        order.add(new OrderLine(order, product, 1));
        Receipt receipt = new Receipt(order);
        order.add(new OrderLine(order, product2, 1));
        receipt.update();
        assertEquals(new Money(200), receipt.getTotalSum());
    }

    @Test
    void testTotalVatAfterUpdateAddedProduct(){
        Product product = new Product("SB300-X2", new Money(100), new Manufacturer("Dell", "Stellan"), new Supplier("Tech Data", "Adam"), new Category("Laptop"));
        Product product2 = new Product("SB300-X3", new Money(100), new Manufacturer("Sony", "Stellan"), new Supplier("Tech Data", "Adam"), new Category("HeadPhone"));
        Order order = new Order();
        order.add(new OrderLine(order, product, 1));
        Receipt receipt = new Receipt(order);
        order.add(new OrderLine(order, product2, 1));
        receipt.update();
        assertEquals(new Money(50), receipt.getTotalVat());
    }

    @Test
    void testTotalVatAfterUpdateRemovedProduct(){
        Product product = new Product("SB300-X2", new Money(100), new Manufacturer("Dell", "Stellan"), new Supplier("Tech Data", "Adam"), new Category("Laptop"));
        Product product2 = new Product("SB300-X3", new Money(100), new Manufacturer("Sony", "Stellan"), new Supplier("Tech Data", "Adam"), new Category("HeadPhone"));
        Order order = new Order();
        OrderLine orderLine = new OrderLine(order, product, 1);
        OrderLine orderLine2 = new OrderLine(order, product2, 1);
        order.add(orderLine);
        order.add(orderLine2);
        Receipt receipt = new Receipt(order);
        order.remove(orderLine);
        receipt.update();
        assertEquals(new Money(25), receipt.getTotalVat());
    }


}
