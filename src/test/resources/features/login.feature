Feature: Login

  Scenario: Login using valid credentials
    Given User is on the LOGIN page
    When User fills and submit the login form with :
      | username      | password     |
      | standard_user | secret_sauce |
    Then User should be redirected to INVENTORY page