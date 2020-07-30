package com.achui.crawler.spider.core;

import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author portz
 * @date 2020/7/29 17:08
 */
@Data
public class LoginPage extends Page {
    private String userName;
    private String password;
    private String userNameInputXpath;
    private String passwordInputXpath;
    private String loginBtnXpath;
    private String toUrlAfterLogin;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public String processPage() throws Exception {
        this.getWebDriver().get(this.getUrl());
        WebElement usernameEle = ((ChromeDriver) webDriver).findElementByXPath(this.userNameInputXpath);
        WebElement passwordEle = ((ChromeDriver) webDriver).findElementByXPath(this.passwordInputXpath);
        WebElement loginEle = ((ChromeDriver) webDriver).findElementByXPath(this.loginBtnXpath);
        //usernameEle.sendKeys(userName);
        //passwordEle.sendKeys(password);
        loginEle.click();
        //Thread.sleep(3000);
        //webDriver.get(toUrlAfterLogin);
        return null;
    }
}
