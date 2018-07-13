package org.selenide.examples.cucumber;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit.TextReport;
import cucumber.api.junit.Cucumber;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class GoogleTest {
  @Rule
  public TestRule report = new TextReport().onFailedTest(true).onSucceededTest(true);

  @Before
  public void setUp() {
    Configuration.startMaximized = false;
    Configuration.reportsFolder = "target/surefire-reports";
  }
}
