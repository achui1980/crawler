package com.achui.crawler.spider.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * @author portz
 * @date 2020/7/31 15:47
 */
public class SMZDMPageProcessor implements PageProcessor {
    @Override
    public void login(SpiderPage page) {
        WebDriver webDriver = page.getWebDriverEngine();
        String userName = "admin";
        String password = "D*u4HlX4P8ey";
        String userInput = "//*[@id=\"app\"]/div/form/div[2]/div/div/input";
        String passwordInput = "//*[@id=\"app\"]/div/form/div[3]/div/div/input";
        String loginBtn = "//*[@id=\"app\"]/div/form/button";
        WebElement usernameEle = ((ChromeDriver) webDriver).findElementByXPath(userInput);
        WebElement passwordEle = ((ChromeDriver) webDriver).findElementByXPath(passwordInput);
        WebElement loginEle = ((ChromeDriver) webDriver).findElementByXPath(loginBtn);
        //usernameEle.sendKeys(userName);
        //passwordEle.sendKeys(password);
        loginEle.click();

        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 30);
        WebElement tableMenu = webDriverWait.until(d -> d.findElement(By.xpath("//*[@id=\"app\"]/div/div[1]/div/div[1]/div/ul/div[9]/li")));
        tableMenu.click();
        WebElement table = webDriverWait.until(d -> d.findElement(By.xpath("//*[@id=\"app\"]/div/div[1]/div/div[1]/div/ul/div[9]/li/ul/div[4]/a/li")));
        table.click();
        WebElement searchBtn = webDriverWait.until(d -> d.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/section/div/div[1]/button[1]")));
        WebElement searchInput = webDriverWait.until(d -> d.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/section/div/div[1]/div[1]/input")));
        //searchInput.sendKeys("F");
        searchBtn.click();
    }

    @Override
    public void processMainPage(SpiderPage page) throws Exception {
        WebDriver webDriver = page.getWebDriverEngine();
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 30);
        WebElement mask = webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/section/div/div[2]/div[5]"));
        webDriverWait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(mask)));
        WebElement nextPage = webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/section/div/div[3]/div/button[2]"));
        WebElement currentPage = webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/section/div/div[3]/div/span[3]/div/input"));

        while (nextPage.getAttribute("disabled") == null) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(currentPage.getAttribute("value"));
            //TODO: Business logic
            processLogic(page);
            nextPage.click();
            mask = webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/section/div/div[2]/div[5]"));
            webDriverWait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(mask)));
        }
        processLogic(page);
    }

    @Override
    public Queue<Request> getRequestQueue() {
        return null;
    }

    private String processLogic(SpiderPage page) {
        WebDriver webDriver = page.getWebDriverEngine();
        WebElement titleElement = webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/section/div/div[2]/div[3]/table/tbody/tr[1]/td[3]/div/span[1]"));
        System.out.println(titleElement.getText());
        RequestItem requestItem = new RequestItem();
        requestItem.put("title", titleElement.getText());
        page.putToList(requestItem);
        return null;
    }
}
