package utils.testhelper;

import io.cucumber.java.Scenario;
import io.cucumber.java.Status;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.reducers.ReducingMethod;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import selenium.core.DriverManager;
import utils.common.FileUtils;
import utils.settings.TestConfig;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TestResult {
  public static void embedScreenCapture(Scenario scenario, String ssName) throws IOException {

    byte[] screenshot = new byte[0];
    if (DriverManager.getDriver() != null) {
      screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
    }
    scenario.attach(screenshot, "image/png", scenario.getName()); // Stick it in the report
  }

  public static void cucumberHtmlResultWeb(String projectName, Path propPath) {
    File reportOutputDirectory = new File("target");
    List<String> jsonFiles = new ArrayList<>();

    // Find all Cucumber.json files then add into report
    List<File> files =
        FileUtils.listFilesFolder(
            TestConfig.CUCUMBER_REPORT_PATH.toString(), new ArrayList<File>());
    for (File file : files) {
      if (file.getName().equals("Cucumber.json")) {
        jsonFiles.add(file.getAbsolutePath());
      }
    }

    // String buildNumber = "1";

    Configuration configuration = new Configuration(reportOutputDirectory, projectName);
    // optional configuration - check javadoc for details
    // configuration.addPresentationModes(PresentationMode.RUN_WITH_JENKINS);
    // configuration.setBuildNumber(buildNumber);
    // addidtional metadata presented on main page

    // optionally add metadata presented on main page via properties file
    List<String> classificationFiles = new ArrayList<>();
    classificationFiles.add(propPath.toString());
    // classificationFiles.add("properties-2.properties");
    configuration.addClassificationFiles(classificationFiles);
    configuration.addReducingMethod(ReducingMethod.MERGE_FEATURES_BY_ID);
    ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);

    reportBuilder.generateReports();
  }

  public static void embedScreenRecording(Scenario scenario, File record, String type) {
    if (record == null) {
      return;
    }
    String folder = TestConfig.SCREEN_RECORDING_PATH.toString();
    String html =
        "<video width=\"640\" height=\"480\" controls>\n"
            + "  <source src=\""
            + Paths.get(folder, record.getName())
            + "\" type=\"video/mp4\">\n"
            + "</video>";

    scenario.attach(html.getBytes(), type, scenario.getName());
  }
}
