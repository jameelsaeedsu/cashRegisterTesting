package se.su.dsv.grupp03;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ManufacturerTest {

    @Test
    public void createManufacturerCheckName() {
        Manufacturer manufacturer = new Manufacturer("Dell", "Sara Sarasson");
        assertEquals("Dell", manufacturer.getName());
    }


    @Test
    public void createManufacturerCheckContactPerson() {
        Manufacturer manufacturer = new Manufacturer("Dell", "Sara Sarasson");
        assertEquals("Sara Sarasson", manufacturer.getContactPerson());
    }

    @Test
    public void checkIfNameIsBlank() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Manufacturer("", "Sara Sarasson");
        });
    }

    @Test
    public void checkIfContactPersonIsBlank() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Manufacturer("Dell", "");
        });
    }

    @Test
    public void testPrint() {
        Manufacturer manufacturer = new Manufacturer("Dell", "Sara Sarasson");
        assertEquals(manufacturer.getName(), manufacturer.toString());
    }

}
