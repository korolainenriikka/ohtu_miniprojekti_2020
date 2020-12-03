package kapistelykirjasto.dao.models;

public class CourseModel {

    private String courseCode;
    private String name;

    public CourseModel(String courseCode, String name) {
        this.courseCode = courseCode;
        this.name = name;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getName() {
        return name;
    }
}
