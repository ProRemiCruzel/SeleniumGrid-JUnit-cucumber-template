package com.selenium.pages;

import com.selenium.driver.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class Page {

    public Page() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    public void waitForElementToBeVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void click(WebElement element) {
        waitForElementToBeVisible(element);
        element.click();
    }

    public void fillInputField(WebElement element, String value) {
        waitForElementToBeVisible(element);
        element.clear();
        element.sendKeys(value);
    }
}