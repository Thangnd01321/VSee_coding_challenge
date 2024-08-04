package cucumber.runner.web;

import cucumber.runner.core.WebRunner;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = {"classpath:resources/features/web"},
    plugin = {
      "pretty",
      "html:target/cucumber-reports/web/login/report.html",
      "json:target/cucumber-reports/web/login/Cucumber.json"
    },
    tags = "@login",
    glue = {"selenium/steps"},
    monochrome = true)
public class LoginTest extends WebRunner {}
