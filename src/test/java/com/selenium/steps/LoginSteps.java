package com.selenium.steps;

import com.selenium.driver.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

    @Given("User is on the home page")
    public void user_is_on_the_home_page() {
        System.out.println("User is on the home page");
    }

    @When("User fills and submit the login form")
    public void user_fills_and_submit_the_login_form() {
        System.out.println("Saisir identifiant et mot de passe...");
    }

    @Then("User should be redirected to dashboard page")
    public void user_should_be_redirected_to_dashboard_page() {
        System.out.println("Vérification de la redirection...");
    }
}