package utils;

import constants.FrameworkConstants;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

    public static String captureScreenshot(String scenarioName) {
        WebDriver driver = DriverFactory.getDriver();
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HHmmss").format(new Date());
        String fileName = scenarioName.replaceAll("[^a-zA-Z0-9_-]", "_") + "_" + timestamp + ".png";
        
        File dir = new File(FrameworkConstants.SCREENSHOT_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        
        String destinationPath = FrameworkConstants.SCREENSHOT_PATH + fileName;
        File destination = new File(destinationPath);
        
        try {
            Files.copy(source.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Screenshot saved at: " + destinationPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destinationPath;
    }
    
    public static byte[] captureScreenshotAsBytes() {
        return ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
