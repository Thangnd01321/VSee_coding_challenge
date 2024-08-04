package cucumber.reporter;

import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.xml.XmlSuite;
import org.uncommons.reportng.HTMLReporter;
import utils.testhelper.TestParams;
import utils.testhelper.TestResult;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Reporter extends HTMLReporter {
  protected int testCount = 0;
  protected int pass = 0;
  protected int fail = 0;
  protected int skip = 0;
  protected String suiteName;
  protected Date startDate;
  protected Date endDate;

  @Override
  public void generateReport(
      List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
    System.setProperty("org.uncommons.reportng.escape-output", "false");

    // Iterating over each suite included in the test
    for (ISuite suite : suites) {
      // Following code gets the suite name
      suiteName = suite.getName();
      // Getting the results for the said suite
      Map<String, ISuiteResult> suiteResults = suite.getResults();
      for (ISuiteResult sr : suiteResults.values()) {

        ITestContext tc = sr.getTestContext();
        pass += tc.getPassedTests().getAllResults().size();
        fail += tc.getFailedTests().getAllResults().size();
        skip += tc.getSkippedTests().getAllResults().size();

        // Set startdate and enddate
        if (startDate == null) {
          startDate = tc.getStartDate();
        } else {
          if (startDate.getTime() >= tc.getStartDate().getTime()) {
            startDate = tc.getStartDate();
          }
        }
        if (endDate == null) {
          endDate = tc.getEndDate();
        } else {
          if (endDate.getTime() <= tc.getEndDate().getTime()) {
            endDate = tc.getEndDate();
          }
        }
      }
    }
    // count all test
    testCount = pass + fail + skip;
  }

  public String countTestDuration() {
    long diff = endDate.getTime() - startDate.getTime();
    String duration =
        String.format(
            "%d min, %d sec",
            TimeUnit.MILLISECONDS.toMinutes(diff),
            TimeUnit.MILLISECONDS.toSeconds(diff)
                - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(diff)));
    return duration;
  }

}
