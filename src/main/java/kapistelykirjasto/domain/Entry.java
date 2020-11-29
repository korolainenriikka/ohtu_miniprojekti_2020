package kapistelykirjasto.domain;

public abstract class Entry {
	
	private int id;
	private String title;
	private String comment;

	public enum Type {
		BOOK, VIDEO
	}
	
	public abstract Type getType();

	//luodaan toisessa taskissa:
	//private List<Tag> tags; Tagilla name
	//private List<Course> courses; Coursella courseCode, name

	//myöhemmässä versiossa abstract class (kun pelkän entryn lisääminen poistetaan)

	public Entry(String title) {
		this.title = title;
	}

	public Entry(String title, String comment) {
		this.title = title;
		this.comment = comment;
	}

	public String getTitle() {
		return title;
	}

	public String getComment() {
		return comment;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entry other = (Entry) obj;

		return this.title.equals(other.getTitle());
	}
}
