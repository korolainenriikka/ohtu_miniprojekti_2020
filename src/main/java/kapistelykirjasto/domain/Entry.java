package kapistelykirjasto.domain;

public abstract class Entry {
	
	public enum Type {
		BOOK, VIDEO
	}
	
	public abstract Type getType();
	
	public abstract int getId();
	
	public abstract String getTitle();
}
