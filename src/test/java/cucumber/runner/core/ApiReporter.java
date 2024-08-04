package cucumber.runner.core;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.xml.XmlSuite;
import org.uncommons.reportng.HTMLReporter;
import utils.enums.Browser;
import utils.logging.Log;
import utils.testhelper.TestParams;
import utils.testhelper.TestResult;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApiReporter extends HTMLReporter {

  @Override
  public void generateReport(
      List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
    System.setProperty("org.uncommons.reportng.escape-output", "false");

    String suiteName = "";

    // Iterating over each suite included in the test
    for (ISuite suite : suites) {
      // Following code gets the suite name
      suiteName = suite.getName();
    }

    // properties
    Path propFile =
        Paths.get(Paths.get("").toAbsolutePath().toString(), "target", "env.properties");
    Properties prop = new Properties();
    // Store properties
    try {
      prop.store(new FileOutputStream(propFile.toFile()), "");
    } catch (IOException e3) {
      e3.printStackTrace();
      return;
    }

    // Generate cucumber html report
    TestResult.cucumberHtmlResultWeb(suiteName, propFile);
  }
}
