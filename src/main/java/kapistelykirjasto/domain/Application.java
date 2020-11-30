package kapistelykirjasto.domain;

import java.util.ArrayList;

import kapistelykirjasto.dao.models.BookModel;
import kapistelykirjasto.dao.models.Model;
import kapistelykirjasto.dao.models.VideoModel;

public interface Application {

    public boolean createBook(String title, String comment, String author, String ISBN);

    public boolean createVideo(String title, String comment, String url, String duration);

    public ArrayList<Entry> getEntries();

    public ArrayList<Book> getBooks();

    public ArrayList<Video> getVideos();

    public boolean deleteEntry(Entry e);

    public boolean deleteBook(int id);

    public boolean deleteVideo(int id);

    //public boolean editEntry(Entry entry);

    public boolean editBook(int id, String title, String comment, String author, String ISBN);

    public boolean editVideo(int id, String title, String comment, String url, String duration);
}
