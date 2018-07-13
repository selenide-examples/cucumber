package org.selenide.examples.cucumber;

import com.codeborne.selenide.Configuration;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class GoogleSearchStepDefinitions {
  private String keyword;
  
  @Given("an open browser with google.com")
  public void openGoogleSearch() {
    Configuration.browser = "chrome";
    open("https://google.com/ncr");
  }

  @When("a keyword (.*) is entered in input field")
  public void enterKeyword(String keyword) {
    this.keyword = keyword;
    $(By.name("q")).val(keyword).pressEnter();
  }

  @Then("at least top (\\d+) matches should be shown")
  public void topTenMatchesShouldBeShown(int resultsCount) {
    $$("#ires .g").shouldHave(sizeGreaterThanOrEqual(resultsCount));
  }

  @Then("the first one should contain (.+)")
  public void theFirstOneShouldContainKeyword(String expectedText) {
    $("#ires .g").shouldHave(text(expectedText));
  }
}
