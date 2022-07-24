package main.java;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.util.Calendar;
import java.util.TimeZone;

public class ExtentReportInit {
    public static Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
    public static long time = cal.getTimeInMillis();
   static ExtentReports extent;
    // Fetches the extent report.
    public static synchronized  ExtentReports getReport(String browser) {
        time = cal.getTimeInMillis()+1;
        String fileName = System.getProperty("user.dir") + "/test-output/index" +  ".html";
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(
                fileName);

        htmlReporter.config(ExtentSparkReporterConfig.builder().
                theme(Theme.DARK).
                documentTitle(fileName).
                timelineEnabled(true).
                timeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'")
                .reportName(fileName)
                .build());
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("OS", "Windows");
        extent.setSystemInfo("AUT", "QA");
        extent.setSystemInfo("Browser", browser);
        return extent;

    }

}
