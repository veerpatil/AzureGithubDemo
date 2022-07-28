import com.aventstack.extentreports.Status;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import main.java.ExtentReportInit;
import main.java.ExtentTestManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import test.mytest.ExtentReportListener;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class MySampleTest {


    WebDriver driver ;
    @BeforeClass()
    @Parameters({"browser"})
    public void browserInit(String browser) throws MalformedURLException, URISyntaxException {
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
        else if(browser.equals("Docker"))
        {
//            ChromeOptions options = new ChromeOptions();
//            options.addArguments("--disable-gpu");
//            options.addArguments("--disable-dev-shm-usage");
//            options.addArguments("--no-sandbox");
//            options.addArguments("--headless");
//            DesiredCapabilities capability = DesiredCapabilities.ch;
//            capability.setPlatform(Platform.LINUX);
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setBrowserName("chrome");
//            caps.setVersion("103.0");
            caps.setPlatform(Platform.LINUX);
//            caps.setCapability(ChromeOptions.CAPABILITY,options);

           driver = new RemoteWebDriver(URI.create("http://localhost:4444/").toURL(), caps);
           driver.get("http://www.google.com");
//            driver.quit();
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
