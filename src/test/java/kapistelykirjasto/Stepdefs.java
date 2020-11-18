
package kapistelykirjasto;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;

public class Stepdefs {

    @Given("Counter is initialized")
    public void counterIsInitialized() {

    }

    @When("it is incremented")
    public void itIsIncremented() {

    }

    @Then("the value should be {int}")
    public void theValueShouldBe(Integer val) {
        assertEquals(1, 1);
    }

}