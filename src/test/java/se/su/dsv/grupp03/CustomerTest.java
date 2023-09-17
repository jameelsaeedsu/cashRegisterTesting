package se.su.dsv.grupp03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

    @Test
    public void nextIdIncrementedByOneEachTimeANewCustomerIsConstructed() {
        Customer[] arr = new Customer[5];
        for (int i = 0; i < 5; i++)
            arr[i] = new Customer("Generic Name");
        for (int i = 1; i < 5; i++)
            assertEquals(arr[i - 1].getId(), arr[i].getId() - 1);
    }

    @Test
    public void getNameDoesNotReturnNull() {
        Customer customer = new Customer("Generic Name");
        assertNotNull(customer.getName());
    }

    @Test
    public void controlGetNameReturnsExpectedName() {
        Customer customer = new Customer("Fredrik Rodriguez");
        assertEquals("Fredrik Rodriguez", customer.getName());
    }

    @Test
    public void controlThatCustomerNameIsNotEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Customer(""));
    }

    @Test
    public void enterCustomerNameWithBlanksShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Customer("         "));
    }

    @Test
    public void checkThatEnteredCustomerNameWithWhiteSpaceIsNormalized() {
        Customer customer = new Customer("Jameel     Saeed");

        assertEquals("Jameel Saeed", customer.getName());
    }

}