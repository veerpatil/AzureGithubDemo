import com.aventstack.extentreports.Status;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import main.java.ExtentReportInit;
import main.java.ExtentTestManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import test.mytest.ExtentReportListener;

public class MySampleTest {


    WebDriver driver ;
    @BeforeClass()
    @Parameters({"browser"})
    public void browserInit(String browser)
    {
        if(browser.equals("Chrome")) {
            DriverManagerType chrome = DriverManagerType.CHROME;
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-gpu");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--no-sandbox");
            options.addArguments("--headless");
            WebDriverManager.getInstance(chrome).setup();
            driver = new ChromeDriver(options);
        }
        else if(browser.equals("Firefox"))
        {
            DriverManagerType firefox = DriverManagerType.FIREFOX;
            WebDriverManager.getInstance(firefox).setup();
            driver = new FirefoxDriver();
        }
    }

    @Test
    public void Test1()
    {
        driver.get("https://www.google.com");
        System.out.println(driver.getTitle());
        ExtentTestManager.getTest().log(Status.PASS,"Opened website successfully" + driver.getTitle());
    }

    @Test
    public void Test2()
    {
        System.out.println(driver.getTitle());
        ExtentTestManager.getTest().log(Status.PASS,"Title of window" + driver.getTitle());
    }

    @AfterClass
    public void quitDriver()
    {
        driver.quit();
    }

    @BeforeTest
    @Parameters({"browser"})
    public void CreateReport(String browser)
    {
        System.out.println("Before "+ browser);
       ExtentReportListener.reports = ExtentReportInit.getReport(browser);

    }

    @AfterTest
    public void flushReport()
    {

        ExtentReportListener.reports.flush();
    }



}
