package com.selenium.driver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.selenium.configuration.ConfigManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {

    private static final Logger logger = LoggerFactory.getLogger(DriverManager.class);
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        options.setAcceptInsecureCerts(true);
        return options;
    }

    private static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
        options.setAcceptInsecureCerts(true);
        return options;
    }

    private static URL getSeleniumGridUrl() throws MalformedURLException {
        String gridUrl = ConfigManager.get("SELENIUM_GRID_URL");
        if (gridUrl == null || gridUrl.isEmpty())
            gridUrl = "http://localhost:4444/wd/hub";

        logger.info("Selenium Grid URL : {}", gridUrl);
        return new URL(gridUrl);
    }

    private static MutableCapabilities getCapabilitiesForBrowser(String browser) {
        return switch (browser.toLowerCase()) {
            case "firefox" -> getFirefoxOptions();
            case "chrome", "chromium" -> getChromeOptions();
            default -> throw new IllegalArgumentException("Unrecognized browser : " + browser);
        };
    }

    public static void setDriver(String browser) {
        try {
            logger.info("Initializing WebDriver for browser : {}", browser);
            driver.set(new RemoteWebDriver(getSeleniumGridUrl(), getCapabilitiesForBrowser(browser)));
        } catch (MalformedURLException e) {
            throw new RuntimeException("Malformed selenium grid url : " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Error initializing WebDriver : " + e.getMessage(), e);
        }
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}