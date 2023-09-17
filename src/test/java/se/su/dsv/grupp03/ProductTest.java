package se.su.dsv.grupp03;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


public class ProductTest {
    public static Manufacturer mockManufac = new Manufacturer("Dell", "Anders Andersson");
    public static Supplier mockSupplier = new Supplier("TechData", "Sara Sarasson");
    public static Category mockCategory = new Category("Headphones");


    private static final Product[] products = new Product[10];

    @BeforeAll
    static void checkId() {
        products[0] = (new Product("Xyz", new Money(100), mockManufac, mockSupplier, mockCategory));
        for (int i = 1; i < 10; i++)
            products[i] = (new Product("Xyz", new Money(100), mockManufac, mockSupplier, mockCategory));
        int id = products[0].getId();
        for (Product product : products) {
            assertEquals(id, product.getId());
            id++;
        }
    }

    @Test
    public void createProductWithEmptyName() {
            assertThrows(IllegalArgumentException.class, () -> new Product("", new Money(100), new Manufacturer("X", "Y"), new Supplier("X", "Y"), new Category("X")));
        }

    @Test
    public void createProductWithNegativePrice() {
        assertThrows(NumberFormatException.class, () -> new Product("Xyz", new Money(-1),mockManufac, mockSupplier, mockCategory));
    }

    @Test
    public void createProductWithNullManufacturer() {
        assertThrows(NullPointerException.class, () -> new Product("Xyz", new Money(100), null, mockSupplier, mockCategory));
    }

    @Test
    public void createProductWithNullSupplier() {
        assertThrows(NullPointerException.class, () -> new Product("Xyz", new Money(100),mockManufac, null, mockCategory));

    }

    @Test
    public void createProductWithNullCategory() {
        assertThrows(NullPointerException.class, () -> new Product("Xyz", new Money(100), mockManufac, mockSupplier, null));

    }

    @Test
    public void checkCorrectName() {
         Product product = new Product("Xyz", new Money(100), mockManufac, mockSupplier, mockCategory);
         assertEquals("Xyz", product.getName());


    }

    @Test
    public void checkCorrectPrice() {
        Product product = new Product("Xyz", new Money(100), mockManufac, mockSupplier, mockCategory);
        Money expectedMoneyValue = new Money(100);
        assertEquals(expectedMoneyValue, product.getPrice());

    }

    @Test
    public void checkCorrectManufac() {
        Product product = new Product("Xyz", new Money(144), mockManufac, mockSupplier, mockCategory);
        assertEquals(mockManufac, product.getManufacturer());

    }

    @Test
    public void checkCorrectSupplier() {
        Product product = new Product("Xyz", new Money(144), mockManufac, mockSupplier, mockCategory);
        assertEquals(mockSupplier, product.getSupplier());

    }

    @Test
    public void checkCorrectCategory() {
        Product product = new Product("Xyz", new Money(144), mockManufac, mockSupplier, mockCategory);
        assertEquals(mockCategory.getName(), product.getCategory().getName());

    }

    @Test
    public void checkPricePlusVatIsCorrect() {
        Product product = new Product("Xyz", new Money(199.99), mockManufac, mockSupplier, mockCategory);
        Money expectedPricePlusVAT = new Money(249.99);

        Money actualPriceAfterVAT = product.getPricePlusVAT();

        assertEquals(expectedPricePlusVAT, actualPriceAfterVAT);
    }

    @Test
    public void checkThatGetVatReturnsCorrect() {
        Product product = new Product("Shirt", new Money(256.50), mockManufac, mockSupplier, mockCategory);
        Money expectedVat = new Money(64.12);
        Money productVat = product.getVat();

        assertEquals(expectedVat, productVat);
    }

    @Test
    public void checkThatGetVatReturnsCorrectWithBiggerValue() {
        Product product = new Product("Shirt", new Money(12564.99), mockManufac, mockSupplier, mockCategory);
        Money expectedVat = new Money(3141.25);
        Money productVat = product.getVat();

        assertEquals(expectedVat, productVat);
    }

}
