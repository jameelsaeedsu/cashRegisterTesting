package se.su.dsv.grupp03;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class OrderLineTest {

    public static Manufacturer mockManufacturer = new Manufacturer("Generic Man", "Man Contact");
    public static Supplier mockSupplier = new Supplier("Generic Sup", "Sup Contact");
    public static Category mockCategory = new Category("Generic Cat");
    public static Product mockProduct = new Product("Generic Pro", new Money(15), mockManufacturer, mockSupplier, mockCategory);
    public static Order mockOrder = new Order();

    @Test
    void validOrderLineGetters(){
        int quantity = 1;
        OrderLine orderLine = new OrderLine(mockOrder, mockProduct, quantity);
        assertEquals(mockOrder, orderLine.getOrder());
        assertEquals(mockProduct, orderLine.getProduct());
        assertEquals(quantity, orderLine.getQuantity());
    }

    @Test
    void setOrderLineValidQuantity(){
        int firstQuantity = 1;
        int secondQuantity = 2;
        int thirdQuantity = 3;
        OrderLine orderLine = new OrderLine(mockOrder, mockProduct, firstQuantity);
        assertEquals(firstQuantity, orderLine.getQuantity());
        orderLine.setQuantity(secondQuantity);
        assertEquals(secondQuantity, orderLine.getQuantity());
        orderLine.setQuantity(thirdQuantity);
        assertEquals(thirdQuantity, orderLine.getQuantity());
    }

    @Test
    void setOrderLineInvalidQuantityReturnsIllegalArgumentException(){
        OrderLine orderLine = new OrderLine(mockOrder, mockProduct, 1);
        assertThrows(IllegalArgumentException.class, () -> orderLine.setQuantity(-1));
    }

    @Test
    void throwsIllegalArgumentExceptionIfQuantityIsInvalid(){
        int[] invalidQuantities = {0, -1};
        for(int i : invalidQuantities)
            assertThrows(IllegalArgumentException.class, () -> new OrderLine(mockOrder, mockProduct, i));
    }

    @Test
    void assertEqualsMethodForOrderLineIsCorrect(){
        // OrderLine will consider Order ID and Product ID to see if equal.
        OrderLine one = new OrderLine(mockOrder, mockProduct, 1);
        OrderLine two = new OrderLine(mockOrder, mockProduct, 2);
        assertEquals(one, one);
        assertEquals(one, two);
    }

    @Test
    void orderLineGivenNullAsOrderReturnsFalse(){
        assertThrows(NullPointerException.class, () -> new OrderLine(null, mockProduct, 1));
    }

    @Test
    void orderLineGivenNullAsProductReturnsFalse(){
        assertThrows(NullPointerException.class, () -> new OrderLine(mockOrder, null, 1));
    }

    @Test
    void orderLineNotEqualsToNullReturnsFalse(){
        OrderLine orderLine = new OrderLine(mockOrder, mockProduct, 1);
        assertFalse(orderLine.equals(null));
    }

    @Test
    void orderLineNotEqualsToOtherKlassReturnsFalse(){
        OrderLine orderLine = new OrderLine(mockOrder, mockProduct, 1);
        assertFalse(orderLine.equals(new Order()));
    }

    @Test
    void hashForOrderLineIs(){
        OrderLine orderLine = new OrderLine(mockOrder, mockProduct, 1);
        int hashedOrderAndProduct = Objects.hash(mockOrder.getId(), mockProduct.getId());
        assertEquals(hashedOrderAndProduct, orderLine.hashCode());
    }

}
