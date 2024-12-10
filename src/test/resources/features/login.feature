Feature: Login

  Scenario: Login using valid credentials
    Given User is on the home page
    When User fills and submit the login form
    Then User should be redirected to dashboard page