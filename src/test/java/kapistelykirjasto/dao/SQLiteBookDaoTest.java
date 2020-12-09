package kapistelykirjasto.dao;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

import kapistelykirjasto.dao.models.BookModel;
import kapistelykirjasto.dao.models.VideoModel;
import kapistelykirjasto.dao.models.CourseModel;
import org.junit.*;

import static org.junit.Assert.*;

public class SQLiteBookDaoTest {

    private SQLiteBookDao dao;
    private SQLiteCourseDao courseDao;
    private final File testDatabaseFile = new File("test_database.db");

    @Before
    public void setUp() throws SQLException, IOException {
        assertTrue(testDatabaseFile.createNewFile());
        this.dao = new SQLiteBookDao(testDatabaseFile.getAbsolutePath());
        this.courseDao = new SQLiteCourseDao(testDatabaseFile.getAbsolutePath());
    }

    @After
    public void tearDown() {
        this.dao.close();
        assertTrue(testDatabaseFile.delete());
    }

    @Test
    public void constructorCreatesBookTable() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + testDatabaseFile.getAbsolutePath());
        ResultSet tables = connection.getMetaData().getTables(null, null, null, null);

        boolean entryTableExists = false;
        while (tables.next()) {
            if (tables.getString("TABLE_NAME").equals("book")) {
                entryTableExists = true;
            }
        }

        assertTrue(entryTableExists);
    }

    @Test
    public void createBookCreatesRowInTableBook() throws SQLException {
        this.dao.createBook("Clean Code: A Handbook of Agile Software Craftsmanship",
                "comments here",
                "Robert Martin",
                "978-0132350884");

        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + testDatabaseFile.getAbsolutePath());
        Statement statement = connection.createStatement();
        ResultSet books = statement.executeQuery("SELECT * FROM book");

        assertTrue(books.next());
        assertTrue(books.getString("title").equals("Clean Code: A Handbook of Agile Software Craftsmanship"));

        books.close();
        statement.close();
        connection.close();
    }

    @Test
    public void getBooksReturnsRightSizeList() throws SQLException {
        this.dao.createBook("Elements of the Theory of Computation",
                "Selkeät selitykset",
                "Harry R. Lewis",
                "135-896577-E");
        this.dao.createBook("Database System Concepts",
                "Todella pitkä kirja",
                "Henry F. Fort",
                "007-124476-X");
        this.dao.createBook("Hello Ruby!",
                "Sopii lapsille!",
                "Linda Liukas",
                "032-135522-K");

        assertEquals(3, this.dao.getBooks().size());
    }

    @Test
    public void getBooksReturnsListContainingAllAddedBooks() {
        this.dao.createBook("Elements of the Theory of Computation",
                "Selkeät selitykset",
                "Harry R. Lewis",
                "135-896577-E");
        this.dao.createBook("Database System Concepts",
                "Todella pitkä kirja",
                "Henry F. Fort",
                "007-124476-X");
        this.dao.createBook("Hello Ruby!",
                "Sopii lapsille!",
                "Linda Liukas",
                "032-135522-K");

        assertEquals("Elements of the Theory of Computation", this.dao.getBooks().get(0).getTitle());
        assertEquals("Database System Concepts", this.dao.getBooks().get(1).getTitle());
        assertEquals("Hello Ruby!", this.dao.getBooks().get(2).getTitle());
    }

    @Test
    public void getBooksReturnsEmptyListWhenNoBooksInDb() throws SQLException {
        assertEquals(0, this.dao.getBooks().size());
    }

    @Test
    public void getBooksReturnsNullWhenDatabaseClosed() {
        this.dao.close();
        assertEquals(null, this.dao.getBooks());
    }

    @Test
    public void getVideosReturnsEmptyListWhenNoVideosInDb() throws SQLException {
        assertEquals(0, this.dao.getBooks().size());
    }

    @Test
    public void createBookReturnsFalseWhenDatabaseIsClosed() throws SQLException {
        this.dao.close();
        assertTrue(this.dao.createBook("Clean Code: A Handbook of Agile Software Craftsmanship",
                "comments here",
                "Robert Martin",
                "978-0132350884").isError());
    }

    @Test
    public void deleteBookDeletesBook() {
        this.dao.createBook("Database System Concepts",
                "Todella pitkä kirja",
                "Henry F. Fort",
                "007-124476-X");
        this.dao.deleteBook(this.dao.getBooks().get(0).getId());
        assertEquals(0, this.dao.getBooks().size());
    }

    @Test
    public void existsBookReturnsFalseWhenDatabaseClosed() {
        this.dao.createBook("Hello Ruby!",
                "Sopii lapsille!",
                "Linda Liukas",
                "032-135522-K");
        int id = this.dao.getBooks().get(0).getId();
        this.dao.close();
        assertFalse(this.dao.deleteBook(id));
    }

    @Test
    public void editBookUpdatesValues() {
        this.dao.createBook("Hello uby!",
                "Sopii kaikille!",
                "Linda",
                "032-135522-S");
        int id = this.dao.getBooks().get(0).getId();
        this.dao.editBook(id,
                "Hello Ruby!",
                "Sopii erityisesti lapsille!",
                "Linda Liukas",
                "032-135522-K");
        BookModel b = this.dao.getBooks().get(0);
        assertEquals(b.getAuthor(), "Linda Liukas");
        assertEquals(b.getTitle(), "Hello Ruby!");
        assertEquals(b.getComment(), "Sopii erityisesti lapsille!");
        assertEquals(b.getISBN(), "032-135522-K");
    }

    @Test
    public void editBookReturnsFalseIfBookDoesNotExist() {
        assertFalse(this.dao.editBook(5,
                "Hello Ruby!",
                "Sopii erityisesti lapsille!",
                "Linda Liukas",
                "032-135522-K"));
    }

    @Test
    public void getNotReadBooksReturnsOnlyNotReadBooks() {
        this.dao.createBook("Elements of the Theory of Computation",
                "Selkeät selitykset",
                "Harry R. Lewis",
                "135-896577-E");
        this.dao.createBook("Database System Concepts",
                "Todella pitkä kirja",
                "Henry F. Fort",
                "007-124476-X");
        int id1 = this.dao.getBooks().get(0).getId();
        int id2 = this.dao.getBooks().get(1).getId();

        this.dao.markBookAsRead(id1);
        ArrayList<BookModel> notReadBooks = this.dao.getNotReadBooks();
        assertEquals(notReadBooks.get(0).getTitle(), "Database System Concepts");
        assertEquals(notReadBooks.size(), 1);
    }

    @Test
    public void getReadBooksReturnsOnlyReadBooks() {
        this.dao.createBook("Elements of the Theory of Computation",
                "Selkeät selitykset",
                "Harry R. Lewis",
                "135-896577-E");
        this.dao.createBook("Database System Concepts",
                "Todella pitkä kirja",
                "Henry F. Fort",
                "007-124476-X");
        int id1 = this.dao.getBooks().get(0).getId();
        int id2 = this.dao.getBooks().get(1).getId();

        this.dao.markBookAsRead(id1);
        ArrayList<BookModel> readBooks = this.dao.getReadBooks();
        assertEquals(readBooks.get(0).getTitle(), "Elements of the Theory of Computation");
        assertEquals(readBooks.size(), 1);
    }

    @Test
    public void addedBooksAreAutomaticallyNotRead() {
        this.dao.createBook("Elements of the Theory of Computation",
                "Selkeät selitykset",
                "Harry R. Lewis",
                "135-896577-E");
        this.dao.createBook("Database System Concepts",
                "Todella pitkä kirja",
                "Henry F. Fort",
                "007-124476-X");

        assertEquals(this.dao.getNotReadBooks().size(), 2);
    }

    @Test
    public void courseBooksReturnsRightList() {
        this.dao.createBook("Clean Code: A Handbook of Agile Software Craftsmanship",
                "comments here",
                "Robert Martin",
                "978-0132350884");
        int bookId = this.dao.getBooks().get(0).getId();
        System.out.println("kirja id:" + bookId);

        this.courseDao.createCourse("TKT20006", "Ohjelmistotuotanto");
        int courseId = this.courseDao.getCourses().get(0).getId();
        
        this.courseDao.addBookCourseRelation(courseId, bookId);

        assertEquals(this.dao.getCourseBooks(courseId).size(), 1);
    }
}
