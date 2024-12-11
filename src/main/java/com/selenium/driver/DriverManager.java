package com.selenium.driver;

import com.selenium.enums.Routes;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
import java.util.Objects;

public class DriverManager {

    private static final Logger logger = LoggerFactory.getLogger(DriverManager.class);
    private static final ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
    private static WebDriver localDriver;


    private static String getExecutionMode() {
        return System.getProperty("mode", "local");
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.setAcceptInsecureCerts(true);
        if (Objects.equals(getExecutionMode(), "remote")) {
            options.addArguments("--no-sandbox");
            options.addArguments("--headless");
        }
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
            String executionMode = getExecutionMode();

            if ("remote".equalsIgnoreCase(executionMode)) {
                logger.info("Initializing remote WebDriver for browser : {}", browser);
                threadLocalDriver.set(new RemoteWebDriver(getSeleniumGridUrl(), getCapabilitiesForBrowser(browser)));
            } else {
                logger.info("Initializing local WebDriver for browser : {}", browser);
                if ("firefox".equalsIgnoreCase(browser)) {
                    localDriver = new FirefoxDriver(getFirefoxOptions());
                } else {
                    ChromeOptions options = getChromeOptions();
                    localDriver = new ChromeDriver(options);
                }
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Malformed selenium grid url : " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Error initializing WebDriver : " + e.getMessage(), e);
        }
    }

    public static WebDriver getDriver() {
        if (localDriver != null)
            return localDriver;
        else
            return threadLocalDriver.get();

    }

    public static void quitDriver() {
        getDriver().quit();
    }

    public static void navigateTo(Routes route) {
        getDriver().navigate().to(ConfigManager.get("BASE_URL").concat(route.getEndpoint()));
    }

    public static void redirectedTo(Routes route) {
        String currentUrl = getDriver().getCurrentUrl();
        String expectedUrl = ConfigManager.get("BASE_URL").concat(route.getEndpoint());
        assert Objects.equals(currentUrl, expectedUrl) : String.format("Unexpected url: %s, expected: %s", currentUrl, expectedUrl);
    }
}