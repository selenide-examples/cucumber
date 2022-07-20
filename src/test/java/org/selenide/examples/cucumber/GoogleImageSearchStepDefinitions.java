package org.selenide.examples.cucumber;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import org.openqa.selenium.By;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GoogleImageSearchStepDefinitions {
  @When("click {string} link")
  public void chooseImagesAsSearchTarget(String linkText) {
    $(byText(linkText)).click();
    if ($(byText("Accept all")).isDisplayed()) {
      $(byText("Accept all")).shouldBe(visible).click();
      $(byText("Accept all")).should(disappear);
    }
  }

  @When("enter a keyword {string} in input field")
  public void enterKeyword(String keyword) {
    $(By.name("q")).val(keyword).pressEnter();
  }

  @Then("at least top {int} matching images should be shown")
  public void topTenMatchedImagesShouldBeShown(int resultsCount) {
    $$(".rg_i").shouldHave(sizeGreaterThanOrEqual(resultsCount));
  }
}
