
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

    @Given("entry with title {string} is added")
    public void entryWithTitleIsAdded(String string) {
        app.createEntry(string);
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
}
