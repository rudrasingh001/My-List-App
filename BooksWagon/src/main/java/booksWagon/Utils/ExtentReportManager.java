package booksWagon.Utils;



import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
 
public class ExtentReportManager {
	private static ExtentReports extent;
 
    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter reporter = new ExtentSparkReporter("ExtentReports/ExtentReport.html");
            reporter.config().setDocumentTitle("Automation Report");
            reporter.config().setReportName("Patient Management Tests");
 
            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Environment", "Staging");
            extent.setSystemInfo("Tester", "Your Name");
        }
        return extent;
    }
}