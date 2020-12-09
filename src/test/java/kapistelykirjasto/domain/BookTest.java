package kapistelykirjasto.domain;

import org.junit.Test;

import kapistelykirjasto.dao.models.BookModel;

import static org.junit.Assert.*;

public class BookTest {

    @Test
    public void createdBookHasAllGivenAttributes() {
        BookModel testBook = new BookModel(0,
                "Elements of the Theory of Computation",
                "Selkeät selitykset",
                "Harry R. Lewis",
                "135-896577-E");
        assertEquals(0, testBook.getId());
        assertEquals("Elements of the Theory of Computation", testBook.getTitle());
        assertEquals("Selkeät selitykset", testBook.getComment());
        assertEquals("Harry R. Lewis", testBook.getAuthor());
        assertEquals("135-896577-E", testBook.getISBN());
    }
}