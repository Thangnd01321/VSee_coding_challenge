package cucumber.runner.web;

import cucumber.runner.core.WebRunner;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = {"classpath:resources/features/web"},
    plugin = {
      "pretty",
      "html:target/cucumber-reports/web/upload/report.html",
      "json:target/cucumber-reports/web/upload/Cucumber.json"
    },
    tags = "@upload",
    glue = {"selenium/steps"},
    monochrome = true)
public class UploadTest extends WebRunner {}
