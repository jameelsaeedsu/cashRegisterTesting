package se.su.dsv.grupp03;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private static final Order order = new Order();
    private static final Manufacturer mockManufacturer = new Manufacturer("Generic Man", "Man Contact");
    private static final Supplier mockSupplier = new Supplier("Generic Sup", "Sup Contact");
    private static final Category mockCategory = new Category("Generic Cat");
    private static final Product mockProduct1 = new Product("Product One", new Money(5), mockManufacturer, mockSupplier, mockCategory);
    private static final Product mockProduct2 = new Product("Product Two",new Money(10), mockManufacturer, mockSupplier, mockCategory);
    private static final Product mockProduct3 = new Product("Product Three", new Money(20), mockManufacturer, mockSupplier, mockCategory);
    private static final OrderLine orderLine1 = new OrderLine(order, mockProduct1, 4);
    private static final OrderLine orderLine2 = new OrderLine(order, mockProduct2, 2);
    private static final OrderLine orderLine3 = new OrderLine(order, mockProduct3, 1);

    @Test
    public void nextIdIncrementedByOneEachTimeANewOrderIsConstructed() {
        Order[] arr = new Order[5];
        for (int i = 0; i < 5; i++)
            arr[i] = new Order();
        for (int i = 1; i < 5; i++)
            assertEquals(arr[i - 1].getId(), arr[i].getId() - 1);
    }

    @Test
    void hashOrder(){
        Order order = new Order();
        assertEquals(Objects.hash(order.getId()), order.hashCode());
    }

    @Test
    public void setOrderLinesForOrder(){
        order.add(orderLine1);
        order.add(orderLine2);
        order.add(orderLine3);
        assertEquals(3, order.size());
        assertTrue(order.contains(orderLine1));
        assertTrue(order.contains(orderLine2));
        assertTrue(order.contains(orderLine3));
    }

    @Test
    public void numberOfOrderLinesInOrder(){
        order.add(orderLine1);
        order.add(orderLine2);
        order.add(orderLine3);
        assertEquals(3, order.size());
    }

    @Test
    public void removeOrderLineFromOrder(){
        // Add orderLine1 and verify that it was indeed added.
        order.add(orderLine1);
        assertTrue(order.contains(orderLine1));
        assertEquals(1, order.size());
        // Remove orderLine1 and verify that it was indeed removed.
        order.remove(orderLine1);
        assertFalse(order.contains(orderLine1));
        assertTrue(order.isEmpty());
    }

    @Test
    public void checkThatOrderTotalIsCorrect() {
        order.add(orderLine1);
        order.add(orderLine2);
        order.add(orderLine3);
        assertEquals(new Money(60), order.getOrderTotal());
    }

    @Test
    public void insertIncorrectExpectedValueAndCompareToOrderTotal() {
        order.add(orderLine1);
        order.add(orderLine2);
        order.add(orderLine3);

        assertNotEquals(new Money(59), order.getOrderTotal());
    }
}