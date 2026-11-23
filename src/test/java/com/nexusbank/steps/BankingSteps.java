package com.nexusbank.steps;

import com.nexusbank.pages.*;
import com.nexusbank.utility.DriverFactory;
import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

public class BankingSteps {
    private DashboardPage dashboard;
    private AccountsPage accounts;
    private BeneficiaryPage beneficiaries;
    private FundTransferPage transfer;
    private TransactionPage transactions;

    @When("I navigate to the dashboard")
    public void navigateDashboard() { dashboard = new DashboardPage(DriverFactory.getDriver()); }

    @Then("I should see customer name {string}")
    public void customerName(String expected) {
        assertEquals(expected, new DashboardPage(DriverFactory.getDriver()).customerName());
    }

    @Then("I should see total balance {string}")
    public void totalBalance(String expected) {
        assertEquals(expected, new DashboardPage(DriverFactory.getDriver()).totalBalance());
    }

    @When("I open the accounts page")
    public void openAccounts() {
        clickNav("Accounts");
        accounts = new AccountsPage(DriverFactory.getDriver());
    }

    @Then("I should see the savings account number {string}")
    public void accountNumber(String expected) { assertEquals(expected, accounts.accountNumber()); }

    @Then("I should see available balance {string}")
    public void availableBalance(String expected) { assertEquals(expected, accounts.availableBalance()); }

    @When("I open beneficiary management")
    public void openBeneficiaries() {
        clickNav("Beneficiaries");
        beneficiaries = new BeneficiaryPage(DriverFactory.getDriver());
    }

    @When("I add beneficiary {string} with account {string} and IFSC {string}")
    public void addBeneficiary(String name, String account, String ifsc) {
        beneficiaries.addBeneficiary(name, account, ifsc);
    }

    @Then("I should see beneficiary {string} in the beneficiary list")
    public void beneficiaryVisible(String name) { assertTrue(beneficiaries.hasBeneficiary(name)); }

    @When("I open fund transfer")
    public void openTransfer() {
        clickNav("Fund Transfer");
        transfer = new FundTransferPage(DriverFactory.getDriver());
    }

    @When("I transfer {string} to beneficiary {string}")
    public void makeTransfer(String amount, String beneficiary) { transfer.transfer(beneficiary, amount); }

    @Then("I should see transfer confirmation")
    public void transferConfirmed() { assertTrue(transfer.isConfirmed()); }

    @Then("the transaction reference should be displayed")
    public void referenceDisplayed() { assertFalse(transfer.reference().isBlank()); }

    @When("I open transaction history")
    public void openTransactions() {
        clickNav("Transactions");
        transactions = new TransactionPage(DriverFactory.getDriver());
    }

    @When("I search transactions for {string}")
    public void searchTransaction(String id) { transactions.search(id); }

    @Then("I should see transaction {string}")
    public void transactionVisible(String id) { assertTrue(transactions.containsTransaction(id)); }

    private void clickNav(String label) {
        DriverFactory.getDriver().findElement(
                org.openqa.selenium.By.xpath("//nav//button[normalize-space()='" + label + "']")
        ).click();
    }
}
