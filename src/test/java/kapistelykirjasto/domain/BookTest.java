package kapistelykirjasto.domain;

import org.junit.Test;

import kapistelykirjasto.dao.models.BookModel;

import static org.junit.Assert.*;

public class BookTest {

    @Test
    public void createdBookHasAllGivenAttributes() {
        BookModel testBook = new BookModel(0, "testBook", "this book would be a cool read", "Uncle Bob", "abcd123");
        assertEquals(0, testBook.getId());
        assertEquals("testBook", testBook.getTitle());
        assertEquals("this book would be a cool read", testBook.getComment());
        assertEquals("Uncle Bob", testBook.getAuthor());
        assertEquals("abcd123", testBook.getISBN());
    }
}