package selenium.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.http.ClientConfig;
import utils.enums.Browser;
import utils.testhelper.TestParams;

public class DriverManager {
  // Driver instance
  private static final ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

  /**
   * Start new browser
   *
   * @return {@link WebDriver}
   */
  public static WebDriver startBrowser() throws MalformedURLException {
    switch (Browser.getBrowser()) {
      case CHROME:
        driver.set(startChrome());
        break;
      case FIREFOX:
        driver.set(startFirefox());
      case EDGE:
        driver.set(startEdge());
        break;
      case IE:
        break;
      case OPERA:
        break;
      case SAFARI:
        break;
      default:
        break;
    }

    driver.get().manage().window().maximize();
    return driver.get();
  }

  /**
   * Start new browser
   *
   * @return {@link WebDriver}
   */
  public static WebDriver startBrowser(Browser browser) throws MalformedURLException {
    switch (browser) {
      case CHROME:
        driver.set(startChrome());
        break;
      case FIREFOX:
        driver.set(startFirefox());
      case EDGE:
        driver.set(startEdge());
        break;
      case IE:
        break;
      case OPERA:
        break;
      case SAFARI:
        break;
      default:
        break;
    }

    driver.get().manage().window().maximize();
    return driver.get();
  }

  public static WebDriver getDriver() {
    return driver.get();
  }

  public static void quitDriver() {
    if (driver.get() != null) {
      driver.get().quit();
      driver.set(null);
    }
  }

  public static void captureScreenshot(String ssPath, String ssName) throws IOException {
    File screenshot = ((TakesScreenshot) driver.get()).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(screenshot, new File(Paths.get(ssPath, ssName).toString()));
  }

  public static WebDriver startEdge() {
    // Edge config
    EdgeOptions options = new EdgeOptions();
    Map<String, Object> prefs = new HashMap<String, Object>();
    prefs.put("safebrowsing.enabled", "false"); // accept file download
    prefs.put("profile.managed_default_content_settings.images", "2");
    prefs.put("disk-cache-size", "4096");
    options.setExperimentalOption("prefs", prefs);
    options.addArguments("enable-automation"); // https://stackoverflow.com/a/43840128/1689770
    options.addArguments("--no-sandbox"); // https://stackoverflow.com/a/50725918/1689770
    options.addArguments("--disable-infobars"); // https://stackoverflow.com/a/43840128/1689770
    options.addArguments("--disable-dev-shm-usage"); // https://stackoverflow.com/a/50725918/1689770
    options.addArguments(
        "--disable-browser-side-navigation"); // https://stackoverflow.com/a/49123152/1689770
    // https://stackoverflow.com/questions/51959986/how-to-solve-selenium-chromedriver-timed-out-receiving-message-from-renderer-exc
    options.addArguments("--disable-web-security"); // Enable using JS on IFrame
    options.addArguments("--ignore-certificate-errors"); // Ignore SSL
    options.addArguments("--incognito"); // Private mode
    options.addArguments("--disable-software-rasterizer");
    options.addArguments("--ignore-gpu-blocklist");
    options.addArguments("--use-gl");
    options.addArguments("--enable-webgl-draft-extensions");
    options.addArguments("--use-fake-ui-for-media-stream"); // To disable the camera and microphone
    // Headless
    if (TestParams.IS_HEADLESS) {
      options.addArguments("--headless");
      options.addArguments("--window-size=1920,1080");
    }

    WebDriverManager.edgedriver().clearDriverCache().setup();

    return new EdgeDriver(options);
  }

  public static WebDriver startChrome() throws MalformedURLException {
    // Chrome config
    ChromeOptions options = new ChromeOptions();
    Map<String, Object> prefs = new HashMap<String, Object>();
//    prefs.put("safebrowsing.enabled", "false"); // accept file download
//    prefs.put("profile.managed_default_content_settings.images", "2");
//    prefs.put("disk-cache-size", "4096");
//    Map<String, Object> profile = new HashMap<>();
//    profile.put("profile.default_content_setting_values.media_stream_camera", 1); // Allow camera
//    profile.put("profile.default_content_setting_values.media_stream_mic", 1); // Allow microphone
//
//    Map<String, Object> permissions = new HashMap<>();
//    permissions.put("origin", "https://team.vsee.me");
//    permissions.put("permissions", new String[] {"geolocation", "camera", "microphone"});
//
//    prefs.put("profile", profile);
//
//    // Create a map for the experimental options
//    prefs.put("hardware.audio_capture_enabled", true);
//    prefs.put("hardware.video_capture_enabled", true);
//    prefs.put("hardware.audio_capture_allowed_urls", new String[] {"https://team.vsee.me"});
//    prefs.put("hardware.video_capture_allowed_urls", new String[] {"https://team.vsee.me"});

//    options.setExperimentalOption("prefs", prefs);
//    options.addArguments("enable-automation"); // https://stackoverflow.com/a/43840128/1689770
//    options.addArguments("--no-sandbox"); // https://stackoverflow.com/a/50725918/1689770
//    options.addArguments("--disable-infobars"); // https://stackoverflow.com/a/43840128/1689770
//    options.addArguments("--disable-dev-shm-usage"); // https://stackoverflow.com/a/50725918/1689770
//    options.addArguments(
//        "--disable-browser-side-navigation"); // https://stackoverflow.com/a/49123152/1689770
    // https://stackoverflow.com/questions/51959986/how-to-solve-selenium-chromedriver-timed-out-receiving-message-from-renderer-exc
//    options.addArguments("--disable-web-security"); // Enable using JS on IFrame
//    options.addArguments("--ignore-certificate-errors"); // Ignore SSL
//    options.addArguments("--incognito"); // Private mode
//    options.addArguments("--disable-software-rasterizer");
//    options.addArguments("--ignore-gpu-blocklist");
//    options.addArguments("--use-gl");
//    options.addArguments("--remote-debugging-pipe");
//    options.addArguments("--remote-debugging-port=9222");
//    options.addArguments("--enable-webgl-draft-extensions");
    options.addArguments("--use-fake-ui-for-media-stream"); // To disable the camera and microphone
    // Set preferences to disable media stream prompts
//    options.addArguments("--use-fake-device-for-media-stream");
//    options.addArguments("--auto-accept-camera-and-microphone-capture");
//    options.setExperimentalOption("useAutomationExtension", false);
    // Headless
    if (TestParams.IS_HEADLESS) {
      options.addArguments("--headless");
      options.addArguments("--window-size=1920,1080");
    }

    DesiredCapabilities capabilities = new DesiredCapabilities();
    //    capabilities.setBrowserName("chrome");
    capabilities.setCapability(ChromeOptions.CAPABILITY, options);

    // Since chrome 114, should clear driver cache first
    WebDriverManager.chromedriver().clearDriverCache().setup();
    return RemoteWebDriver.builder()
        .address(new URL("http://192.168.1.14:4444/wd/hub"))
        .oneOf(options)
        .config(ClientConfig.defaultConfig())
        .build();

//    // Initialize RemoteWebDriver with Chrome options
//    return new RemoteWebDriver(new URL("http://192.168.1.14:4444/wd/hub"), options);
  }

  public static WebDriver startFirefox() {
    FirefoxOptions options = new FirefoxOptions();
    // Headless
    if (TestParams.IS_HEADLESS) {
      options.addArguments("-headless");
      options.addArguments("window-size=1920,1080");
    }

    WebDriverManager.firefoxdriver().clearDriverCache().setup();
    return new FirefoxDriver(options);
  }
}
