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

	@Override
	public boolean editBook(int id, String title, String comment, String author, String ISBN) {
		if (title.length() == 0 || !this.dao.editBook(id, title, comment, author, ISBN)) {
			return false;
		}
		return true;
	}

	@Override
	public boolean editVideo(int id, String title, String comment, String url, String duration) {
		if (title.length() == 0 || !this.dao.editVideo(id, title, comment, url, duration)) {
			return false;
		}
		return true;
	}

	@Override
	public boolean markBookAsRead(int id) {
		return this.dao.markBookAsRead(id);

	}

	@Override
	public boolean markVideoAsRead(int id) {
		return this.dao.markVideoAsRead(id);
	}

	@Override
	public ArrayList<Book> getReadBooks() {
		ArrayList<Book> books = new ArrayList<>();
		for (BookModel model : this.dao.getReadBooks()) {
			books.add(new Book(model));
		}
		return books;
	}

	@Override
	public ArrayList<Video> getReadVideos() {
		ArrayList<Video> videos = new ArrayList<>();
		for (VideoModel model : this.dao.getReadVideos()) {
			videos.add(new Video(model));
		}
		return videos;
	}

	@Override
	public ArrayList<Entry> getNotReadEntries() {
		ArrayList<Entry> notReadEntries = new ArrayList<>();

		for (BookModel model : this.dao.getNotReadBooks()) {
			notReadEntries.add(new Book(model));
		}
		for (VideoModel model : this.dao.getNotReadVideos()) {
			notReadEntries.add(new Video(model));
		}
		return notReadEntries;
	}

	@Override
	public ArrayList<Entry> getReadEntries() {
		ArrayList<Entry> readEntries = new ArrayList<>();

		for (BookModel model : this.dao.getReadBooks()) {
			readEntries.add(new Book(model));
		}
		for (VideoModel model : this.dao.getReadVideos()) {
			readEntries.add(new Video(model));
		}
		return readEntries;
	}

}