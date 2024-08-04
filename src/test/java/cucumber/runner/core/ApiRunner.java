package cucumber.runner.core;

import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import utils.logging.Log;

import java.io.PrintStream;

public class ApiRunner extends TestRunner {
  @BeforeTest
  public void setUpTest() {
  }

  @AfterTest
  public void tearDownTest() {}
}
