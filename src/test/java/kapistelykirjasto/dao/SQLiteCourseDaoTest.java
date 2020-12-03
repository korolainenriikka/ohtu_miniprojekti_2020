package kapistelykirjasto.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.sql.*;

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

        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + testDatabaseFile.getAbsolutePath());
        Statement statement = connection.createStatement();
        ResultSet courses = statement.executeQuery("SELECT * FROM course");

        assertTrue(courses.next());
        assertTrue(courses.getString("name").equals("refaktoroinnin perusteet"));

        courses.close();
        statement.close();
        connection.close();
    }

}
