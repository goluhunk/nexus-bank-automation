package com.nexusbank.pages;

import com.nexusbank.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TransactionPage extends BasePage {
    private final By search = By.id("transaction-search");
    private final By searchButton = By.id("search-transactions");
    private final By rows = By.id("transaction-rows");

    public TransactionPage(WebDriver driver) { super(driver); }
    public void search(String id) { type(search, id); click(searchButton); }
    public boolean containsTransaction(String id) { return text(rows).contains(id); }
}
