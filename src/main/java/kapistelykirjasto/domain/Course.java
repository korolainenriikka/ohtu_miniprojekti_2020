package kapistelykirjasto.domain;

import kapistelykirjasto.dao.models.CourseModel;

public class Course {
		
	private CourseModel model;
	
	public Course(CourseModel model) {
		this.model = model;
	}
	
	public int getId() {
		return model.getId();
	}
	
	public String getCourseCode() {
		return model.getCourseCode();
	}

	public String getName() {
		return model.getName();
	}

	@Override
	public String toString() {
		return getCourseCode() + " " + getName();
	}
}
