package com.achui.crawler.spider.core;

import com.achui.crawler.spider.Request;
import com.achui.crawler.spider.page.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

/**
 * @author portz
 * @date 2020/8/3 16:07
 */
public class GithubPageProcessor implements PageProcessor {
    @Override
    public void login(Page page) {
        WebDriver webDriver = page.getWebDriverEngine();
        String userName = "admin";
        String password = "D*u4HlX4P8ey";
        String userInput = "//*[@id=\"login_field\"]";
        String passwordInput = "//*[@id=\"password\"]";
        String loginBtn = "//*[@id=\"login\"]/form/div[4]/input[9]";
        WebElement usernameEle = ((ChromeDriver) webDriver).findElementByXPath(userInput);
        WebElement passwordEle = ((ChromeDriver) webDriver).findElementByXPath(passwordInput);
        WebElement loginEle = ((ChromeDriver) webDriver).findElementByXPath(loginBtn);
        usernameEle.sendKeys("achui_1980@163.com");
        passwordEle.sendKeys("achui_19800724");
        loginEle.click();
    }

    @Override
    public void process(Page page) throws Exception {
        WebDriver webDriver = page.getWebDriverEngine();
        List<WebElement> projectList = webDriver.findElements(By.xpath("//*[@id=\"repos-container\"]/ul/li"));
        for (WebElement element : projectList) {
            WebElement projectUri = element.findElement(By.cssSelector("div>a"));
            System.out.println(projectUri.getAttribute("href"));
            Request request = new Request();
            request.setUrl(projectUri.getAttribute("href"));
            request.setCallback(this);
        }
    }
}
