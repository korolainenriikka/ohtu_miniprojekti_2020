package kapistelykirjasto.dao.models;


public class BookModel extends Model {
    /*
    SPEKSISTÄ ESIMERKKIKIRJA:
        Kirjoittaja: Robert Martin /book
        Otsikko: Clean Code: A Handbook of Agile Software Craftsmanship /entry
        Tyyppi: Kirja
        ISBN: 978-0132350884 /book
        Tagit: Ohjelmointi, design patterns /entry
        Related courses: TKT20006 Ohjelmistotuotanto /entry
     */
	
	private String author;
	private String ISBN;

	public BookModel(int id, String title, String comment, String author, String ISBN) {
		super(id, title, comment);
		this.author = author;
		this.ISBN = ISBN;
	}

	public String getAuthor() {
		return author;
	}

	public String getISBN() {
		return ISBN;
	}

}