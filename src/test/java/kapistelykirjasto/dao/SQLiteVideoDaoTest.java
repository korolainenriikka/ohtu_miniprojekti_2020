package kapistelykirjasto.dao;

import kapistelykirjasto.dao.models.VideoModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.sql.*;

import static org.junit.Assert.*;

public class SQLiteVideoDaoTest {

    private SQLiteVideoDao dao;
    private SQLiteCourseDao courseDao;
    private final File testDatabaseFile = new File("test_database.db");

    @Before
    public void setUp() throws SQLException, IOException {
        assertTrue(testDatabaseFile.createNewFile());
        this.dao = new SQLiteVideoDao(testDatabaseFile.getAbsolutePath());
        this.courseDao = new SQLiteCourseDao(testDatabaseFile.getAbsolutePath());
    }

    @After
    public void tearDown() {
        this.dao.close();
        assertTrue(testDatabaseFile.delete());
    }

    @Test
    public void createVideoCreatesRowInTableVideo() throws SQLException {
        this.dao.createVideo("Merge sort algorithm",
                "Vau! Miten visuaalista!",
                "https://www.youtube.com/watch?v=TzeBrDU-JaY",
                "7 min 34 sek");

        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + testDatabaseFile.getAbsolutePath());
        Statement statement = connection.createStatement();
        ResultSet videos = statement.executeQuery("SELECT * FROM video");

        assertTrue(videos.next());
        assertTrue(videos.getString("title").equals("Merge sort algorithm"));

        videos.close();
        statement.close();
        connection.close();
    }

    @Test
    public void getVideosReturnsRightSizeList() throws SQLException {
        this.dao.createVideo("otsikko", "kommentti", "tekija", "123");
        this.dao.createVideo("otsikko2", "kommentti", "tekija", "1234");
        this.dao.createVideo("otsikko3", "kommentti", "tekija", "1235");

        assertEquals(3, this.dao.getVideos().size());
    }

    @Test
    public void getVideosReturnsListContainingAllAddedVideos() {
        this.dao.createVideo("title", "comment", "author", "1.23");
        this.dao.createVideo("title2", "comment", "author", "1.59");
        this.dao.createVideo("title3", "comment", "author", "2");

        assertEquals("title", this.dao.getVideos().get(0).getTitle());
        assertEquals("title2", this.dao.getVideos().get(1).getTitle());
        assertEquals("title3", this.dao.getVideos().get(2).getTitle());
    }

    @Test
    public void getVideosReturnsNullWhenDatabaseClosed() {
        this.dao.close();
        assertEquals(null, this.dao.getVideos());
    }

    @Test
    public void createVideoReturnsErrorWhenDatabaseIsClosed() throws SQLException {
        this.dao.close();
        assertTrue(this.dao.createVideo("title", null, null, null).isError());
    }

    @Test
    public void deleteVideoDeletesVideo() {
        this.dao.createVideo("testi", "testi", "testi", "testi");
        this.dao.deleteVideo(this.dao.getVideos().get(0).getId());
        assertEquals(0, this.dao.getVideos().size());
    }

    @Test
    public void existsVideoReturnsFalseWhenDatabaseClosed() {
        this.dao.createVideo("testi", "testi", "testi", "testi");
        int id = this.dao.getVideos().get(0).getId();
        this.dao.close();
        assertFalse(this.dao.deleteVideo(id));
    }

    @Test
    public void editVideoUpdatesValues() {
        this.dao.createVideo("testi", "testi", "testi", "testi");
        int id = this.dao.getVideos().get(0).getId();
        this.dao.editVideo(id, "edit1", "edit2", "edit3", "edit4");
        VideoModel b = this.dao.getVideos().get(0);
        assertEquals(b.getUrl(), "edit3");
        assertEquals(b.getTitle(), "edit1");
        assertEquals(b.getComment(), "edit2");
        assertEquals(b.getDuration(), "edit4");
    }

    @Test
    public void editVideoReturnsFalseIfVideoDoesNotExist() {
        assertFalse(this.dao.editVideo(5, "edit1", "edit2", "edit3", "edit4"));
    }

    @Test
    public void courseBooksReturnsRigthList() {
        this.dao.createVideo("title", "comment", "author", "isbn");
        int videoId = this.dao.getVideos().get(0).getId();

        this.courseDao.createCourse("123", "testi");
        int courseId = this.courseDao.getCourses().get(0).getId();

        this.courseDao.addVideoCourseRelation(courseId, videoId);

        assertEquals(this.dao.getCourseVideos(courseId).size(), 1);
    }

}
