package se.su.dsv.grupp03;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SupplierTest {

    @Test
    public void createSupplierWithEmptyNames() {
        assertThrows(IllegalArgumentException.class, () -> new Supplier("", ""));
    }

    @Test
    public void createSupplierCheckName() {
        Supplier supplier = new Supplier("TechData", "Anders Andersson");
        assertEquals("TechData", supplier.getName());
    }

    @Test
    public void createSupplierCheckContactPerson() {
        Supplier supplier = new Supplier("TechData", "Anders Andersson");
        assertEquals("Anders Andersson", supplier.getContactPerson());
    }

    @Test
    public void checkIfNameIsBlank() {
        assertThrows(IllegalArgumentException.class, () -> new Manufacturer("", "Anders Andersson"));
    }

    @Test
    public void checkIfContactPersonIsBlank() {
        assertThrows(IllegalArgumentException.class, () -> new Manufacturer("TechData", ""));
    }

    @Test
    public void testPrint() {
        Supplier supplier = new Supplier("TechData", "Anders Andersson");
        assertEquals(supplier.getName(), supplier.toString());
    }


}
