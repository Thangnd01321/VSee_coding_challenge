# VSee coding challenge

## Overview

This project includes tests for video calls and webchat, as well as API tests. The video call and webchat tests are designed to simulate different user interactions using Selenium Grid and TestNG. The tests use a Facade design pattern along with the Page Object Model to ensure maintainability and readability.

## Video Call and Webchat Tests

### Description

The video call and webchat tests consist of two main scenarios:
1. **Patient Walk-In and Send Webchat:** Simulates a patient entering the system and sending a webchat message.
2. **Provider Interaction:** Simulates a provider waiting for the patient to be ready, then making a call and sending a chat message.

The tests are run in parallel using TestNG, which allows for interactions between test cases. Data-driven testing is used for webchat scenarios to simplify maintenance and extendibility for future test cases, such as group visits.

### Design and Implementation

- **Facade Design Pattern:** Applied for easier interaction with the test components.
- **Page Object Model:** Used for better maintainability and readability.
- **Relative Element Finding:** A custom framework allows locating elements relative to each other. For example:
  ```java
  page.webchat(visitId).findElement(page.chatMessage(message)).waitUntilVisible();


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
   - Open `testconf/web/call_chat.xml` to `{hub_url}` then execute the following Maven command to run the tests:
     ```bash
     mvn clean test -Dsurefire.suiteXmlFiles=testconf/web/call_chat.xml
     ```
   - The test results can be found in the `target/ExtentReport.html` file.

2. **Run API Tests:**
   - Execute the following Maven command to run the API tests:
     ```bash
     mvn clean test -Dsurefire.suiteXmlFiles=testconf/api/github_api_test.xml
     ```
