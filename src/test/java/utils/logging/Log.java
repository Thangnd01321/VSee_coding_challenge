package utils.logging;

import io.cucumber.java.Scenario;
import org.apache.log4j.Logger;
import org.testng.Reporter;
import utils.settings.TestConfig;

public class Log {

  // Initialize Log4j instance
  private static final Logger Log = Logger.getLogger(Log.class.getName());

  // Info Level Logs and cucumber report
  public static void info(String message, Scenario scenario) {
    if (scenario != null) {
      scenario.log(message);
    }
    //    if (TestParams.DEBUGGING) {
    //      Log.info(message);
    //    }
  }

  // Info Level Logs but doesn't log to cucumber report
  public static void info(String message) {
    Log.info(message);
  }

  public static void details(String message) {
    if (TestConfig.scenario.get() != null) {
      TestConfig.scenario.get().log(message);
    }
    Reporter.log(message + "<p>");
    //    message = "				" + message;
    //    if (TestParams.DEBUGGING) {
    //      Log.info(message);
    //      System.out.println(message);
    //    }
  }

  public static void action(String message) {
    if (TestConfig.scenario.get() != null) {
      TestConfig.scenario.get().log(message);
    }
    Reporter.log(message + "<p>");
    //    message = "			" + message;
    //    if (TestParams.DEBUGGING) {
    Log.info(message);
    //    }
  }

  // Warn Level Logs
  public static void warn(String message) {
    Log.warn(message);
  }

  // Error Level Logs
  public static void error(String message) {
    Log.error(message);
  }

  // Fatal Level Logs
  public static void fatal(String message) {
    Log.fatal(message);
  }

  // Debug Level Logs
  public static void debug(String message) {
    Log.debug(message);
  }
}
