package com.selenium.steps;

import com.selenium.models.UserCredentials;
import com.selenium.pages.LoginPage;
import io.cucumber.java.en.When;

public class LoginSteps {

    private final LoginPage loginPage;

    public LoginSteps(LoginPage loginPage) {
        this.loginPage = loginPage;
    }

    @When("User fills and submit the login form with :")
    public void user_fills_and_submit_the_login_form(UserCredentials userCredentials) {
        loginPage.loginAs(userCredentials);
    }
}