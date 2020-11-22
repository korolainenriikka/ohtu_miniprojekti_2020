package kapistelykirjasto.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SQLiteDao implements Dao {

    private Connection connection;

    /**
     * Creates a new connection to the SQLite database in fileName. Pass in ":memory:" as the file name to
     * create a in-memory database.
     *
     * @param fileName
     * @throws SQLException
     */
    public SQLiteDao(String fileName) {
        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:" + fileName);

            Statement statement = this.connection.createStatement();
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS entry (title TEXT UNIQUE);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a new connection to a SQLite database with the file name "production.db"
     *
     * @throws SQLException
     */
    public SQLiteDao() throws SQLException {
        this("production.db");
    }

    @Override
    public boolean createEntry(Entry entry) {
        try {
            if (existsEntry(entry)) {
                return false;
            }
            PreparedStatement statement = this.connection.prepareStatement("INSERT INTO entry(title) VALUES(?);");
            statement.setString(1, entry.getTitle());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.getErrorCode();
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean existsEntry(Entry entry) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(
                "SELECT * FROM entry WHERE"
                        + " title=?;"
        );
        statement.setString(1, entry.getTitle());
        ResultSet res = statement.executeQuery();
        boolean exists = res.next(); // If there is an element available in the result set, next() returns true.
        statement.close();
        return exists;
    }

    public ArrayList<Entry> getEntries() {

        ArrayList<Entry> entryes = new ArrayList<>();

        try {
            PreparedStatement statement = this.connection.prepareStatement(
                    "SELECT * FROM entry");
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                entryes.add(new Entry(res.getString("title")));
            }
            statement.close();
            return entryes;

        } catch (SQLException e) {
            e.getErrorCode();
            e.printStackTrace();
        }
        return entryes;
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
