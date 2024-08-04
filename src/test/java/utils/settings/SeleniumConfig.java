package utils.settings;

import java.nio.file.Path;
import java.nio.file.Paths;

public class SeleniumConfig {
  // Property
  public static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
  public static final String FIREFOX_DRIVER_PROPERTY = "webdriver.gecko.driver";
  public static final String IE_DRIVER_PROPERTY = "webdriver.ie.driver";
  public static final String EDGE_DRIVER_PROPERTY = "webdriver.edge.driver";
  private static final String SELENIUM_CONFIG_PATH =
      Paths.get(
              Paths.get("").toAbsolutePath().toString(),
              "src",
              "test",
              "resources",
              "configs",
              "Selenium.properties")
          .toString();
  private static final ConfigReader CONFIG = new ConfigReader(SELENIUM_CONFIG_PATH);
  private static final String CHROME_DRIVER_MAC = CONFIG.getValue("chrome.driver.macos");
  private static final String CHROME_DRIVER_WINDOWS = CONFIG.getValue("chrome.driver.windows");
  private static final String FIREFOX_DRIVER_MAC = CONFIG.getValue("firefox.driver.macos");
  private static final String FIREFOX_DRIVER_WINDOWS = CONFIG.getValue("firefox.driver.windows");
  private static final Path DRIVER_PATH =
      Paths.get(Paths.get("").toAbsolutePath().toString(), "src", "test", "resources", "drivers");

  public SeleniumConfig() {}

  public static String getChromeBinary() {
    if (System.getProperty("os.name").startsWith("Mac")) {
      return Paths.get(DRIVER_PATH.toString(), CHROME_DRIVER_MAC).toString();
    } else {
      return Paths.get(DRIVER_PATH.toString(), CHROME_DRIVER_WINDOWS).toString();
    }
  }

  public static String getFirefoxBinary() {
    if (System.getProperty("os.name").startsWith("Mac")) {
      return Paths.get(DRIVER_PATH.toString(), FIREFOX_DRIVER_MAC).toString();
    } else {
      return Paths.get(DRIVER_PATH.toString(), FIREFOX_DRIVER_WINDOWS).toString();
    }
  }
}
