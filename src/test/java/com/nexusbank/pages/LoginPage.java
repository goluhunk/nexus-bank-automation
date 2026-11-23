package com.nexusbank.pages;

import com.nexusbank.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {


    private final By username = By.id("username");
    private final By password = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By error = By.id("login-error");

    public LoginPage(WebDriver driver) { super(driver); }
    public void open(String url) { driver.get(url); }
    public void login(String user, String pass) {
        type(username, user);
        type(password, pass);
        click(loginButton);
    }
    public boolean isErrorDisplayed(String expected) {
        return visible(error) && text(error).contains(expected);
    }
}
