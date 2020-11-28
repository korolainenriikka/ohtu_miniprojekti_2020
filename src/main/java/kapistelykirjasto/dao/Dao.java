package kapistelykirjasto.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import kapistelykirjasto.domain.*;

public interface Dao {

    /**
     * Creates an entry with the given title to the database.
     *
     * @param title
     * @return true, if the entry was successfully created, false otherwise.
     */
    public boolean createEntry(Entry entry);

    public boolean createBook(Book book);

    public boolean createVideo(Video video);

    /**
     * Closes the connection to the database. Call when you no longer need this
     * DAO.
     */
    public void close();

    public ArrayList<Entry> getEntries();

    public ArrayList<Book> getBooks();

    public ArrayList<Video> getVideos();

    public boolean deleteBook(int id);

    public boolean deleteVideo(int id);

    public boolean deleteEntryBasedOnTitle(String title);

}
