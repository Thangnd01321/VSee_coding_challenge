package cucumber.reporter;

import org.testng.ISuite;
import org.testng.xml.XmlSuite;
import utils.enums.Browser;
import utils.logging.Log;
import utils.testhelper.TestParams;
import utils.testhelper.TestResult;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

public class ApiReporter extends Reporter {

  @Override
  public void generateReport(
      List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
//    super.generateReport(xmlSuites, suites, outputDirectory);

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
