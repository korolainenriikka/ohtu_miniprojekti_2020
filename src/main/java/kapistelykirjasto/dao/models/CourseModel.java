package kapistelykirjasto.dao.models;

public class CourseModel {

    private int id;
    private String courseCode;
    private String name;

    public CourseModel(int id, String courseCode, String name) {
        this.id = id;
        this.courseCode = courseCode;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getName() {
        return name;
    }
}
