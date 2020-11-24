package kapistelykirjasto.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import kapistelykirjasto.domain.Entry;

public interface Dao {
	
	/**
	 * Creates an entry with the given title to the database.
	 * @param title
	 * @return true, if the entry was successfully created, false otherwise.
	 */
	public boolean createEntry(Entry entry);
	
	/**
	 * Closes the connection to the database. Call when you no longer need this DAO.
	 */
	public void close();

	public ArrayList<Entry>getEntries();

	public boolean deleteEntryBasedOnTitle(String title);

}
