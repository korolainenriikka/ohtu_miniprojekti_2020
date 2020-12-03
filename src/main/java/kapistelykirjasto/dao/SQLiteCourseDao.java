package kapistelykirjasto.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteCourseDao implements CourseDao {

    private Connection connection;

    public SQLiteCourseDao(String fileName) {
        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:" + fileName);

            Statement statement = this.connection.createStatement();
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS course (id INTEGER PRIMARY KEY AUTOINCREMENT"
                            + ", coursecode TEXT, name TEXT);");
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS courseBook (courseId INTEGER, bookId INTEGER);");
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS courseVideo (courseId INTEGER, videoId INTEGER);");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public SQLiteCourseDao() throws SQLException {
        this("production.db");
    }
}
