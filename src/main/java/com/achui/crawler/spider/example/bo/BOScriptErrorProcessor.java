package com.achui.crawler.spider.example.bo;

import com.achui.crawler.spider.core.PageProcessor;
import com.achui.crawler.spider.core.SpiderPage;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

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
        //webDriver.get("https://www9.qa.ehealthinsurance.com/bov2/index.html#/scripterrorlogdetail?scriptId=1&date=07-15-2020");
    }

    @Override
    public void processMainPage(SpiderPage page) throws Exception {
        TimeUnit.SECONDS.sleep(5);
        WebDriver webDriver = page.getWebDriverEngine();
        webDriver.get("https://www9.qa.ehealthinsurance.com/bov2/index.html#/scripterrorlogdetail?scriptId=1&date=07-15-2020");
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 30);
        //WebElement loadingBar = webDriver.findElement(By.xpath("//*[@id=\"loading-bar\"]"));
        //webDriverWait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//*[@id=\"loading-bar\"]"), 0));

        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(webDriver);
        wait.withTimeout(Duration.ofSeconds(10))
                .ignoring(NoSuchElementException.class)
                .pollingEvery(Duration.ofMillis(500))
                .withMessage("Demo");
        WebElement processBar = null;
        try {
            processBar = wait.until(new Function<WebDriver, WebElement>() {
                @Override
                public WebElement apply(WebDriver driver) {
                    return driver.findElement(By.xpath("//*[@id=\"loading-bar\"]"));
                }
            });
        } catch (Exception e) {
        }
        if (processBar != null) {
            WebDriverWait waitProcessor = new WebDriverWait(webDriver, 60);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"loading-bar\"]")));
        }
        //.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"loading-bar\"]")));
        log.info("==============");
        WebElement nextPage = webDriver.findElement(By.xpath("//*[@id=\"main-container\"]/div/div/div[2]/div/nav[1]/ul/li[6]"));
        List<WebElement> currentPage = webDriver.findElements(By.xpath("//*[@id=\"table-content\"]/table/tbody/tr"));
        boolean disabled = false;
        while (true) {
            TimeUnit.SECONDS.sleep(1);
            log.info("list.size:{}", currentPage.size());
            disabled = "disabled".equals(nextPage.getAttribute("class"));
            log.info("disable:{}", disabled);
            if (disabled) {
                break;
            }
            nextPage.findElement(By.xpath("a")).click();
            //webDriverWait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//*[@id=\"loading-bar\"]"), 0));
            nextPage = webDriver.findElement(By.xpath("//*[@id=\"main-container\"]/div/div/div[2]/div/nav[1]/ul/li[6]"));
            currentPage = webDriver.findElements(By.xpath("//*[@id=\"table-content\"]/table/tbody/tr"));

        }
    }

    @Override
    public Queue getRequestQueue() {
        return null;
    }
}
