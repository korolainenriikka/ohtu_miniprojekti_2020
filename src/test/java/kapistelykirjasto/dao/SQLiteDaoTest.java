package kapistelykirjasto.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SQLiteDaoTest {
	
	private SQLiteDao dao;
	private final File testDatabaseFile = new File("test_database.db");
	
	@Before
	public void setUp() throws SQLException, IOException {
		assertTrue(testDatabaseFile.createNewFile());
		this.dao = new SQLiteDao(testDatabaseFile.getAbsolutePath());
	}
	
	@After
	public void tearDown( ) {
		this.dao.close();
		assertTrue(testDatabaseFile.delete());
	}
	
	@Test
	public void constructorCreatesEntryTable() throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:sqlite:" + testDatabaseFile.getAbsolutePath());
		ResultSet tables = connection.getMetaData().getTables(null, null, null, null);
		
		boolean entryTableExists = false;
		while (tables.next()) {
			if (tables.getString("TABLE_NAME").equals("entry")) {
				entryTableExists = true;
			}
		}
		
		assertTrue(entryTableExists);
	}
	
	@Test
	public void createEntryCreatesRowInTableEntry() throws SQLException {
		this.dao.createEntry(new Entry("Test"));
		
		Connection connection = DriverManager.getConnection("jdbc:sqlite:" + testDatabaseFile.getAbsolutePath());
		Statement statement = connection.createStatement();
		ResultSet entries = statement.executeQuery("SELECT * FROM entry");
		
		assertTrue(entries.next());
		assertTrue(entries.getString("title").equals("Test"));
		
		entries.close();
		statement.close();
		connection.close();
	}
	
	@Test
	public void createEntryReturnsFalseForDuplicateEntry() throws SQLException {
		this.dao.createEntry(new Entry("Test"));
		assertFalse(this.dao.createEntry(new Entry("Test")));
	}
}
