package kapistelykirjasto.dao;

import java.util.ArrayList;

import kapistelykirjasto.dao.models.BookModel;
import kapistelykirjasto.dao.models.VideoModel;

public interface Dao {

    public boolean createBook(String title, String comment, String author, String ISBN);

    public boolean createVideo(String title, String comment, String url, String duration);

    /**
     * Closes the connection to the database. Call when you no longer need this
     * DAO.
     */
    public void close();

    public ArrayList<BookModel> getBooks();

    public ArrayList<VideoModel> getVideos();

    public boolean deleteBook(int id);

    public boolean deleteVideo(int id);

    public boolean editBook(int id, String title, String comment, String author, String ISBN);

    public boolean editVideo(int id, String title, String comment, String url, String duration);
}
