package kapistelykirjasto.dao;

public interface CourseDao {

    public boolean createCourse(String courseCode, String name);

    public boolean addBookCourseRelation(int courseId, int bookId);

    public boolean addVideoCourseRelation(int courseId, int videoId);

    public void close();
}
