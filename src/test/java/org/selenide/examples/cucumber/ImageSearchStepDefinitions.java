package org.selenide.examples.cucumber;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ImageSearchStepDefinitions {
  @When("click {string} link")
  public void chooseImagesAsSearchTarget(String linkText) {
    $("[data-testid=\"tab-label-images\"]")
      .shouldHave(text(linkText))
      .click();
  }

  @When("enter a keyword {string} in input field")
  public void enterKeyword(String keyword) {
    $(By.name("q")).val(keyword).pressEnter();
  }

  @Then("at least top {int} matching images should be shown")
  public void topTenMatchedImagesShouldBeShown(int resultsCount) {
    $$("[data-testid=\"zci-images\"] .tile--img").shouldHave(sizeGreaterThanOrEqual(resultsCount));
  }
}
