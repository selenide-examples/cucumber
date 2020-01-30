package org.selenide.examples.cucumber;

import com.codeborne.selenide.Configuration;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class GoogleSearchStepDefinitions {
  private String keyword;
  
  @Given("an open browser with google.com")
  public void openGoogleSearch() {
    Configuration.reportsFolder = "target/surefire-reports";
    open("https://google.com/ncr");
  }

  @When("a keyword (.*) is entered in input field")
  public void enterKeyword(String keyword) {
    this.keyword = keyword;
    $(By.name("q")).val(keyword).pressEnter();
  }

  @Then("at least top (\\d+) matches should be shown")
  public void topTenMatchesShouldBeShown(int resultsCount) {
    $$("#res .g").shouldHave(sizeGreaterThanOrEqual(resultsCount));
  }

  @Then("the first one should contain (.+)")
  public void theFirstOneShouldContainKeyword(String expectedText) {
    $("#res .g").shouldHave(text(expectedText));
  }
}
