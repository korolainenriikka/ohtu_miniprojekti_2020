
package kapistelykirjasto;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import kapistelykirjasto.dao.SQLiteDao;
import kapistelykirjasto.ui.*;
import kapistelykirjasto.domain.*;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class Stepdefs {

    private ArrayList<String> inputLines;
    private StubIO io;
    Application app;
    CLI userInterface;

    @Before
    public void setup() {
        this.inputLines = new ArrayList<>();
        app = new ApplicationLogic(new SQLiteDao(":memory:"));
    }

    @Given("book with title {string} is added")
    public void bookWithTitleIsAdded(String string) {
        app.createBook(string, string, string, string);
    }

    @Given("book with title {string}, author {string}, ISBN {string} and comment {string} is added")
    public void bookWithTitleAuthorAndIsbnIsAdded(String title, String author, String isbn, String comment) {
        app.createBook(title, comment, author, isbn);
    }

    @Given("video with title {string} is added")
    public void videoWithTitleIsAdded(String string) {
        app.createVideo(string, string, string, string);
    }

    @When("action {string} is chosen")
    public void chooseAction(String action) {
        inputLines.add(action);
    }

    @When("type {string} is selected")
    public void chooseType(String type) {
        inputLines.add(type);
    }

    @When("title {string} is entered")
    public void titleIsEntered(String string) {
        inputLines.add(string);
    }

    @When("title {string}, author {string}, ISBN {string} and comment {string} is entered")
    public void bookParamsEntered(String title, String author, String ISBN, String comment) {
        addBookParams(title, author, ISBN, comment);
    }

    @When("title {string}, author {string}, ISBN {string} and no comment is entered")
    public void bookParamsWithoutCommentEntered(String title, String author, String ISBN) {
        addBookParams(title, author, ISBN, "");
    }

    @When("no title, author {string}, ISBN {string} and comment {string} is entered")
    public void bookParamsWithoutTitleEntered(String author, String ISBN, String comment) {
        addBookParams("", author, ISBN, comment);
    }
    
    @When("title {string} and url {string} is entered")
    public void videoRequiredParamsEntered(String title, String url) {
        addVideoParams(title, url, "", "");
    }
    
    @When("title {string}, url {string}, duration {string} and comment {string} is entered")
    public void videoAllParamsEntered(String title, String url, String duration, String comment) {
        addVideoParams(title, url, duration, comment);
    }
    
    @When("existing entry {string} is selected")
    public void deleteExistingEntry(String entry) {
        app.createVideo("What is Coding?", "www.youtube.com/watch?v=cKhVupvyhKk", "1:15", "Opettavainen!");
        inputLines.add(entry);
    }

    @When("non-existent entry {string} is selected")
    public void deleteNonexistentEntry(String entry) {
        inputLines.add(entry);
    }

    public void addBookParams(String title, String author, String ISBN, String comment) {
        inputLines.add(title);
        inputLines.add(author);
        inputLines.add(ISBN);
        inputLines.add(comment);
    }
    
    
    public void addVideoParams(String title, String url, String duration, String comment) {
        inputLines.add(title);
        inputLines.add(url);
        inputLines.add(duration);
        inputLines.add(comment);
    }

    @Then("system will respond with {string}")
    public void systemRespondsWith(String response) {
        this.io = new StubIO(this.inputLines);
        userInterface = new CLI(app, io);
        userInterface.run();

        String ioResponse = "";
        for (int i = 0; i < io.getPrints().size(); i++) {
            ioResponse += io.getPrints().get(i);
        }

        assertTrue(ioResponse.contains(response));
    }

    @Then("book with title {string}, author {string}, ISBN {string} and comment {string} exists")
    public void bookWithParamsExists(String title, String author, String isbn, String comment) {
        StringBuilder sb = new StringBuilder("");
        for (Book b : app.getBooks()) {
            sb.append(b.toString());
        }
        String s = sb.toString();
        assertTrue(s.contains(title));
        assertTrue(s.contains(author));
        assertTrue(s.contains(isbn));
        assertTrue(s.contains(comment));
    }
}
