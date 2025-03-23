package utils;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Object testClass = result.getInstance();
        WebDriver driver;

        try {
            driver = (WebDriver) testClass.getClass().getField("driver").get(testClass);
        } catch (Exception e) {
            System.out.println("🚫 Driver'a erişilemedi: " + e.getMessage());
            return;
        }

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String methodName = result.getName();
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        File dest = new File("screenshots/" + methodName + "_" + timestamp + ".png");
        dest.getParentFile().mkdirs(); // klasör yoksa oluştur

        try {
            Files.copy(screenshot.toPath(), dest.toPath());
            System.out.println("✅ Screenshot saved: " + dest.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("🚫 Screenshot kaydedilemedi: " + e.getMessage());
        }
    }
}
