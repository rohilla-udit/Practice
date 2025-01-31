package Assignment;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class TestReport implements ITestListener{
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;

    public void onStart(ITestContext args) {
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/src/test/java/Assignment/Reports/myreport.html");
        sparkReporter.config().setDocumentTitle("Automation Report");
        sparkReporter.config().setReportName("Functional Testing");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Computer Name","localhost" );
        extent.setSystemInfo("environment", "SDET Intern");
        extent.setSystemInfo("Tester Name","Udit" );
        extent.setSystemInfo("os", "MAC OS");
        extent.setSystemInfo("Browser Name","Google Chrome" );
    }

    public void onTestSuccess(ITestResult result) {

        test = extent.createTest(result.getName());
        test.log(Status.PASS, "Test Case Passed is " + result.getName());
    }

    public void onTestFailure(ITestResult result) {
        String failedTest=result.getName();
        test = extent.createTest(result.getName());
        extent.attachReporter(sparkReporter);
        test.log(Status.FAIL, "Test Case Failed is " + result.getName());
        test.log(Status.FAIL, "Test Case Failed cause is " + result.getThrowable());
    }

    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getName());
        test.log(Status.SKIP, "Test Case Skipped is " + result.getName());

    }
    public void onFinish( ITestContext context ) {
        extent.flush();
    }
}