package kapistelykirjasto.domain;

public class Entry {
	private String title;
	private String comment;
	//luodaan toisessa taskissa:
	//private List<Tag> tags; Tagilla name
	//private List<Course> courses; Coursella courseCode, name

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
