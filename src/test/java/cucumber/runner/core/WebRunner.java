package cucumber.runner.core;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class WebRunner extends TestRunner {
  @BeforeTest
  public void setUpTest() {}

  @AfterTest
  public void tearDownTest() {}
}
