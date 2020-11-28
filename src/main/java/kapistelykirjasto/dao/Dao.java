package kapistelykirjasto.dao;

import java.util.ArrayList;

import kapistelykirjasto.domain.*;

public interface Dao {

    public boolean createBook(Book book);

    public boolean createVideo(Video video);

    /**
     * Closes the connection to the database. Call when you no longer need this
     * DAO.
     */
    public void close();

    public ArrayList<Book> getBooks();

    public ArrayList<Video> getVideos();

    public boolean deleteBook(int id);

    public boolean deleteVideo(int id);
}
