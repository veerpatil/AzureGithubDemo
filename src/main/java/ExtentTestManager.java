import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {

    public static ThreadLocal<ExtentTest>  extentTestThreadSafe = new ThreadLocal<ExtentTest>();

    //getting the test
    public static synchronized ExtentTest getTest()
    {
        return extentTestThreadSafe.get();
    }

    //setting the test
    public static void setTest(ExtentTest tst) {

        extentTestThreadSafe.set(tst);
    }
}
