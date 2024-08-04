package reporter;

import org.apache.commons.io.FileUtils;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CustomReportNGListener extends org.uncommons.reportng.HTMLReporter implements IReporter {

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        super.generateReport(xmlSuites, suites, outputDirectory);
        for (ISuite suite : suites) {
            for (ISuiteResult result : suite.getResults().values()) {
                ITestContext context = result.getTestContext();
                for (ITestResult failedTest : context.getFailedTests().getAllResults()) {
                    String screenshotPath = (String) failedTest.getAttribute("screenshotPath");
                    if (screenshotPath != null) {
                        File screenshotFile = new File(screenshotPath);
                        if (screenshotFile.exists()) {
                            // Add a link to the screenshot in the report
                            try {
                                File reportFile = new File(outputDirectory + "/html/" + failedTest.getName() + ".html");
                                String reportContent = FileUtils.readFileToString(reportFile, "UTF-8");
                                String screenshotLink = "<a href='" + screenshotFile.getName() + "'>Screenshot</a>";
                                reportContent = reportContent.replace("</body>", screenshotLink + "</body>");
                                FileUtils.writeStringToFile(reportFile, reportContent, "UTF-8");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }
}
