# Project README

## Running Video Call and Webchat Tests

To execute the video call and webchat tests, follow these steps:

### Prerequisites
1. **Download Selenium Server:**
   - Download the Selenium Server JAR file from [Selenium Downloads](https://www.selenium.dev/downloads/).

### Setting Up Selenium Grid

1. **Start the Selenium Hub:**
   - On one machine, run the following command to start the Selenium Hub:
     ```bash
     java -jar selenium-server-{version}.jar hub
     ```

2. **Start Selenium Nodes:**
   - On each of the two other machines, run the following command to start a Selenium Node. Make sure to replace `{hub_ip}` with the IP address of the machine where the hub is running:
     ```bash
     java -jar selenium-server-{version}.jar node --hub http://{hub_ip}:4444 --max-sessions 1 --port 5555
     ```

### Running the Tests

1. **Run Video Call and Webchat Tests:**
   - Execute the following Maven command to run the tests:
     ```bash
     mvn clean test -Dsurefire.suiteXmlFiles=testconf/web/call_chat.xml
     ```
   - The test results can be found in the `target/ExtentReport.html` file.

2. **Run API Tests:**
   - Execute the following Maven command to run the API tests:
     ```bash
     mvn clean test -Dsurefire.suiteXmlFiles=testconf/api/github_api_test.xml
     ```
