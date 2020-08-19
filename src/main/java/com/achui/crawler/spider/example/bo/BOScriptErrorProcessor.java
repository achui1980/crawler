package com.achui.crawler.spider.example.bo;

import com.achui.crawler.spider.core.PageProcessor;
import com.achui.crawler.spider.core.Request;
import com.achui.crawler.spider.core.SpiderPage;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * @author portz
 * @date 2020/8/19 16:48
 */
@Slf4j
public class BOScriptErrorProcessor implements PageProcessor {
    @Override
    public void login(SpiderPage page) {
        WebDriver webDriver = page.getWebDriverEngine();
        String userName = "portz";
        String password = "Achui0724";
        String userInput = "//*[@id=\"userId\"]";
        String passwordInput = "//*[@id=\"password\"]";
        String loginBtn = "//*[@id=\"BOLogin\"]";
        WebElement usernameEle = ((ChromeDriver) webDriver).findElementByXPath(userInput);
        WebElement passwordEle = ((ChromeDriver) webDriver).findElementByXPath(passwordInput);
        WebElement loginEle = ((ChromeDriver) webDriver).findElementByXPath(loginBtn);
        usernameEle.sendKeys(userName);
        passwordEle.sendKeys(password);
        loginEle.click();
        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException e) {
            log.error("error:", e);
        }
        webDriver.get("https://www9.qa.ehealthinsurance.com/bov2/index.html#/scripterrorlogdetail?scriptId=1&date=07-15-2020");
    }

    @Override
    public void processMainPage(SpiderPage page) throws Exception {
        WebDriver webDriver = page.getWebDriverEngine();
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 30);
        //WebElement loadingBar = webDriver.findElement(By.xpath("//*[@id=\"loading-bar\"]"));
        webDriverWait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//*[@id=\"loading-bar\"]"), 0));
        log.info("==============");
        WebElement nextPage = webDriver.findElement(By.xpath("//*[@id=\"main-container\"]/div/div/div[2]/div/nav[1]/ul/li[6]"));
        List<WebElement> currentPage = webDriver.findElements(By.xpath("//*[@id=\"table-content\"]/table/tbody/tr"));

        do {
            TimeUnit.SECONDS.sleep(1);
            log.info("list.size:{}", currentPage.size());
            //TODO: Business logic
            nextPage.findElement(By.xpath("a")).click();
            //loadingBar = webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/section/div/div[2]/div[5]"));
            webDriverWait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//*[@id=\"loading-bar\"]"), 0));
            nextPage = webDriver.findElement(By.xpath("//*[@id=\"main-container\"]/div/div/div[2]/div/nav[1]/ul/li[6]"));
            //webDriverWait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(loadingBar)));
        } while (!"disabled".equals(nextPage.getAttribute("class")));
    }

    @Override
    public Queue getRequestQueue() {
        return null;
    }
}
