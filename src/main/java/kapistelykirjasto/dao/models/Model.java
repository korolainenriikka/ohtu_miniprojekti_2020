package kapistelykirjasto.dao.models;

public abstract class Model {
	
	private int id;
	private String title;
	private String comment;
	
	//luodaan toisessa taskissa:
	//private List<Tag> tags; Tagilla name
	//private List<Course> courses; Coursella courseCode, name

	public Model(int id) {
		this.id = id;
	}
	
	public Model(int id, String title) {
		this(id);
		this.title = title;
	}

	public Model(int id, String title, String comment) {
		this(id, title);
		this.comment = comment;
	}
	
	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getComment() {
		return comment;
	}
}
