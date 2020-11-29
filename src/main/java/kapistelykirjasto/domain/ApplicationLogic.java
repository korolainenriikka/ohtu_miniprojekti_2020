package kapistelykirjasto.domain;

import kapistelykirjasto.dao.Dao;
import kapistelykirjasto.dao.models.BookModel;
import kapistelykirjasto.dao.models.VideoModel;

import java.util.ArrayList;

public class ApplicationLogic implements Application {

	private Dao dao;

	public ApplicationLogic(Dao dao) {
		this.dao = dao;
	}

	@Override
	public boolean createBook(String title, String comment, String author, String ISBN) {
		if (title.length() == 0 || author.length() == 0 || ISBN.length() == 0) {
			return false;
		}
		return this.dao.createBook(title, comment, author, ISBN);
	}

	@Override
	public boolean createVideo(String title, String comment, String url, String duration) {
		if (title.length() == 0 || url.length() == 0) {
			return false;
		}
		return this.dao.createVideo(title, comment, url, duration);
	}

	@Override
	public ArrayList<Entry> getEntries() {
		ArrayList<Entry> entries = new ArrayList<>();
		entries.addAll(this.getBooks());
		entries.addAll(this.getVideos());

		return entries;
	}

	@Override
	public ArrayList<Book> getBooks() {
		ArrayList<Book> books = new ArrayList<>();
		for (BookModel model : this.dao.getBooks()) {
			books.add(new Book(model));
		}

		return books;
	}

	@Override
	public ArrayList<Video> getVideos() {
		ArrayList<Video> videos = new ArrayList<>();
		for (VideoModel model : this.dao.getVideos()) {
			videos.add(new Video(model));
		}

		return videos;
	}

	@Override
	public boolean deleteEntry(Entry e) {

		if (e.getType() == Entry.Type.BOOK) {
			if (deleteBook(e.getId())) {
				return true;
			}
		} else {
			if (deleteVideo(e.getId())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean deleteBook(int id) {
		if (!this.dao.deleteBook(id)) {
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteVideo(int id) {
		if (!this.dao.deleteVideo(id)) {
			return false;
		}
		return true;
	}
}