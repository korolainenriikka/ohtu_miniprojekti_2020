package kapistelykirjasto.domain;

import kapistelykirjasto.dao.StubDao;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ApplicationTest {

    private ApplicationLogic logic;
    private StubDao dao;

    @Before
    public void setUp() {
        this.dao = new StubDao();
        this.logic = new ApplicationLogic(dao, dao, dao);
    }

    @Test
    public void createBookCreatesNewBookWithDao() {
        int[] course = new int[1];
        course[0] = 1;
        this.logic.createBook("Hello Ruby!",
                "Sopii lapsille!",
                "Linda Liukas",
                "032-135522-K",
                course);
        assertEquals(this.dao.getBooks().size(), 1);
        assertEquals(this.dao.getBooks().get(0).getTitle(), "Hello Ruby!");
    }

    @Test
    public void addBookReturnsErrorIfRequiredAttributeMissing() {
        assertTrue(this.logic.createBook("Elements of the Theory of Computation",
                "Selkeät selitykset",
                "",
                "135-896577-E").isError());
    }

    @Test
    public void addBookReturnsValueIfAllRequiredParamsGiven() {
        assertTrue(this.logic.createBook("Elements of the Theory of Computation",
                "",
                "Harry R. Lewis",
                "135-896577-E").isValue());
    }
    
    @Test
    public void createVideoCreatesNewVideoWithDao() {
        int[] course = new int[1];
        course[0] = 1;
        this.logic.createVideo("Slow sorting: Stooge sort and Bogo sort",
                "Hyvä havainnollistus",
                "https://www.youtube.com/watch?v=bfzYj-qGw7U",
                "40:52",
                course);
        assertEquals(this.dao.getVideos().size(), 1);
        assertEquals(this.dao.getVideos().get(0).getTitle(), "Slow sorting: Stooge sort and Bogo sort");
    }

    @Test
    public void addVideoReturnsErrorIfRequiredAttributeMissing() {
        assertTrue(this.logic.createVideo("Visualization of Quick sort",
                "Todella selkeä",
                "",
                "2:56").isError());
    }

    @Test
    public void addVideoReturnsTrueIfAllParamsGiven() {
        assertTrue(this.logic.createVideo("Visualization of Quick sort",
                "Todella selkeä",
                "https://www.youtube.com/watch?v=vxENKlcs2Tw",
                "2:56").isValue());
    }

    @Test
    public void addVideoReturnsTrueIfRequiredParamsGiven() {
        assertTrue(this.logic.createVideo("Visualization of Quick sort",
                "",
                "https://www.youtube.com/watch?v=vxENKlcs2Tw",
                "").isValue());
    }

    @Test
    public void getBooksReturnsRightList() {
        this.logic.createBook("Elements of the Theory of Computation",
                "Selkeät selitykset",
                "Harry R. Lewis",
                "135-896577-E");
        this.logic.createBook("Database System Concepts",
                "Todella pitkä kirja",
                "Henry F. Fort",
                "007-124476-X");
        this.logic.createBook("Hello Ruby!",
                "Sopii lapsille!",
                "Linda Liukas",
                "032-135522-K");

        assertEquals(3, this.logic.getBooks().size());
        assertEquals(this.logic.getBooks().get(0).getTitle(), "Elements of the Theory of Computation");
        assertEquals(this.logic.getBooks().get(2).getTitle(), "Hello Ruby!");
    }

    @Test
    public void getVideosReturnsRightList() {
        this.logic.createVideo("Slow sorting: Stooge sort and Bogo sort",
                "Hyvä havainnollistus",
                "https://www.youtube.com/watch?v=bfzYj-qGw7U",
                "40:52");
        this.logic.createVideo("Visualization of Quick sort",
                "Todella selkeä",
                "https://www.youtube.com/watch?v=vxENKlcs2Tw",
                "2:56");
        this.logic.createVideo("Crash Course Computer Science Preview",
                "Mielenkiintoinen",
                "https://youtu.be/tpIctyqH29Q",
                "2:44");

        assertEquals(3, this.logic.getVideos().size());
        assertEquals(this.logic.getVideos().get(0).getTitle(), "Slow sorting: Stooge sort and Bogo sort");
        assertEquals(this.logic.getVideos().get(2).getTitle(), "Crash Course Computer Science Preview");
    }

    @Test
    public void getReadBooksReturnsRightList() {
        this.logic.createBook("Database System Concepts",
                "Todella pitkä kirja",
                "Henry F. Fort",
                "007-124476-X");
        this.logic.createBook("Hello Ruby!",
                "Sopii lapsille!",
                "Linda Liukas",
                "032-135522-K");
        ArrayList<Entry> entries = this.logic.getEntries();
        assertTrue(this.logic.markBookAsRead(entries.get(1).getId()));
        assertEquals(1, this.logic.getReadBooks().size());
        assertEquals(this.logic.getReadBooks().get(0).getTitle(), "Hello Ruby!");
    }

    @Test
    public void getReadVideosReturnsRightList() {
        this.logic.createVideo("Visualization of Quick sort",
                "Todella selkeä",
                "https://www.youtube.com/watch?v=vxENKlcs2Tw",
                "2:56");
        this.logic.createVideo("Crash Course Computer Science Preview",
                "Mielenkiintoinen",
                "https://youtu.be/tpIctyqH29Q",
                "2:44");
        ArrayList<Entry> entries = this.logic.getEntries();
        assertTrue(this.logic.markVideoAsRead(entries.get(1).getId()));
        assertEquals(1, this.logic.getReadVideos().size());
        assertEquals(this.logic.getReadVideos().get(0).getTitle(), "Crash Course Computer Science Preview");
    }

    @Test
    public void getNotReadEntriesReturnsRightList() {
        this.logic.createBook("Database System Concepts",
                "Todella pitkä kirja",
                "Henry F. Fort",
                "007-124476-X");
        this.logic.createBook("Hello Ruby!",
                "Sopii lapsille!",
                "Linda Liukas",
                "032-135522-K");
        assertEquals(2, this.logic.getNotReadEntries().size());
        assertEquals(this.logic.getNotReadEntries().get(1).getTitle(), "Hello Ruby!");
    }

    @Test
    public void getReadEntriesReturnsRightList() {
        this.logic.createBook("Database System Concepts",
                "Todella pitkä kirja",
                "Henry F. Fort",
                "007-124476-X");
        this.logic.createBook("Hello Ruby!",
                "Sopii lapsille!",
                "Linda Liukas",
                "032-135522-K");
        this.logic.markBookAsRead(0);
        this.logic.markBookAsRead(1);
        assertEquals(2, this.logic.getReadEntries().size());
        assertEquals(this.logic.getReadEntries().get(1).getTitle(), "Hello Ruby!");
    }

    @Test
    public void deleteBookDeletesBook() {
        this.logic.createBook("Elements of the Theory of Computation",
                "Selkeät selitykset",
                "Harry R. Lewis",
                "135-896577-E");
        this.logic.deleteBook(this.logic.getBooks().get(0).getId());
        assertEquals(0, this.logic.getBooks().size());
    }

    @Test
    public void deleteVideoDeletesVideo() {
        this.logic.createVideo("Crash Course Computer Science Preview",
                "Mielenkiintoinen",
                "https://youtu.be/tpIctyqH29Q",
                "2:44");
        this.logic.deleteVideo(this.logic.getVideos().get(0).getId());
        assertEquals(0, this.logic.getVideos().size());
    }

    @Test
    public void deleteEntryDeletesBook() {
        this.logic.createBook("Elements of the Theory of Computation",
                "Selkeät selitykset",
                "Harry R. Lewis",
                "135-896577-E");
        this.logic.deleteEntry(this.logic.getBooks().get(0));
        assertEquals(0, this.logic.getBooks().size());
    }

    @Test
    public void deleteEntryDeletesVideo() {
        this.logic.createVideo("Crash Course Computer Science Preview",
                "Mielenkiintoinen",
                "https://youtu.be/tpIctyqH29Q",
                "2:44");
        this.logic.deleteEntry(this.logic.getVideos().get(0));
        assertEquals(0, this.logic.getVideos().size());
    }

    @Test
    public void editBookEditsBook() {
        this.logic.createBook("Hello uby!",
                "Sopii kaikille!",
                "Linda",
                "032-135522-S");
        int bookId = this.logic.getBooks().get(0).getId();

        assertTrue(this.logic.editBook(bookId,
                "Hello Ruby!",
                "Sopii erityisesti lapsille!",
                "Linda Liukas",
                "032-135522-K"));
        Book book = this.logic.getBooks().get(0);

        assertEquals("Hello Ruby!", book.getTitle());
        assertEquals("Linda Liukas", book.getAuthor());
        assertEquals("032-135522-K", book.getISBN());
        assertEquals("Sopii erityisesti lapsille!", book.getComment());
    }

    @Test
    public void editVideoEditsVideo() {
        this.logic.createVideo("Visualization of sort",
                "Todella outo",
                "https://www.youtube.com/",
                "2:5655");
        int videoId = this.logic.getVideos().get(0).getId();

        assertTrue(this.logic.editVideo(videoId, "Visualization of Quick sort",
                "Todella selkeä",
                "https://www.youtube.com/watch?v=vxENKlcs2Tw",
                "2:56"));
        Video video = this.logic.getVideos().get(0);

        assertEquals("Visualization of Quick sort", video.getTitle());
        assertEquals("Todella selkeä", video.getComment());
        assertEquals("https://www.youtube.com/watch?v=vxENKlcs2Tw", video.getUrl());
        assertEquals("2:56", video.getDuration());
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
    @Test
    public void getCourseEntriesReturnRightEntries() {

        this.logic.createCourse("TKT20003", "Operating Systems");
        int[] course = new int[1];
        course[0] = 1;
        this.logic.createBook("Hello Ruby!",
                "Sopii erityisesti lapsille!",
                "Linda Liukas",
                "032-135522-K",
                course);
        this.logic.createVideo("Visualization of Quick sort",
                "Todella selkeä",
                "https://www.youtube.com/watch?v=vxENKlcs2Tw",
                "2:56",
                course);
        ArrayList<Entry>courseEntries = this.logic.getCourseEntries(1);
        assertEquals(2,courseEntries.size());
        assertEquals("Visualization of Quick sort", courseEntries.get(1).getTitle());
    }
}
