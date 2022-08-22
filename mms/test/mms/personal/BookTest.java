package mms.personal;

import org.junit.Test;

import static org.junit.Assert.*;

public class BookTest {

    @Test(expected = IllegalArgumentException.class)
    public void constructorThrowsErrorForNullTitle() {
        Book book = new Book("Jonas", null, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorThrowsErrorForEmptyTitle() {
        Book book = new Book("Jonas", "", true);

    }

    @Test
    public void bookHasCorrectDimensions() {
        Book book = new Book("Test", "TestBook", true);
        assertTrue(book.getWidth() == 20);
        assertTrue(book.getHeight() == 20);
        assertTrue(book.getLength() == 5);
    }

    @Test
    public void getTitle() {
        Book book = new Book("Test", "TestBook", true);
        assertTrue(book.getTitle() == "TestBook");
    }

    @Test
    public void testToStringFiction() {
        Book book = new Book("Test", "TestBook", true);
        assertTrue(book.toString().equals("Book (Test) Title: TestBook (Fiction)"));
    }

    @Test
    public void testToStringNonFiction() {
        Book book = new Book("Test", "TestBook", false);
        assertTrue(book.toString().equals("Book (Test) Title: TestBook (Non-Fiction)"));
    }

    @Test
    public void getVolume() {
        Book book = new Book("Test", "TestBook", true);
        assertEquals(book.getVolume(), 20 * 20 * 5, 0.1);

    }
}