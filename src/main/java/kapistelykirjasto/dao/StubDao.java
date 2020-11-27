package kapistelykirjasto.dao;

import java.util.ArrayList;

import kapistelykirjasto.domain.*;

public class StubDao implements Dao {

    private ArrayList<Entry> entries = new ArrayList<>();
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Video> videos = new ArrayList<>();
    private boolean closed = false;

    @Override
    public boolean createEntry(Entry entry) {
        if (closed || existsEntry(entry)) {
            return false;
        }

        entries.add(entry);
        return true;
    }

    private boolean existsEntry(Entry entry) {
        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).equals(entry)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean createBook(Book book) {
        if (closed) {
            return false;
        }

        books.add(book);
        return true;
    }

    @Override
    public boolean createVideo(Video video) {
        if (closed) {
            return false;
        }

        videos.add(video);
        return true;
    }

    @Override
    public void close() {
        this.closed = true;
    }

    public ArrayList<Entry> getEntries() {
        return this.entries;
    }

    @Override
    public ArrayList<Book> getBooks() {
        return this.books;
    }

    @Override
    public ArrayList<Video> getVideos() {
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

    @Override
    public boolean deleteEntryBasedOnTitle(String title) {
        if (!existsEntry(new Entry(title))) {
            return false;
        }
        entries.remove(new Entry(title));
        return true;
    }
}
