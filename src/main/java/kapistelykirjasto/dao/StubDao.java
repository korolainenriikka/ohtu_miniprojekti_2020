package kapistelykirjasto.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import kapistelykirjasto.dao.models.BookModel;
import kapistelykirjasto.dao.models.CourseModel;
import kapistelykirjasto.dao.models.Model;
import kapistelykirjasto.dao.models.VideoModel;
import kapistelykirjasto.domain.*;

public class StubDao implements BookDao, VideoDao, CourseDao {

    private ArrayList<Model> entries = new ArrayList<>();
    private ArrayList<BookModel> books = new ArrayList<>();
    private ArrayList<VideoModel> videos = new ArrayList<>();
    private ArrayList<CourseModel> courses = new ArrayList<>();
    private ArrayList<BookModel> notReadBooks = new ArrayList<>();
    private ArrayList<VideoModel> notReadVideos = new ArrayList<>();
    private ArrayList<BookModel> readBooks = new ArrayList<>();
    private ArrayList<VideoModel> readVideos = new ArrayList<>();
    private HashMap<Integer, Integer> bookCourseRelation = new HashMap<>();
    private HashMap<Integer, Integer> videoCourseRelation = new HashMap<>();
    private boolean closed = false;

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

    private boolean existsBook(int id) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == id) {
                return true;
            }
        }
        return false;
    }

    private boolean existsVideo(int id) {
        for (int i = 0; i < videos.size(); i++) {
            if (videos.get(i).getId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteBook(int id) {
        if (!existsBook(id)) {
            return false;
        }
        books.removeIf(book -> book.getId() == id);
        return true;
    }

    @Override
    public boolean deleteVideo(int id) {
        if (!existsVideo(id)) {
            return false;
        }
        videos.removeIf(video -> video.getId() == id);
        return true;
    }

    @Override
    public boolean editBook(int id, String title, String comment, String author, String ISBN) {
        if (closed) {
            return false;
        }
        BookModel editedBook = new BookModel(id, title, comment, author, ISBN);

        for (int i = 0; i < this.books.size(); i++) {
            if (this.books.get(i).getId() == id) {
                this.books.remove(i);
                this.books.add(editedBook);
            }
        }
        return true;
    }

    @Override
    public boolean editVideo(int id, String title, String comment, String url, String duration) {

        if (closed) {
            return false;
        }
        VideoModel editedVideo = new VideoModel(id, title, comment, url, duration);

        for (int i = 0; i < this.videos.size(); i++) {
            if (this.videos.get(i).getId() == id) {
                this.videos.remove(i);
                this.videos.add(editedVideo);
            }
        }
        return true;
    }

    @Override
    public boolean markBookAsRead(int id) {

        if (closed || this.books.size() < id) {
            return false;
        }
        this.notReadBooks.add(this.books.get(id));
        return true;
    }

    @Override
    public boolean markVideoAsRead(int id) {

        if (closed || this.videos.size() < id) {
            return false;
        }
        this.notReadVideos.add(this.videos.get(id));
        return true;
    }

    @Override
    public ArrayList<BookModel> getReadBooks() {
        return this.readBooks;
    }

    @Override
    public ArrayList<VideoModel> getReadVideos() {
        return this.readVideos;
    }

    @Override
    public ArrayList<BookModel> getNotReadBooks() {
        return this.notReadBooks;
    }

    @Override
    public ArrayList<VideoModel> getNotReadVideos() {
        return this.notReadVideos;
    }

    @Override
    public boolean createCourse(String courseCode, String name) {
        if (closed) {
            return false;
        }
        courses.add(new CourseModel(courses.size(), courseCode, name));
        return true;
    }

    @Override
    public boolean addBookCourseRelation(int courseId, int bookId) {
        bookCourseRelation.put(courseId, bookId);
        if (bookCourseRelation.get(courseId) != bookId) {
            return false;
        }
        return true;
    }

    @Override
    public boolean addVideoCourseRelation(int courseId, int videoId) {
        videoCourseRelation.put(courseId, videoId);
        if (bookCourseRelation.get(courseId) != videoId) {
            return false;
        }
        return true;
    }

    @Override
    public List<CourseModel> getCourses() {
        return this.courses;
    }
}

