package org.selenide.examples.cucumber;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class GoogleSearchStepDefinitions {
  String keyword;
  
  @Given("an open browser with google.com")
  public void openGoogleSearch() {
    open("http://google.com/en");
  }

  @When("a keyword (.*) is entered in input field")
  public void enterKeyword(String keyword) {
    this.keyword = keyword;
    $(By.name("q")).val(keyword).pressEnter();
  }

  @Then("top (\\d+) matches should be shown")
  public void topTenMatchesShouldBeShown(int resultsCount) {
    $$("#res .g").shouldHave(size(resultsCount));
    $("#res .g").shouldHave(text(keyword + ".org"));
  }
}
