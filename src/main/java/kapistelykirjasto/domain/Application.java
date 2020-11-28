package kapistelykirjasto.domain;

import java.util.ArrayList;

public interface Application {

    public boolean createBook(String title, String comment, String author, String ISBN);
    
    public boolean createVideo(String title, String comment, String url, String duration);

    public ArrayList<Entry> getEntries();

    public ArrayList<Book> getBooks();

    public ArrayList<Video> getVideos();

    public boolean deleteBook(int id);

    public boolean deleteVideo(int id);
}
