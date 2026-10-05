package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.time.Duration;

public class DriverFactory {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void initDriver() {
        if (driver.get() == null) {
            String browser = ConfigReader.getProperty("browser").toLowerCase();
            boolean isHeadless = Boolean.parseBoolean(ConfigReader.getProperty("headless"));

            WebDriver webDriver;
            switch (browser) {
                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    if (isHeadless) {
                        edgeOptions.addArguments("--headless");
                    }
                    webDriver = new EdgeDriver(edgeOptions);
                    break;
                case "chrome":
                default:
                    ChromeOptions chromeOptions = new ChromeOptions();
                    if (isHeadless) {
                        chromeOptions.addArguments("--headless=new");
                    }
                    webDriver = new ChromeDriver(chromeOptions);
                    break;
            }

            webDriver.manage().window().maximize();
            
            driver.set(webDriver);
        }
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
