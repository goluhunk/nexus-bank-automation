package com.nexusbank.pages;

import com.nexusbank.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BeneficiaryPage extends BasePage {
    private final By name = By.id("beneficiary-name");
    private final By account = By.id("beneficiary-account");
    private final By ifsc = By.id("beneficiary-ifsc");
    private final By add = By.id("add-beneficiary");
    private final By list = By.id("beneficiary-list");

    public BeneficiaryPage(WebDriver driver) { super(driver); }
    public void addBeneficiary(String n, String a, String i) {
        type(name, n); type(account, a); type(ifsc, i); click(add);
    }
    public boolean hasBeneficiary(String n) { return text(list).contains(n); }
}
