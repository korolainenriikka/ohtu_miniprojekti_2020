package kapistelykirjasto.domain;

import kapistelykirjasto.dao.*;
import kapistelykirjasto.dao.models.*;

import java.util.ArrayList;

public class ApplicationLogic implements Application {

	private BookDao bookDao;
	private VideoDao videoDao;

	public ApplicationLogic(BookDao bookDao, VideoDao videoDao) {
		this.bookDao = bookDao;
		this.videoDao = videoDao;
	}

	@Override
	public boolean createBook(String title, String comment, String author, String ISBN) {
		if (title.length() == 0 || author.length() == 0 || ISBN.length() == 0) {
			return false;
		}
		return this.bookDao.createBook(title, comment, author, ISBN);
	}

	@Override
	public boolean createVideo(String title, String comment, String url, String duration) {
		if (title.length() == 0 || url.length() == 0) {
			return false;
		}
		return this.videoDao.createVideo(title, comment, url, duration);
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
		for (BookModel model : this.bookDao.getBooks()) {
			books.add(new Book(model));
		}

		return books;
	}

	@Override
	public ArrayList<Video> getVideos() {
		ArrayList<Video> videos = new ArrayList<>();
		for (VideoModel model : this.videoDao.getVideos()) {
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
		if (!this.bookDao.deleteBook(id)) {
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteVideo(int id) {
		if (!this.videoDao.deleteVideo(id)) {
			return false;
		}
		return true;
	}

	@Override
	public boolean editBook(int id, String title, String comment, String author, String ISBN) {
		if (title.length() == 0 || !this.bookDao.editBook(id, title, comment, author, ISBN)) {
			return false;
		}
		return true;
	}

	@Override
	public boolean editVideo(int id, String title, String comment, String url, String duration) {
		if (title.length() == 0 || !this.videoDao.editVideo(id, title, comment, url, duration)) {
			return false;
		}
		return true;
	}

	@Override
	public boolean markBookAsRead(int id) {
		return this.bookDao.markBookAsRead(id);
	}

	@Override
	public boolean markVideoAsRead(int id) {
		return this.videoDao.markVideoAsRead(id);
	}

	@Override
	public ArrayList<Book> getReadBooks() {
		ArrayList<Book> books = new ArrayList<>();
		for (BookModel model : this.bookDao.getReadBooks()) {
			books.add(new Book(model));
		}
		return books;
	}

	@Override
	public ArrayList<Video> getReadVideos() {
		ArrayList<Video> videos = new ArrayList<>();
		for (VideoModel model : this.videoDao.getReadVideos()) {
			videos.add(new Video(model));
		}
		return videos;
	}

	@Override
	public ArrayList<Entry> getNotReadEntries() {
		ArrayList<Entry> notReadEntries = new ArrayList<>();

		for (BookModel model : this.bookDao.getNotReadBooks()) {
			notReadEntries.add(new Book(model));
		}
		for (VideoModel model : this.videoDao.getNotReadVideos()) {
			notReadEntries.add(new Video(model));
		}
		return notReadEntries;
	}

	@Override
	public ArrayList<Entry> getReadEntries() {
		ArrayList<Entry> readEntries = new ArrayList<>();

		for (BookModel model : this.bookDao.getReadBooks()) {
			readEntries.add(new Book(model));
		}
		for (VideoModel model : this.videoDao.getReadVideos()) {
			readEntries.add(new Video(model));
		}
		return readEntries;
	}

}