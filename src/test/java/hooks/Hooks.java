package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ConfigReader;
import utils.DriverFactory;
import utils.ScreenshotUtils;

public class Hooks {

    @Before
    public void setup() {
        DriverFactory.initDriver();
        String baseUrl = ConfigReader.getProperty("baseUrl");
        DriverFactory.getDriver().get(baseUrl);
    }

    @After
    public void teardown(Scenario scenario) {
        if (scenario.isFailed()) {
            String screenshotPath = ScreenshotUtils.captureScreenshot(scenario.getName());
            System.out.println("Scenario failed. Screenshot attached: " + screenshotPath);
            byte[] screenshotBytes = ScreenshotUtils.captureScreenshotAsBytes();
            scenario.attach(screenshotBytes, "image/png", "Failed Scenario Screenshot");
        }
        DriverFactory.quitDriver();
    }
}
