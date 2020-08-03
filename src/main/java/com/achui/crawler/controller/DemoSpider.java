package com.achui.crawler.controller;

import com.achui.crawler.spider.core.BrigePage;
import com.achui.crawler.spider.core.LoginPage;
import com.achui.crawler.spider.core.SearchPage;
import com.achui.crawler.util.WebDriverPool;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author portz
 * @date 2020/7/24 15:44
 */
@RestController
@Slf4j
public class DemoSpider {
    @Autowired
    public WebDriverPool pool;

    @GetMapping("/spider")
    private String spider() throws Exception {
        String url = "https://panjiachen.github.io/vue-element-admin/#/login";
        String userName = "admin";
        String password = "D*u4HlX4P8ey";
        String userInput = "//*[@id=\"app\"]/div/form/div[2]/div/div/input";
        String passwordInput = "//*[@id=\"app\"]/div/form/div[3]/div/div/input";
        String loginBtn = "//*[@id=\"app\"]/div/form/button";
        WebDriver webDriver = pool.getWebDriverFromPool();
        /******************** Login *************************/
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.setUrl(url);
        loginPage.setLoginBtnXpath(loginBtn);
        loginPage.setPassword(password);
        loginPage.setUserName(userName);
        loginPage.setUserNameInputXpath(userInput);
        loginPage.setPasswordInputXpath(passwordInput);
        loginPage.processPage();
        /******************** Login *************************/

        /******************** Got To Search Page *************************/
//        WebElement tableMenu = pool.getWebDriverFactory().getWait().until(d -> d.findElement(By.xpath("//*[@id=\"app\"]/div/div[1]/div/div[1]/div/ul/div[9]/li")));
//        tableMenu.click();
//        WebElement table = pool.getWebDriverFactory().getWait().until(d -> d.findElement(By.xpath("//*[@id=\"app\"]/div/div[1]/div/div[1]/div/ul/div[9]/li/ul/div[4]/a/li")));
//        table.click();
        BrigePage brigePage = new BrigePage(webDriver);
        brigePage.processPage();
        /******************** Got To Search Page *************************/

        /******************** Search Result *************************/
//        WebElement searchBtn = pool.getWebDriverFactory().getWait().until(d -> d.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/section/div/div[1]/button[1]")));
//        WebElement searchInput = pool.getWebDriverFactory().getWait().until(d -> d.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/section/div/div[1]/div[1]/input")));
//        searchInput.sendKeys("F");
//        searchBtn.click();

//        WebElement mask = webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/section/div/div[2]/div[5]"));
//        pool.getWebDriverFactory().getWait().until(ExpectedConditions.not(ExpectedConditions.visibilityOf(mask)));
//        /******************** Search Result *************************/
//        //TODO: get data from first page
//        WebElement nextPage = webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/section/div/div[3]/div/button[2]"));
//        while (nextPage.getAttribute("disabled") == null) {
//            nextPage.click();
//            mask = webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/section/div/div[2]/div[5]"));
//            pool.getWebDriverFactory().getWait().until(ExpectedConditions.not(ExpectedConditions.visibilityOf(mask)));
//            //TODO get data from second page
//        }
        SearchPage searchPage = new SearchPage(webDriver);
        searchPage.processPage();
        /******************** Search Result *************************/

        return "a";
    }
}
