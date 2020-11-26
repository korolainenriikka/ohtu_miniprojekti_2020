package kapistelykirjasto.domain;

import java.util.Random;

import kapistelykirjasto.domain.Entry;

import kapistelykirjasto.dao.SQLiteDao;
import kapistelykirjasto.dao.StubDao;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ApplicationTest {

    private ApplicationLogic logic;
    private StubDao dao;

    @Before
    public void setUp() {
        this.dao = new StubDao();
        this.logic = new ApplicationLogic(dao);
    }

    @Test
    public void createEntryCreatesNewEntryWithDao() {
        this.logic.createEntry("testi");
        assertEquals(this.dao.getEntries().size(), 1);
        assertEquals(this.dao.getEntries().get(0).getTitle(), "testi");
    }

    @Test
    public void createEntryReturnsTrueWithValidName() {
        assertTrue(this.logic.createEntry("testi"));
    }

    @Test
    public void createEntryReturnsFalseWithEmptyTitle() {
        assertFalse(this.logic.createEntry(""));
    }

    @Test
    public void createEntryReturnsFalseWithDuplicateEntry() {
        this.logic.createEntry("testi");
        assertFalse(this.logic.createEntry("testi"));
    }

    @Test
    public void createEntryDoesNotCreateDuplicateEntryWithDao() {
        this.logic.createEntry("testi");
        this.logic.createEntry("testi");

        assertEquals(1, this.dao.getEntries().size());
    }

    @Test
    public void addBookReturnsFalseIfRequiredAttributeMissing() {
        assertFalse(this.logic.createBook("testiTitle", "comment", "", "asdc123"));
    }

    @Test
    public void addBookReturnsTrueIfAllRequiredParamsGiven() {
        assertTrue(this.logic.createBook("testiTitle", "", "testiAuthor", "asdc123"));
    }
    
    @Test
    public void addVideoReturnsFalseIfRequiredAttributeMissing() {
        assertFalse(this.logic.createVideo("testiTitle", "comment", "", "asdc123"));
    }
    
    @Test
    public void addVideoReturnsTrueIfAllParamsGiven() {
        assertTrue(this.logic.createVideo("testiTitle", "comment", "url", "asdc123"));
    }
    
    @Test
    public void addVideoReturnsTrueIfRequiredParamsGiven() {
        assertTrue(this.logic.createVideo("testiTitle", "", "url", ""));
    }
    
    @Test
    public void getEntriesReturnsRightSizeList() {
        this.logic.createEntry("test1");
        this.logic.createEntry("test2");
        this.logic.createEntry("test3");

        assertEquals(3, this.logic.getEntries().size());
    }
    @Test
    public void getBooksReturnsRightSizeList() {
        this.logic.createBook("title", "comment", "author", "ISBN1");
        this.logic.createBook("title1", "comment", "author", "ISBN2");
        this.logic.createBook("title2", "comment", "author", "ISBN3");

        assertEquals(3, this.logic.getBooks().size());
    }

    @Test
    public void getVideosReturnsRightSizeList() {
        this.logic.createVideo("title", "comment", "author", "123");
        this.logic.createVideo("title1", "comment", "author", "1234");
        this.logic.createVideo("title2", "comment", "author", "12345");

        assertEquals(3, this.logic.getVideos().size());
    }

    @Test
    public void deleteEntryBasedOnTitleReturnsFalseIfTitleNotInDb() {
        assertFalse(this.logic.deleteEntryBasedOnTitle("NotTitle"));
    }

    @Test
    public void deleteEntryReturnsFalseIfNoTitleGiven() {
        assertFalse(this.logic.deleteEntryBasedOnTitle(""));
    }

    @Test
    public void deleteEntryReturnsTrueIfDeleteSuccessful() {
        this.logic.createEntry("IsTitle");
        assertTrue(this.logic.deleteEntryBasedOnTitle("IsTitle"));
    }
}
