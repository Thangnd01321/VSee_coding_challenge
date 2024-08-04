package cucumber.runner.api;

import cucumber.runner.core.WebRunner;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = {"classpath:resources/features/api"},
    plugin = {
      "pretty",
      "html:target/cucumber-reports/web/create_user/report.html",
      "json:target/cucumber-reports/web/create_user/Cucumber.json"
    },
    tags = "@create_user",
    glue = {"api"},
    monochrome = true)
public class CreateUserTest extends WebRunner {}
