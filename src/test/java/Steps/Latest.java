package Steps;

import Base.Base;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class Latest extends Base {
    @Given("a web browser is at the home page")
    public void aWebBrowserIsAtTheHomePage() {
        getUrl();
    }

    @When("^user searched (.*)$")
    public void userSearchedClassified(String classified) {
        sendKeys(classified,"//input[@placeholder = 'Kelime ile ara...']",Type.xPath, TimeOut.MIDDLE);
    }

    @And("user clicks submit")
    public void userClicksSubmit() {
        findElementAndClick("//span[@class='hidden-xs hidden-sm']",Type.xPath, TimeOut.MIDDLE);
    }

    @Then("user sees classifieds")
    public void userSeesClassifieds() {
        Assert.assertEquals("Satılık Daire Fiyatları ve Satılık Ev İlanları - Zingat", getTitle());
    }
}
