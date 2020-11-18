package kapistelykirjasto.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteDao implements Dao {

	private Connection connection;
	
	public SQLiteDao(String fileName) throws SQLException {
		this.connection = DriverManager.getConnection("jdbc:sqlite:" + fileName);
	
		Statement statement = this.connection.createStatement();
		statement.executeUpdate(
			"CREATE TABLE IF NOT EXISTS entry ("
		  + "	title TEXT"
		  + ")"
		);
	}
	
	public SQLiteDao() throws SQLException {
		this("production.db");
	}
	
	@Override
	public boolean createEntry(String title) {
		try {
			PreparedStatement statement = this.connection.prepareStatement("INSERT INTO entry(title) VALUES(?)");
			statement.setString(1, title);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public void close() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
