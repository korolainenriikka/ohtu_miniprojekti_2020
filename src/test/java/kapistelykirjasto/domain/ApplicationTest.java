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
    public void createBookCreatesNewBookWithDao() {
        this.logic.createBook("testi", "testi", "testi", "testi");
        assertEquals(this.dao.getBooks().size(), 1);
        assertEquals(this.dao.getBooks().get(0).getTitle(), "testi");
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
    public void getBooksReturnsRightList() {
        this.logic.createBook("title", "comment", "author", "ISBN1");
        this.logic.createBook("title1", "comment", "author", "ISBN2");
        this.logic.createBook("title2", "comment", "author", "ISBN3");

        assertEquals(3, this.logic.getBooks().size());
        assertEquals(this.dao.getBooks().get(0).getTitle(), "title");
        assertEquals(this.dao.getBooks().get(2).getTitle(), "title2");
    }

    @Test
    public void getVideosReturnsRightList() {
        this.logic.createVideo("title", "comment", "author", "123");
        this.logic.createVideo("title1", "comment", "author", "1234");
        this.logic.createVideo("title2", "comment", "author", "12345");

        assertEquals(3, this.logic.getVideos().size());
        assertEquals(this.dao.getVideos().get(0).getTitle(), "title");
        assertEquals(this.dao.getVideos().get(2).getTitle(), "title2");
    }
}
