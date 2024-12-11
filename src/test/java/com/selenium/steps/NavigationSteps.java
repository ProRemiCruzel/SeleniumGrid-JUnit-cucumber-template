package com.selenium.steps;

import com.selenium.driver.DriverManager;
import com.selenium.enums.Routes;
import com.selenium.failsafe.policies.FailsafePolicies;
import dev.failsafe.Failsafe;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class NavigationSteps {

    @Given("User is on the {route} page")
    public void user_is_on_the_home_page(Routes route) {
        DriverManager.navigateTo(route);
    }

    @Then("User should be redirected to {route} page")
    public void user_should_be_redirected_to_dashboard_page(Routes route) {
        Failsafe.with(FailsafePolicies.backoffRetryPolicy())
                .run(() -> DriverManager.redirectedTo(route));
    }
}
