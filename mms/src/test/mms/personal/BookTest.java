package mms.personal;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.*;

public class BookTest {

    @Test
    public void constructorThrowsErrorForNullTitle() {
        assertThrows(IllegalArgumentException.class, () -> {
            Book book = new Book("Jonas", null, true);
        });
    }

    @Test
    public void constructorThrowsErrorForEmptyTitle() {
        assertThrows(IllegalArgumentException.class, () -> {
            Book book = new Book("Jonas", "", true);
        });
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
        assertTrue(book.getVolume() == book.getHeight() * book.getWidth() * book.getLength());
    }
}