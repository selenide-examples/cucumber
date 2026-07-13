package org.selenide.examples.cucumber;

import org.openqa.selenium.chrome.ChromeOptions;
import org.selenide.videorecorder.core.VideoRecorder;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SimpleReport;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;

import java.nio.file.Path;
import java.util.Optional;

public class TextReport {
  private final SimpleReport report = new SimpleReport();
  private VideoRecorder videoRecorder;

  @BeforeAll
  public static void beforeAllTests() {
    Configuration.reportsFolder = "target/surefire-reports";
    Configuration.downloadsFolder = "target/downloads";
    Configuration.browserCapabilities = new ChromeOptions()
      .addArguments("--disable-blink-features=AutomationControlled");
  }

  @Before
  public void beforeTest(Scenario scenario) {
    scenario.log("Starting " + scenario.getName());
    report.start();

    videoRecorder = new VideoRecorder();
    videoRecorder.start();
  }

  @After
  public void afterTest(Scenario scenario) {
    if (scenario.isFailed()) {
      Optional<Path> video = videoRecorder.finish();
      scenario.log("Finished " + scenario.getName() + ", video: " + video.map(path -> path.toUri().toString()));
    }
    else {
      videoRecorder.cancel();
      scenario.log("Finished " + scenario.getName());
    }
    videoRecorder = null;
    report.finish(scenario.getName());
  }
}
