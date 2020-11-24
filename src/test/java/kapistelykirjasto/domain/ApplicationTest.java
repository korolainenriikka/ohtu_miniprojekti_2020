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
    public void getEntriesReturnsRightSizeList() {
        this.logic.createEntry("test1");
        this.logic.createEntry("test2");
        this.logic.createEntry("test3");

        assertEquals(3, this.logic.getEntries().size());
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
