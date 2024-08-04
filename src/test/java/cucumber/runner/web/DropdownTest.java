package cucumber.runner.web;

import cucumber.runner.core.WebRunner;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = {"classpath:resources/features/web"},
    plugin = {
      "pretty",
      "html:target/cucumber-reports/web/dropdown/report.html",
      "json:target/cucumber-reports/web/dropdown/Cucumber.json"
    },
    tags = "@dropdown",
    glue = {"selenium/steps"},
    monochrome = true)
public class DropdownTest extends WebRunner {}
