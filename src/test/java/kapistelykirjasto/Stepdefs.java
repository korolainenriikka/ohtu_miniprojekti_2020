
package kapistelykirjasto;

import io.cucumber.java.en.*;
import kapistelykirjasto.ui.*;
import kapistelykirjasto.domain.*;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class Stepdefs {

    private ArrayList<String> inputLines;
    private StubIO io;

    @Given("application is launched")
    public void launchApp() {
        this.inputLines = new ArrayList<>();
    }

    @When("action {string} is chosen")
    public void chooseAction(String action) {
        inputLines.add(action);
    }

    @Then("the system will respond with {string}")
    public void systemRespondsWith(String response) {
        this.io = new StubIO(this.inputLines);
        Application app = new ApplicationLogic("test.db");
        CLI userInterface = new CLI(app, io);
        userInterface.run();
        System.out.println(io.getPrints());

        assertTrue(io.getPrints().contains(response));
    }

}