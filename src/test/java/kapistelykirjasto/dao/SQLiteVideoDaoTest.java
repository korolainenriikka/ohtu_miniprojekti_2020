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
        this.dao.createVideo("Slow sorting: Stooge sort and Bogo sort",
                "Hyvä havainnollistus",
                "https://www.youtube.com/watch?v=bfzYj-qGw7U",
                "40:52");
        this.dao.createVideo("Visualization of Quick sort",
                "Todella selkeä",
                "https://www.youtube.com/watch?v=vxENKlcs2Tw",
                "2:56");
        this.dao.createVideo("Crash Course Computer Science Preview",
                "Mielenkiintoinen",
                "https://youtu.be/tpIctyqH29Q",
                "2:44");

        assertEquals(3, this.dao.getVideos().size());
    }

    @Test
    public void getVideosReturnsListContainingAllAddedVideos() {
        this.dao.createVideo("Slow sorting: Stooge sort and Bogo sort",
                "Hyvä havainnollistus",
                "https://www.youtube.com/watch?v=bfzYj-qGw7U",
                "40:52");
        this.dao.createVideo("Visualization of Quick sort",
                "Todella selkeä",
                "https://www.youtube.com/watch?v=vxENKlcs2Tw",
                "2:56");
        this.dao.createVideo("Crash Course Computer Science Preview",
                "Mielenkiintoinen",
                "https://youtu.be/tpIctyqH29Q",
                "2:44");

        assertEquals("Slow sorting: Stooge sort and Bogo sort", this.dao.getVideos().get(0).getTitle());
        assertEquals("Visualization of Quick sort", this.dao.getVideos().get(1).getTitle());
        assertEquals("Crash Course Computer Science Preview", this.dao.getVideos().get(2).getTitle());
    }

    @Test
    public void getVideosReturnsNullWhenDatabaseClosed() {
        this.dao.close();
        assertEquals(null, this.dao.getVideos());
    }

    @Test
    public void createVideoReturnsErrorWhenDatabaseIsClosed() throws SQLException {
        this.dao.close();
        assertTrue(this.dao.createVideo("Crash Course Computer Science Preview", null, null, null).isError());
    }

    @Test
    public void deleteVideoDeletesVideo() {
        this.dao.createVideo("Crash Course Computer Science Preview",
                "Mielenkiintoinen",
                "https://youtu.be/tpIctyqH29Q",
                "2:44");
        this.dao.deleteVideo(this.dao.getVideos().get(0).getId());
        assertEquals(0, this.dao.getVideos().size());
    }

    @Test
    public void existsVideoReturnsFalseWhenDatabaseClosed() {
        this.dao.createVideo("Crash Course Computer Science Preview",
                "Mielenkiintoinen",
                "https://youtu.be/tpIctyqH29Q",
                "2:44");
        int id = this.dao.getVideos().get(0).getId();
        this.dao.close();
        assertFalse(this.dao.deleteVideo(id));
    }

    @Test
    public void editVideoUpdatesValues() {
        this.dao.createVideo("Visualization of sort",
                "Todella outo",
                "https://www.youtube.com/",
                "2:5655");
        int id = this.dao.getVideos().get(0).getId();
        this.dao.editVideo(id, "Visualization of Quick sort",
                "Todella selkeä",
                "https://www.youtube.com/watch?v=vxENKlcs2Tw",
                "2:56");
        VideoModel b = this.dao.getVideos().get(0);
        assertEquals(b.getUrl(), "https://www.youtube.com/watch?v=vxENKlcs2Tw");
        assertEquals(b.getTitle(), "Visualization of Quick sort");
        assertEquals(b.getComment(), "Todella selkeä");
        assertEquals(b.getDuration(), "2:56");
    }

    @Test
    public void editVideoReturnsFalseIfVideoDoesNotExist() {
        assertFalse(this.dao.editVideo(5, "Visualization of Quick sort",
                "Todella selkeä",
                "https://www.youtube.com/watch?v=vxENKlcs2Tw",
                "2:56"));
    }

    @Test
    public void courseVideosReturnsRightList() {
        this.dao.createVideo("Crash Course Computer Science Preview",
                "Mielenkiintoinen",
                "https://youtu.be/tpIctyqH29Q",
                "2:44");
        int videoId = this.dao.getVideos().get(0).getId();

        this.courseDao.createCourse("TKT10001", "Johdatus tietojenkäsittelytieteeseen");
        int courseId = this.courseDao.getCourses().get(0).getId();

        this.courseDao.addVideoCourseRelation(courseId, videoId);

        assertEquals(this.dao.getCourseVideos(courseId).size(), 1);
    }

}
