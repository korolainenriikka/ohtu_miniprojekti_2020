package kapistelykirjasto.dao;

import java.sql.*;

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

    @Override
    public boolean createCourse(String courseCode, String name) {
        try {
            PreparedStatement statement = this.connection.prepareStatement("INSERT INTO course(coursecode, name) VALUES(?,?);");
            statement.setString(1, courseCode);
            statement.setString(2, name);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.getErrorCode();
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean addBookCourseRelation(int courseId, int bookId) {
        return false;
    }

    @Override
    public boolean addVideoCourseRelation(int courseId, int videoId) {
        return false;
    }

    @Override
    public void close() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
