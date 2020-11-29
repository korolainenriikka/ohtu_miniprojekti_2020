package kapistelykirjasto.domain;


import kapistelykirjasto.dao.SQLiteDao;
import kapistelykirjasto.dao.StubDao;
import kapistelykirjasto.dao.models.Model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ApplicationTest {

    private ApplicationLogic logic;
    private SQLiteDao dao;
    
    @Before
    public void setUp() {
        this.dao = new SQLiteDao(":memory:");
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
    
    @Test
    public void deleteBookDeletesBook() {
    	this.logic.createBook("title", "comment", "author", "ISBN");
    	this.logic.deleteBook(this.logic.getBooks().get(0).getId());
    	assertEquals(0, this.logic.getBooks().size());
    }
    
    @Test
    public void deleteVideoDeletesVideo() {
    	this.logic.createVideo("title", "comment", "url", "duration");
    	this.logic.deleteVideo(this.logic.getVideos().get(0).getId());
    	assertEquals(0, this.logic.getVideos().size());
    }
    
    @Test
    public void deleteEntryDeletesBook() {
    	this.logic.createBook("title", "comment", "author", "ISBN");
    	this.logic.deleteEntry(this.logic.getBooks().get(0));
    	assertEquals(0, this.logic.getBooks().size());
    }
    
    @Test
    public void deleteEntryDeletesVideo() {
    	this.logic.createVideo("title", "comment", "url", "duration");
    	this.logic.deleteEntry(this.logic.getVideos().get(0));
    	assertEquals(0, this.logic.getVideos().size());
    }
    
    @Test
    public void deleteBookReturnsFalseOnInvalidId() {
    	assertFalse(this.logic.deleteBook(0));
    }
    
    @Test
    public void deleteVideoReturnsFalseOnInvalidId() {
    	assertFalse(this.logic.deleteVideo(0));
    }
}
