package kapistelykirjasto.dao;

import kapistelykirjasto.dao.models.CourseModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class SQLiteCourseDaoTest {

    private SQLiteCourseDao dao;
    private final File testDatabaseFile = new File("test_database.db");

    @Before
    public void setUp() throws SQLException, IOException {
        assertTrue(testDatabaseFile.createNewFile());
        this.dao = new SQLiteCourseDao(testDatabaseFile.getAbsolutePath());
    }

    @After
    public void tearDown() {
        this.dao.close();
        assertTrue(testDatabaseFile.delete());
    }

    @Test
    public void constructorCreatesCourseTable() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + testDatabaseFile.getAbsolutePath());
        ResultSet tables = connection.getMetaData().getTables(null, null, null, null);

        boolean courseTableExists = false;
        while (tables.next()) {
            if (tables.getString("TABLE_NAME").equals("course")) {
                courseTableExists = true;
            }
        }

        assertTrue(courseTableExists);
    }

    @Test
    public void createCourseAddsRowToCourseTable() throws SQLException{
        this.dao.createCourse("TKT123", "refaktoroinnin perusteet");
        assertTrue(dao.getCourses().get(0).getName().equals("refaktoroinnin perusteet"));
    }

    @Test
    public void addBookCourseRelationAddsRowToRelationTable() {

    }

    @Test
    public void addVideoCourseRelationAddsRowToRelationTable() {

    }

    @Test
    public void getCoursesReturnsEmptyListWhenNoCoursesInDb() {
        assertTrue(dao.getCourses().isEmpty());
    }

    @Test
    public void getCoursesReturnsAllCoursesAddedToDb() {
        this.dao.createCourse("TKT123", "refaktoroinnin perusteet");
        this.dao.createCourse("TKT123", "refaktoroinnin jatkokurssi");

        List<CourseModel> courses = dao.getCourses();
        assertTrue(courses.size() == 2);
        assertTrue(courses.get(0).getName().equals("refaktoroinnin perusteet"));
        assertTrue(courses.get(1).getName().equals("refaktoroinnin jatkokurssi"));
    }

}
