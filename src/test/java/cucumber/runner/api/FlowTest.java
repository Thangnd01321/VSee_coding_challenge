package cucumber.runner.api;

import cucumber.runner.core.WebRunner;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = {"classpath:resources/features/api"},
    plugin = {
      "pretty",
      "html:target/cucumber-reports/web/flow/report.html",
      "json:target/cucumber-reports/web/flow/Cucumber.json"
    },
    tags = "@flow",
    glue = {"api/steps"},
    monochrome = true)
public class FlowTest extends WebRunner {}
