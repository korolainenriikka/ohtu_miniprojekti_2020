package kapistelykirjasto.domain;

import java.util.ArrayList;
import java.util.List;

import kapistelykirjasto.dao.models.BookModel;
import kapistelykirjasto.dao.models.CourseModel;
import kapistelykirjasto.dao.models.Model;
import kapistelykirjasto.dao.models.VideoModel;
import kapistelykirjasto.util.Result;

public interface Application {

    public Result<String, Integer> createBook(String title, String comment, String author, String ISBN);
    
    public Result<String, Integer> createBook(String title, String comment, String author, String ISBN, int[] courseIds);

    public Result<String, Integer> createVideo(String title, String comment, String url, String duration);
    
    public Result<String, Integer> createVideo(String title, String comment, String url, String duration, int[] courseIds);

    public ArrayList<Entry> getEntries();

    public ArrayList<Book> getBooks();

    public ArrayList<Video> getVideos();

    public boolean deleteEntry(Entry e);

    public boolean deleteBook(int id);

    public boolean deleteVideo(int id);

    public boolean editBook(int id, String title, String comment, String author, String ISBN);

    public boolean editVideo(int id, String title, String comment, String url, String duration);

    public boolean markBookAsRead(int id);

    public boolean markVideoAsRead(int id);

    public ArrayList<Book> getReadBooks();

    public ArrayList<Video> getReadVideos();

    public ArrayList<Entry> getNotReadEntries();

    public ArrayList<Entry> getReadEntries();

    public boolean createCourse(String courseCode, String name);

	public List<Course> getCourses();
}
