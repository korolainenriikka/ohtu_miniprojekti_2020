package kapistelykirjasto.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class BookTest {

    @Test
    public void createdBookHasAllGivenAttributes() {
        Book testBook = new Book("testBook", "this book would be a cool read", "Uncle Bob", "abcd123");
        assertEquals("testBook", testBook.getTitle());
        assertEquals("this book would be a cool read", testBook.getComment());
        assertEquals("Uncle Bob", testBook.getAuthor());
        assertEquals("abcd123", testBook.getISBN());
    }
}