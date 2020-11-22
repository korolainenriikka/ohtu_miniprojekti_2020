package kapistelykirjasto.domain;

public class Entry {
	private String title;

	public Entry(String title) {
		this.title = title;
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
