import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportListener implements ITestListener {

    public static   ExtentReports reports;
   public static    ExtentTest test;


    public void onTestStart(ITestResult result) {

        test =reports.createTest(result.getMethod().getMethodName());
        ExtentTestManager.setTest(test);
        ExtentTestManager.getTest().assignDevice(result.getTestContext().getCurrentXmlTest().getParameter("browser"));
    }

    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest().log(Status.PASS, "Test is successful");
        ExtentTestManager.getTest().assignDevice(result.getTestContext().getCurrentXmlTest().getParameter("browser"));
    }

    public void onTestFailure(ITestResult result) {
        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
        ExtentTestManager.getTest().assignDevice(result.getTestContext().getCurrentXmlTest().getParameter("browser"));
    }

    public void onTestSkipped(ITestResult result) {
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
    }

    public void onStart(ITestContext context) {

        System.out.println( "On Start");
      reports = ExtentReportInit.getReport(context.getCurrentXmlTest().getParameter("browser"));
    }

    public void onFinish(ITestContext context) {
        //ExtentTestManager.getTest().getExtent().flush();
     //  reports.flush();
    }
}
