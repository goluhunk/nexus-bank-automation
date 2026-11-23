@smoke @login
Feature: Nexus Bank authentication

  Scenario: Login with valid credentials
    Given I open the Nexus Bank login page
    When I login with username "gaurav" and password "Bank@123"
    Then I should see the banking dashboard

  Scenario: Login with invalid credentials
    Given I open the Nexus Bank login page
    When I login with username "wrong-user" and password "wrong-password"
    Then I should see the login error "Invalid username or password"
