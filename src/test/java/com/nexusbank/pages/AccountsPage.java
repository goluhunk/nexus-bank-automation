package com.nexusbank.pages;

import com.nexusbank.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountsPage extends BasePage {
    private final By accountNumber = By.id("savings-account-number");
    private final By availableBalance = By.id("savings-available");

    public AccountsPage(WebDriver driver) { super(driver); }
    public String accountNumber() { return text(accountNumber); }
    public String availableBalance() { return text(availableBalance); }
}
