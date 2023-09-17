package se.su.dsv.grupp03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CatalogTest {

    @Test
    public void testSizeIsZero() {
        Catalog catalog = new Catalog();
        assertEquals(0, catalog.getSize());
    }

    @Test
    public void testCatalogNotNull() {
        Catalog catalog = new Catalog();
        assertNotNull(catalog);
    }


    @Test
    public void testAddNullAsProduct() {
        Catalog catalog = new Catalog();
        assertThrows(IllegalArgumentException.class, () -> catalog.add(null));
    }

    @Test
    public void getProductsInCategoryThrowsExceptionIfGivenNull() {
        Catalog catalog = new Catalog();
        assertThrows(NullPointerException.class, () -> catalog.getProductsInCategory(null));
    }

    @Test
    public void testCatalogAddProduct() {
        Product product = new Product("Temp", new Money(100), new Manufacturer("Dell", "Stellan"), new Supplier("Tech Data", "Adam"), new Category("Laptop"));
        Catalog catalog = new Catalog();
        catalog.add(product);
        assertEquals(product, catalog.getProduct(product));
    }

    @Test
    public void testCatalogAddSameProduct() {
        Product product = new Product("Temp", new Money(100), new Manufacturer("Dell", "Stellan"), new Supplier("Tech Data", "Adam"), new Category("Laptop"));
        Catalog catalog = new Catalog();
        catalog.add(product);
        catalog.add(product);
        catalog.add(product);
        assertEquals(1, catalog.getSize());
        assertEquals(1, catalog.getProductsInCategory(product.getCategory()).size());
    }


    @Test
    public void testClearCatalog() {
        Product product = new Product("Temp", new Money(100), new Manufacturer("Dell", "Stellan"), new Supplier("Tech Data", "Adam"), new Category("Laptop"));
        Catalog catalog = new Catalog();
        catalog.add(product);
        catalog.deleteCatalog();
        assertTrue(catalog.isEmpty());
    }



    @Test
    public void testCatalogAddDifferentProductsSameCategory() {
        Product product = new Product("Temp", new Money(100), new Manufacturer("Dell", "Stellan"), new Supplier("Tech Data", "Adam"), new Category("Laptop"));
        Product product2 = new Product("Temp2", new Money(100), new Manufacturer("Dell", "Stellan"), new Supplier("Tech Data", "Adam"), new Category("Laptop"));
        Product product3 = new Product("Temp3", new Money(100), new Manufacturer("Dell", "Stellan"), new Supplier("Tech Data", "Adam"), new Category("Laptop"));

        Catalog catalog = new Catalog();
        catalog.add(product);
        catalog.add(product2);
        catalog.add(product3);
        assertEquals(3, catalog.getSize());
        assertEquals(3, catalog.getProductsInCategory(product.getCategory()).size());
    }

    @Test
    public void testSizeOfCatalogWhenAddingDifferentProductsDifferentCategory() {
        Product product = new Product("Temp", new Money(100), new Manufacturer("Dell", "Stellan"), new Supplier("Tech Data", "Adam"), new Category("Laptop"));
        Product product2 = new Product("Temp2", new Money(100), new Manufacturer("Dell", "Stellan"), new Supplier("Tech Data", "Adam"), new Category("HeadPhones"));
        Product product3 = new Product("Temp3",  new Money(100), new Manufacturer("Dell", "Stellan"), new Supplier("Tech Data", "Adam"), new Category("CD"));
        Product product4 = new Product("Temp3",  new Money(100), new Manufacturer("Dell", "Stellan"), new Supplier("Tech Data", "Adam"), new Category("DVD"));

        Catalog catalog = new Catalog();
        catalog.add(product);
        catalog.add(product2);
        catalog.add(product3);
        catalog.add(product4);
        assertEquals(4, catalog.getSize());
    }

    @Test
    public void testNumberOfCategoriesWhenAddingDifferentProductsDifferentCategory() {
        Product product = new Product("Temp",  new Money(100), new Manufacturer("Dell", "Stellan"), new Supplier("Tech Data", "Adam"), new Category("Laptop"));
        Product product2 = new Product("Temp2",  new Money(100), new Manufacturer("Dell", "Stellan"), new Supplier("Tech Data", "Adam"), new Category("HeadPhones"));
        Product product3 = new Product("Temp3",  new Money(100), new Manufacturer("Dell", "Stellan"), new Supplier("Tech Data", "Adam"), new Category("CD"));

        Catalog catalog = new Catalog();
        catalog.add(product);
        catalog.add(product2);
        catalog.add(product3);
        assertEquals(3, catalog.getAllCategories().size());
    }

    @Test
    public void testCheckProductInCategory() {
        Product product = new Product("Headphones0",  new Money(100), new Manufacturer("Dell", "Stellan"), new Supplier("Tech Data", "Adam"), new Category("HeadPhones"));
        Product product2 = new Product("Headphones1",  new Money(100), new Manufacturer("Dell", "Stellan"), new Supplier("Tech Data", "Adam"), new Category("HeadPhones"));
        Product product3 = new Product("Headphones2",  new Money(100), new Manufacturer("Dell", "Stellan"), new Supplier("Tech Data", "Adam"), new Category("HeadPhones"));
        Catalog catalog = new Catalog();
        catalog.add(product);
        catalog.add(product2);
        catalog.add(product3);
        assertTrue(catalog.getProductsInCategory(product.getCategory()).contains(product));
        assertTrue(catalog.getProductsInCategory(product.getCategory()).contains(product2));
        assertTrue(catalog.getProductsInCategory(product.getCategory()).contains(product3));
    }

    @Test
    public void testGetProduct(){
        Product product = new Product("Headphones",  new Money(100), new Manufacturer("Dell", "Stellan"), new Supplier("Tech Data", "Adam"), new Category("HeadPhones"));
        Catalog catalog = new Catalog();
        catalog.add(product);
        assertEquals(product, catalog.getProduct(product));
    }

    @Test
    public void testGetProductWithNull(){
        Catalog catalog = new Catalog();
        assertThrows(IllegalArgumentException.class, () -> catalog.getProduct(null));
    }

    @Test
    public void testGetProductWhenNotExistsInCatalog(){
        Product product = new Product("Headphones",  new Money(100), new Manufacturer("Dell", "Stellan"), new Supplier("Tech Data", "Adam"), new Category("HeadPhones"));
        Catalog catalog = new Catalog();
        assertThrows(IllegalArgumentException.class, () -> catalog.getProduct(product));
    }

    @Test
    public void testRemoveProductFromEmptyCatalog(){
        Product product = new Product("Headphones",  new Money(100), new Manufacturer("Dell", "Stellan"), new Supplier("Tech Data", "Adam"), new Category("HeadPhones"));
        Catalog catalog = new Catalog();
        assertThrows(IllegalArgumentException.class, () -> catalog.remove(product));
    }

    @Test
    public void testRemoveProductWhenOnlyOneProductInCatalog(){
        Product product = new Product("Headphones",  new Money(100), new Manufacturer("Dell", "Stellan"), new Supplier("Tech Data", "Adam"), new Category("HeadPhones"));
        Catalog catalog = new Catalog();
        catalog.add(product);
        assertEquals(product, catalog.getProduct(product));
        catalog.remove(product);
        assertFalse(catalog.isProductPresent(product));
    }

    @Test
    public void testRemoveProductWhenMultipleProductInCatalog(){
        Product product = new Product("Temp",  new Money(100), new Manufacturer("Dell", "Stellan"), new Supplier("Tech Data", "Adam"), new Category("Laptop"));
        Product product2 = new Product("Temp2",  new Money(100), new Manufacturer("Sony", "Stellan"), new Supplier("Big Tech", "Adam"), new Category("HeadPhones"));
        Product product3 = new Product("Temp3",  new Money(100), new Manufacturer("LG", "Stellan"), new Supplier("Tech Data", "Adam"), new Category("CD"));
        Catalog catalog = new Catalog();
        catalog.add(product);
        catalog.add(product2);
        catalog.add(product3);

        catalog.remove(product3);
        assertTrue(catalog.isProductPresent(product));
        assertTrue(catalog.isProductPresent(product2));
        assertFalse(catalog.isProductPresent(product3));
    }

    @Test
    public void testSizeWhenItemsRemoved(){
        Product product = new Product("Temp",  new Money(100), new Manufacturer("Dell", "Stellan"), new Supplier("Tech Data", "Adam"), new Category("Laptop"));
        Product product2 = new Product("Temp2",  new Money(100), new Manufacturer("Sony", "Stellan"), new Supplier("Big Tech", "Adam"), new Category("HeadPhones"));
        Product product3 = new Product("Temp3",  new Money(100), new Manufacturer("LG", "Stellan"), new Supplier("Tech Data", "Adam"), new Category("CD"));
        Catalog catalog = new Catalog();
        catalog.add(product);
        catalog.add(product2);
        catalog.add(product3);

        catalog.remove(product2);
        assertEquals(2, catalog.getSize());
    }

}
