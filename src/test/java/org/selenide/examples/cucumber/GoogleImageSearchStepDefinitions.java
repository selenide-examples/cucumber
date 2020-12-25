package org.selenide.examples.cucumber;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class GoogleImageSearchStepDefinitions {
  private String keyword;
  
  @When("click \"Images\" link")
  public void chooseImagesAsSearchTarget() {
    $(byText("Images")).click();
  }

  @When("enter a keyword {string} in input field")
  public void enterKeyword(String keyword) {
    this.keyword = keyword;
    $(By.name("q")).val(keyword).pressEnter();
  }

  @Then("at least top {int} matching images should be shown")
  public void topTenMatchedImagesShouldBeShown(int resultsCount) {
    $$(".rg_i").shouldHave(sizeGreaterThanOrEqual(resultsCount));
  }
}
