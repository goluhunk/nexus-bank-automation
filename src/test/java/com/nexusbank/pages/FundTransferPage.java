package com.nexusbank.pages;

import com.nexusbank.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class FundTransferPage extends BasePage {
    private final By beneficiary = By.id("transfer-beneficiary");
    private final By amount = By.id("transfer-amount");
    private final By remarks = By.id("transfer-remarks");
    private final By submit = By.id("submit-transfer");
    private final By confirmation = By.id("transfer-confirmation");
    private final By reference = By.id("transaction-reference");

    public FundTransferPage(WebDriver driver) { super(driver); }
    public void transfer(String name, String value) {
        new Select(wait.until(d -> d.findElement(beneficiary))).selectByVisibleText(name);
        type(amount, value);
        type(remarks, "Automation test transfer");
        click(submit);
    }
    public boolean isConfirmed() { return visible(confirmation); }
    public String reference() { return text(reference); }
}
