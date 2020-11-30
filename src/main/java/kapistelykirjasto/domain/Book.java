package kapistelykirjasto.domain;

import kapistelykirjasto.dao.models.BookModel;

public class Book extends Entry {
	
	private BookModel model;
	
	public Book(BookModel model) {
		this.model = model;
	}
	
	public int getId() {
		return model.getId();
	}

	public String getTitle() {
		return model.getTitle();
	}

	public String getAuthor() {
		return model.getAuthor();
	}

	public String getComment() {
		return model.getComment();
	}

	public String getISBN() {
		return model.getISBN();
	}

	@Override
	public Type getType() {
		return Type.BOOK;
	}

	@Override
	public String toString() {
		String s = getTitle();
		String author = getAuthor();
		if (!author.equals("")) {
			s += "\n\tkirjailija: " + author;
		}
		String isbn = getISBN();
		if (!isbn.equals("")) {
			s += "\n\tISBN: " + isbn;
		}
		String comment = getComment();
		if (!comment.equals("")) {
			s += "\n\tkommentti: " + comment;
		}
		return s;
	}
}
