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
        return executeSQLUpdate("INSERT INTO course(courseCode, name) VALUES(?,?);",
                courseCode, name);
    }

    @Override
    public boolean addBookCourseRelation(int courseId, int bookId) {
        return executeSQLUpdate("INSERT INTO courseBook(courseid, bookId) VALUES(?,?);",
                courseId + "", bookId + "");
    }

    @Override
    public boolean addVideoCourseRelation(int courseId, int videoId) {
        return executeSQLUpdate("INSERT INTO courseBook(courseid, bookId) VALUES(?,?);",
                courseId + "", videoId + "");
    }

    private boolean executeSQLUpdate(String SQLstatement, String... params) {
        try {
            PreparedStatement statement = this.connection.prepareStatement(SQLstatement);
            int i = 1;
            for (String param: params) {
                statement.setString(i, param);
                i++;
            }
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
    public void close() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
