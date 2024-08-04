package cucumber.runner.web;

import cucumber.runner.core.WebRunner;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = {"classpath:resources/features/web"},
    plugin = {
      "pretty",
      "html:target/cucumber-reports/web/checkbox/report.html",
      "json:target/cucumber-reports/web/checkbox/Cucumber.json"
    },
    tags = "@checkbox",
    glue = {"selenium/steps"},
    monochrome = true)
public class CheckboxTest extends WebRunner {}
