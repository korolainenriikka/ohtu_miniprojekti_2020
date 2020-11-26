package kapistelykirjasto.domain;

public class Book extends Entry {
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

    public Book(String title, String comment, String author, String ISBN) {
        super(title, comment);
        this.author = author;
        this.ISBN = ISBN;
    }

    public String getAuthor() {
        return author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
}