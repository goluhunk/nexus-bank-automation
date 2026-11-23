package com.nexusbank.pages;

import com.nexusbank.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage {
    private final By heading = By.id("dashboard-heading");
    private final By customer = By.id("customer-name");
    private final By totalBalance = By.id("total-balance");

    public DashboardPage(WebDriver driver) { super(driver); }
    public boolean isLoaded() { return visible(heading); }
    public String customerName() { return text(customer); }
    public String totalBalance() { return text(totalBalance); }
}
