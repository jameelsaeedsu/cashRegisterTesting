package se.su.dsv.grupp03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest {

    @Test
    public void nextIdIncrementedByOneEachTimeANewCategoryIsConstructed() {
        Category[] arr = new Category[5];
        for (int i = 0; i < 5; i++)
            arr[i] = new Category("Name-" + i);
        for (int i = 0; i < 5; i++)
            assertEquals("Name-" + i, arr[i].getName());
    }

    @Test
    public void testPhoneCasesCategoryName(){
        Category phoneCases = new Category("Phone Cases");
        assertEquals("Phone Cases", phoneCases.getName());
    }

    @Test
    public void createCategoryNameWithManyWhiteSpacesAndExpectOnlyOneWhiteSpaceBetweenWords() {
        Category samsungPhones = new Category("Samsung       Phones");
        assertEquals("Samsung Phones", samsungPhones.getName());
    }


    @Test
    public void checkIfContactPersonIsBlank() {
        assertThrows(IllegalArgumentException.class, () -> new Category(""));
    }

    @Test
    public void checkThatTwoCategoriesAreEqual() {
        Category categoryOne = new Category("HeadPhones");
        Category categoryTwo = new Category("HeadPhones");
        assertTrue(categoryOne.equals(categoryTwo) && categoryTwo.equals(categoryOne));
    }

    @Test
    public void checkThatTwoCategoriesAreNotEqual() {
        Category categoryOne = new Category("iPhone");
        Category categoryTwo = new Category("iPhones");
        assertFalse(categoryOne.equals(categoryTwo) && categoryTwo.equals(categoryOne));
    }

    @Test
    public void testHashCodeIsTheSameForTwoObjects() {
        Category categoryOne = new Category("Clothing");
        Category categoryTwo = new Category("Clothing");
        assertEquals(categoryOne.hashCode(), categoryTwo.hashCode());
    }

    @Test
    public void testHashCodeIsNotTheSameForTwoDifferentObjects() {
        Category categoryOne = new Category("Samsung");
        Category categoryTwo = new Category("Samsungs");
        assertNotEquals(categoryOne.hashCode(), categoryTwo.hashCode());
    }

    @Test
    public void returnFalseIfObjectEqualMethodIsGivenItself() {
        Category category = new Category("Test");
        assertTrue(category.equals(category));
    }

    @Test
    public void returnFalseIfObjectEqualMethodIsGivenNull() {
        Category category = new Category("Test");
        assertFalse(category.equals(null));
    }

}
