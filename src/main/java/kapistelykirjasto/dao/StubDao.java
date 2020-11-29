package kapistelykirjasto.dao;

import java.util.ArrayList;

import kapistelykirjasto.dao.models.BookModel;
import kapistelykirjasto.dao.models.Model;
import kapistelykirjasto.dao.models.VideoModel;
import kapistelykirjasto.domain.*;

public class StubDao implements Dao {

    private ArrayList<Model> entries = new ArrayList<>();
    private ArrayList<BookModel> books = new ArrayList<>();
    private ArrayList<VideoModel> videos = new ArrayList<>();
    private boolean closed = false;

    private boolean existsEntry(Model entry) {
        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).equals(entry)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean createBook(String title, String comment, String author, String ISBN) {
        if (closed) {
            return false;
        }

        books.add(new BookModel(books.size(), title, comment, author, ISBN));
        return true;
    }

    @Override
    public boolean createVideo(String title, String comment, String url, String duration) {
        if (closed) {
            return false;
        }

        videos.add(new VideoModel(videos.size(), title, comment, url, duration));
        return true;
    }

    @Override
    public void close() {
        this.closed = true;
    }

    @Override
    public ArrayList<BookModel> getBooks() {
        return this.books;
    }

    @Override
    public ArrayList<VideoModel> getVideos() {
        return this.videos;
    }

    @Override
    public boolean deleteBook(int id) {
        return false;
    }

    @Override
    public boolean deleteVideo(int id) {
        return false;
    }
}
