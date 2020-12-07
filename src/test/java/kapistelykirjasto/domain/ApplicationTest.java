package kapistelykirjasto.domain;


import kapistelykirjasto.dao.StubDao;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ApplicationTest {

    private ApplicationLogic logic;
    //   private SQLiteDao dao;
    private StubDao dao;

    @Before
    public void setUp() {
        //  this.dao = new SQLiteDao(":memory:");
        this.dao = new StubDao();
        this.logic = new ApplicationLogic(dao, dao, dao);
    }

    @Test
    public void createBookCreatesNewBookWithDao() {
        this.logic.createBook("testi", "testi", "testi", "testi");
        assertEquals(this.dao.getBooks().size(), 1);
        assertEquals(this.dao.getBooks().get(0).getTitle(), "testi");
    }

    @Test
    public void addBookReturnsErrorIfRequiredAttributeMissing() {
        assertTrue(this.logic.createBook("testiTitle", "comment", "", "asdc123").isError());
    }

    @Test
    public void addBookReturnsValueIfAllRequiredParamsGiven() {
        assertTrue(this.logic.createBook("testiTitle", "", "testiAuthor", "asdc123").isValue());
    }

    @Test
    public void addVideoReturnsErrorIfRequiredAttributeMissing() {
        assertTrue(this.logic.createVideo("testiTitle", "comment", "", "asdc123").isError());
    }

    @Test
    public void addVideoReturnsTrueIfAllParamsGiven() {
        assertTrue(this.logic.createVideo("testiTitle", "comment", "url", "asdc123").isValue());
    }

    @Test
    public void addVideoReturnsTrueIfRequiredParamsGiven() {
        assertTrue(this.logic.createVideo("testiTitle", "", "url", "").isValue());
    }

    @Test
    public void getBooksReturnsRightList() {
        this.logic.createBook("title", "comment", "author", "ISBN1");
        this.logic.createBook("title1", "comment", "author", "ISBN2");
        this.logic.createBook("title2", "comment", "author", "ISBN3");

        assertEquals(3, this.logic.getBooks().size());
        assertEquals(this.logic.getBooks().get(0).getTitle(), "title");
        assertEquals(this.logic.getBooks().get(2).getTitle(), "title2");
    }

    @Test
    public void getVideosReturnsRightList() {
        this.logic.createVideo("title", "comment", "author", "123");
        this.logic.createVideo("title1", "comment", "author", "1234");
        this.logic.createVideo("title2", "comment", "author", "12345");

        assertEquals(3, this.logic.getVideos().size());
        assertEquals(this.logic.getVideos().get(0).getTitle(), "title");
        assertEquals(this.logic.getVideos().get(2).getTitle(), "title2");
    }

    @Test
    public void getReadBooksReturnsRightList() {
        this.logic.createBook("title", "comment", "author", "ISBN1");
        this.logic.createBook("title1", "comment", "author", "ISBN2");
        ArrayList<Entry>entries = this.logic.getEntries();
        assertTrue(this.logic.markBookAsRead(entries.get(1).getId()));
        assertEquals(1, this.logic.getReadBooks().size());
        assertEquals(this.logic.getReadBooks().get(0).getTitle(), "title1");
    }

    @Test
    public void getReadVideosReturnsRightList() {
        this.logic.createVideo("title", "comment", "author", "123");
        this.logic.createVideo("title1", "comment", "author", "1234");
        ArrayList<Entry>entries = this.logic.getEntries();
        assertTrue(this.logic.markVideoAsRead(entries.get(1).getId()));
        assertEquals(1, this.logic.getReadVideos().size());
        assertEquals(this.logic.getReadVideos().get(0).getTitle(), "title1");
    }

    @Test
    public void getNotReadEntriesReturnsRightList() {
        this.logic.createBook("title", "comment", "author", "ISBN1");
        this.logic.createBook("title1", "comment", "author", "ISBN2");
        assertEquals(2, this.logic.getNotReadEntries().size());
        assertEquals(this.logic.getNotReadEntries().get(1).getTitle(), "title1");
    }

    @Test
    public void getReadEntriesReturnsRightList() {
        this.logic.createBook("title", "comment", "author", "ISBN1");
        this.logic.createBook("title1", "comment", "author", "ISBN2");
        this.logic.markBookAsRead(0);
        this.logic.markBookAsRead(1);
        assertEquals(2, this.logic.getReadEntries().size());
        assertEquals(this.logic.getReadEntries().get(1).getTitle(), "title1");
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
    public void editBookEditsBook() {
        this.logic.createBook("title", "comment", "author", "ISBN");
        int bookId = this.logic.getBooks().get(0).getId();

        assertTrue(this.logic.editBook(bookId,"title2", "comment2", "author2", "ISBN2"));
        Book book = this.logic.getBooks().get(0);

        assertEquals("title2", book.getTitle());
        assertEquals("author2", book.getAuthor());
        assertEquals("ISBN2", book.getISBN());
        assertEquals("comment2", book.getComment());
    }
    @Test
    public void editVideoEditsVideo() {
        this.logic.createVideo("title", "comment", "url", "duration");
        int videoId = this.logic.getVideos().get(0).getId();

        assertTrue(this.logic.editVideo(videoId,"title2", "comment2", "url2", "duration2"));
        Video video = this.logic.getVideos().get(0);

        assertEquals("title2", video.getTitle());
        assertEquals("comment2", video.getComment());
        assertEquals("url2", video.getUrl());
        assertEquals("duration2", video.getDuration());
    }

    @Test
    public void deleteBookReturnsFalseOnInvalidId() {
        assertFalse(this.logic.deleteBook(0));
    }

    @Test
    public void deleteVideoReturnsFalseOnInvalidId() {
        assertFalse(this.logic.deleteVideo(0));
    }

    @Test
    public void createCourseReturnsTrueIfValidParamsGiven() {
        assertTrue(this.logic.createCourse("TKT20005", "Laskennan mallit"));
    }

    @Test
    public void createCourseReturnsFalseIfInvalidParamsGiven() {
        assertFalse(this.logic.createCourse("", ""));
    }

    @Test
    public void createCourseReturnsFalseIfCourseCodeMissing() {
        assertFalse(this.logic.createCourse("", "Todennäköisyyslaskenta"));
    }

    @Test
    public void createCourseReturnsFalseIfNameMissing() {
        assertFalse(this.logic.createCourse("TKT20011", ""));
    }
}
