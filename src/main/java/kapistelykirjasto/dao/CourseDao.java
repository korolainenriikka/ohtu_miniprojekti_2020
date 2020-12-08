package kapistelykirjasto.dao;

import kapistelykirjasto.dao.models.CourseModel;
import java.util.List;

public interface CourseDao {

    public boolean createCourse(String courseCode, String name);

    public boolean addBookCourseRelation(int courseId, int bookId);

    public boolean addVideoCourseRelation(int courseId, int videoId);

    public List<CourseModel> getCourses();
    
    

    public void close();
}
