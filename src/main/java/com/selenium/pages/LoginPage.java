package com.selenium.pages;

import com.selenium.models.UserCredentials;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends Page {

    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    public LoginPage() {
        super();
    }

    public void loginAs(UserCredentials userCredentials) {
        fillInputField(usernameField, userCredentials.getUsername());
        fillInputField(passwordField, userCredentials.getPassword());
        click(loginButton);
    }
}
