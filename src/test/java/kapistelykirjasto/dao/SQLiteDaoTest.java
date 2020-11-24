package kapistelykirjasto.dao;

import java.io.*;
import java.sql.*;

import kapistelykirjasto.domain.Entry;

import org.junit.*;

import static org.junit.Assert.*;

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

	@Test
	public void getEntriesReturnsRightSizeList() throws SQLException {
		this.dao.createEntry(new Entry("Test1"));
		this.dao.createEntry(new Entry("Test2"));
		this.dao.createEntry(new Entry("Test3"));

		assertEquals(3,this.dao.getEntries().size());
	}

	@Test
	public void getEntriesReturnsEmptyListWhenNoEntriesInDb() throws SQLException {
		assertEquals(0,this.dao.getEntries().size());
	}

	@Test
	public void getEntriesReturnsListContainingAllAddedEntries() {
		this.dao.createEntry(new Entry("Test1"));
		this.dao.createEntry(new Entry("Test2"));

		assertEquals("Test1", this.dao.getEntries().get(0).getTitle());
		assertEquals("Test2", this.dao.getEntries().get(1).getTitle());
	}
	@Test
	public void createEntryReturnsFalseWhenDatabaseIsClosed() throws SQLException {
		this.dao.close();
		assertFalse(this.dao.createEntry(new Entry("Test")));
	}

	@Test
	public void deleteEntryBasedOnTitleReturnsFalseWhenGivenTitleIsNotInDb() {
		assertFalse(this.dao.deleteEntryBasedOnTitle("Not here"));
	}

	@Test
	public void deleteEntryBasedOnTitleReturnsTrueWhenGivenTitleIsInDb() {
		this.dao.createEntry(new Entry("Testbook"));
		assertTrue(this.dao.deleteEntryBasedOnTitle("Testbook"));
	}

	@Test
	public void deleteEntryBasedOnTitleReturnsFalseWhenDbClosed() {
		this.dao.close();
		assertFalse(this.dao.deleteEntryBasedOnTitle("TestTitle"));
	}

	@Test
	public void deleteEntryBasedOnTitleReturnsFalseIfNoTitleGiven() {
		assertFalse(this.dao.deleteEntryBasedOnTitle(""));
	}
}
