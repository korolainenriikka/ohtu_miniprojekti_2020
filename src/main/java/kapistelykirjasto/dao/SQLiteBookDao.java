package kapistelykirjasto.dao;

import java.sql.*;
import java.util.ArrayList;

import kapistelykirjasto.dao.models.BookModel;
import kapistelykirjasto.dao.models.VideoModel;

public class SQLiteBookDao implements BookDao {

    private Connection connection;

    public SQLiteBookDao(String fileName) {
        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:" + fileName);

            Statement statement = this.connection.createStatement();
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS book (id INTEGER PRIMARY KEY AUTOINCREMENT"
                            + ", title TEXT UNIQUE, comment TEXT, author TEXT, ISBN TEXT, read TIMESTAMP DEFAULT NULL);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public SQLiteBookDao() throws SQLException {
        this("production.db");
    }

    @Override
    public boolean createBook(String title, String comment, String author, String ISBN) {
        try {
            PreparedStatement statement = this.connection.prepareStatement("INSERT INTO book(title, comment, author, isbn) VALUES(?,?,?,?);");
            statement.setString(1, title);
            statement.setString(2, comment);
            statement.setString(3, author);
            statement.setString(4, ISBN);
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
    public ArrayList<BookModel> getBooks() {
        try {
            PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM book");
            ResultSet res = statement.executeQuery();
            ArrayList<BookModel> books = new ArrayList<>();
            while (res.next()) {
                books.add(
                        new BookModel(res.getInt("id"), res.getString("title"), res.getString("comment"),
                                res.getString("author"), res.getString("ISBN"))
                );
            }
            statement.close();
            return books;

        } catch (SQLException e) {
            e.getErrorCode();
            e.printStackTrace();
        }
        return null;
    }

    private boolean existsBook(int id) {
        try {
            PreparedStatement statement = this.connection.prepareStatement(
                    "SELECT * FROM book WHERE id=?;"
            );
            statement.setInt(1, id);
            ResultSet res = statement.executeQuery();
            boolean exists = res.next(); // If there is an element available in the result set, next() returns true.
            statement.close();
            return exists;
        } catch (SQLException e) {
            e.getErrorCode();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteBook(int id) {
        try {
            if (!existsBook(id)) {
                return false;
            }
            PreparedStatement statement = this.connection.prepareStatement("DELETE FROM book WHERE id = ?");
            statement.setInt(1, id);
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
    public boolean editBook(int id, String title, String comment, String author, String ISBN) {
        try {
            if (!existsBook(id)) {
                return false;
            }
            PreparedStatement statement = this.connection.prepareStatement(
                    "UPDATE book SET title=?, comment=?, author=?, isbn=? " + "WHERE id=?");
            statement.setString(1, title);
            statement.setString(2, comment);
            statement.setString(3, author);
            statement.setString(4, ISBN);
            statement.setString(5, String.valueOf(id));
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            printSQLException(e);
            return false;
        }
        return true;
    }

    public void printSQLException(SQLException exception) {

        exception.getErrorCode();
        exception.printStackTrace();
    }

    @Override
    public boolean markBookAsRead(int id) {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String setTime = String.valueOf(timestamp.getTime());
        try {
            if (!existsBook(id)) {
                return false;
            }
            PreparedStatement statement = this.connection.prepareStatement("UPDATE book SET read=? " + "WHERE id=?");
            statement.setString(1, setTime);
            statement.setString(2, String.valueOf(id));
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            printSQLException(e);
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<BookModel> getReadBooks() {
        try {
            PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM book WHERE read IS NOT NULL;");
            ResultSet res = statement.executeQuery();
            ArrayList<BookModel> books = new ArrayList<>();
            while (res.next()) {
                books.add(
                        new BookModel(res.getInt("id"), res.getString("title"), res.getString("comment"),
                                res.getString("author"), res.getString("ISBN"))
                );
            }
            statement.close();
            return books;

        } catch (SQLException e) {
            e.getErrorCode();
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<BookModel> getNotReadBooks() {
        try {
            PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM book WHERE read IS NULL;");
            ResultSet res = statement.executeQuery();
            ArrayList<BookModel> books = new ArrayList<>();
            while (res.next()) {
                books.add(
                        new BookModel(res.getInt("id"), res.getString("title"), res.getString("comment"),
                                res.getString("author"), res.getString("ISBN"))
                );
            }
            statement.close();
            return books;

        } catch (SQLException e) {
            e.getErrorCode();
            e.printStackTrace();
        }
        return null;
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
