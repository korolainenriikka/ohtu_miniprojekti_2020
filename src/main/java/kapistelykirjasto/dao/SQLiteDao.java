package kapistelykirjasto.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteDao implements Dao {

	private Connection connection;
	
	/**
	 * Creates a new connection to the SQLite database in fileName. Pass in ":memory:" as the file name to
	 * create a in-memory database.
	 * @param fileName
	 * @throws SQLException
	 */
	public SQLiteDao(String fileName) throws SQLException {
		this.connection = DriverManager.getConnection("jdbc:sqlite:" + fileName);
	
		Statement statement = this.connection.createStatement();
		statement.executeUpdate(
			"CREATE TABLE IF NOT EXISTS entry ("
		  + "	title TEXT"
		  + ")"
		);
	}
	
	/**
	 * Creates a new connection to a SQLite database with the file name "production.db"
	 * @throws SQLException
	 */
	public SQLiteDao() throws SQLException {
		this("production.db");
	}
	
	@Override
	public boolean createEntry(Entry entry) {
		try {
			PreparedStatement statement = this.connection.prepareStatement("INSERT INTO entry(title) VALUES(?)");
			statement.setString(1, entry.getTitle());
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
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
