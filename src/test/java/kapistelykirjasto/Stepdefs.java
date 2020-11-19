
package kapistelykirjasto;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
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
    public void setup(){
        inputLines = new ArrayList<>();
        app = new ApplicationLogic("test.db");
    }

    @Given("application is launched")
    public void launchApp() {
        this.inputLines = new ArrayList<>();
        this.io = new StubIO(this.inputLines);
        userInterface = new CLI(app, io);
    }

    @When("action {string} is chosen")
    public void chooseAction(String action) {
        inputLines.add(action);
        userInterface.run();
    }

    @Then("system will respond with {string}")
    public void systemRespondsWith(String response) {
        System.out.println(io.getPrints());

        assertTrue(io.getPrints().contains(response));
    }

    @Given("entry with title {string} is added")
    public void entryWithTitleIsAdded(String string) {
        app.createEntry(string);
    }

    @When("title {string} is entered")
    public void titleIsEntered(String string) {
        inputLines.add(string);
        userInterface.run();
    }

}