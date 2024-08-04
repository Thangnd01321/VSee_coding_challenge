package cucumber.runner.core;

import io.cucumber.testng.CucumberPropertiesProvider;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import org.apache.log4j.PropertyConfigurator;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.testng.xml.XmlTest;
import utils.logging.Log;
import utils.settings.TestConfig;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Properties;

public class TestRunner {

  protected TestNGCucumberRunner testNGCucumberRunner;
  protected Path propFile =
      Paths.get(Paths.get("").toAbsolutePath().toString(), "target", "env.properties");
  /**
   * Before all, do setup stuffs for the test to run
   *
   * @throws Exception
   */
  @BeforeClass(alwaysRun = true)
  public void setUpClass(ITestContext context) throws Exception {
    // setup logger
    Properties prop = new Properties();
    prop.load(
            Files.newInputStream(Paths.get(Paths.get(TestConfig.RESOURCE_PATH, "configs", "log4j.properties").toString())));
    PropertyConfigurator.configure(prop);

    XmlTest currentXmlTest = context.getCurrentXmlTest();
    Objects.requireNonNull(currentXmlTest);
    CucumberPropertiesProvider properties = currentXmlTest::getParameter;

    testNGCucumberRunner = new TestNGCucumberRunner(this.getClass(), properties);
  }

  @BeforeSuite
  public void beforeSuite() {}

  @AfterSuite
  public void afterSuite() {}

  @Test(groups = {"cucumber"}, description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
  public void scenario(PickleWrapper eventWrapper, FeatureWrapper featureWrapper)
      throws Throwable {

    if (featureWrapper != null) {
      Log.details("Feature: " + featureWrapper);
    }
    testNGCucumberRunner.runScenario(eventWrapper.getPickle());
  }

  @DataProvider
  public Object[][] scenarios() {
    return this.testNGCucumberRunner == null ? new Object[0][0] : this.testNGCucumberRunner.provideScenarios();
  }

  @AfterClass(alwaysRun = true)
  public void tearDownClass() {
    // <---- After feature ----->
    testNGCucumberRunner.finish();
  }
}
