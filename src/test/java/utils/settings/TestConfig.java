package utils.settings;

import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.Scenario;

import java.nio.file.Path;
import java.nio.file.Paths;

public class TestConfig {

  public static final String TOOL_PATH =
      Paths.get(Paths.get("").toAbsolutePath().toString(), "src", "test", "resources", "tools")
          .toString();
  public static final String RESOURCE_PATH =
      Paths.get(Paths.get("").toString(), "src", "test", "resources").toString();
  public static final String DATA_PATH =
      Paths.get(Paths.get("").toString(), "src", "test", "resources", "data").toString();
  public static final Path TARGET_PATH =
      Paths.get(Paths.get("").toAbsolutePath().toString(), "target");
  public static final Path SCREEN_RECORDING_PATH =
      Paths.get(TARGET_PATH.toString(), "screenrecording");
  public static final Path CUCUMBER_REPORT_PATH =
      Paths.get(TARGET_PATH.toString(), "cucumber-reports");
  // Execution scenario
  public static ThreadLocal<Scenario> scenario = new ThreadLocal<Scenario>();
  public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
  public static String testID;
}
