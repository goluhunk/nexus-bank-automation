@regression
Feature: Nexus Bank customer banking journeys

  Background:
    Given I am logged in to Nexus Bank

  @dashboard
  Scenario: View dashboard summary
    When I navigate to the dashboard
    Then I should see customer name "Gaurav Singh"
    And I should see total balance "48520.75"

  @accounts
  Scenario: View savings account details
    When I open the accounts page
    Then I should see the savings account number "**** 4821"
    And I should see available balance "32520.75"

  @beneficiary
  Scenario: Add a new beneficiary
    When I open beneficiary management
    And I add beneficiary "Priya Sharma" with account "9876543210" and IFSC "NEXU0000123"
    Then I should see beneficiary "Priya Sharma" in the beneficiary list

  @transfer
  Scenario: Transfer funds to a beneficiary
    When I open fund transfer
    And I transfer "500" to beneficiary "John Smith"
    Then I should see transfer confirmation
    And the transaction reference should be displayed

  @transactions
  Scenario: Search transaction history
    When I open transaction history
    And I search transactions for "TRX1002"
    Then I should see transaction "TRX1002"
