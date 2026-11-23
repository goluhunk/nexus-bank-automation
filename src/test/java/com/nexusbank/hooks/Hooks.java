package com.nexusbank.hooks;

import com.nexusbank.utility.ConfigReader;
import com.nexusbank.utility.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {
    @Before
    public void beforeScenario(Scenario scenario) {
        String browser = System.getProperty("browser", ConfigReader.get("browser"));
        DriverFactory.startDriver(browser);
        System.out.printf("%n[%s] browser=%s environment=%s%n",
                scenario.getName(), browser, System.getProperty("environment", "dev"));
        DriverFactory.getDriver().get(ConfigReader.get("base.url"));
    }

    @After
    public void afterScenario(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver())
                        .getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "failure-" + scenario.getName());
                Path dir = Path.of("target", "screenshots");
                Files.createDirectories(dir);
                Files.write(dir.resolve("failure-" + System.currentTimeMillis() + ".png"), screenshot);
            }
        } catch (Exception e) {
            System.err.println("Could not capture failure screenshot: " + e.getMessage());
        } finally {
            DriverFactory.quitDriver();
        }
    }
}
